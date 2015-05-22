package com.core.config;

import com.client.confluence.ChildrenGetter;
import com.client.confluence.GetContent;
import com.client.confluence.PageCreator;
import com.client.confluence.UpdatePage;
import com.wordnik.swagger.models.Swagger;
import freemarker.ext.beans.HashAdapter;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by shubham.tyagi on 11/05/15.
 */
public class PresentDocumentation {
    private String json;
    private Swagger swagger;
    private String newModel;

    public PresentDocumentation(String json, Swagger swagger) {
        this.json = json;
        this.swagger = swagger;
    }

    public void generate(String apiName, String apiVersion, String templateName, String templateLocation, String BASE_URL, String spaceKey, String pageId, String userName, String password) throws IOException, JSONException, TemplateException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(PresentDocumentation.class, "/");
        //cfg.getTemplate("Template.ftl");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            if(!templateLocation.equals("null")) cfg.setDirectoryForTemplateLoading(new File(templateLocation));
            Map<String, Object> templateInput = new HashMap<String, Object>();
            Map<String, Object> schema = new HashMap<String, Object>();
           // List<String> definitions = new ArrayList<String>();
            Template template = cfg.getTemplate(templateName);
            StringWriter stringWriter = new StringWriter();
            boolean flag;

            Map<String, JSONObject> models = new HashMap<String, JSONObject>();
            System.out.println(json);
            JSONObject jsonObject = new JSONObject(json);

            if(jsonObject.has("definitions")) {
                Iterator<?> iterator = jsonObject.getJSONObject("definitions").keys();
                while (iterator.hasNext()) {
                    String key = (String)iterator.next();
                    if(new JSONObject(jsonObject.getJSONObject("definitions").getString(key)).has("properties")) {
                       // definitions.add(key);
                        String convert = convert(new JSONObject(jsonObject.getJSONObject("definitions").getString(key)).getJSONObject("properties")).toString();
                        models.put("#/definitions/" + key, new JSONObject(convert));
                        schema.put("#/definitions/" + key, newModel);
                    }
                   // System.out.println();
                }
            }

            System.out.println(models.size());

            System.out.println("while in");
            flag = true;

            Map<String, JSONObject> improvedModels = new HashMap<String, JSONObject>();
            int co = 0;
            while(models.size() != 0) {
                System.out.println(models.size());
                if(co > 50) break;
                co++;
                List<String> toRemove = new ArrayList<String>();
                for(String model : models.keySet()) {
                    JSONObject jsonObject1 = models.get(model);
                    Iterator<?> iterator = jsonObject1.keys();
                    flag = false;
                    List<String> keys = new ArrayList<String>();
                    while(iterator.hasNext()) {
                        String key = (String) iterator.next();
                        keys.add(key);
                    }
                    for(String key : keys) {
                        String value = jsonObject1.getString(key);
                        boolean flag2 = false;
                        if(value.charAt(0) == '[') {
                            flag2 = true;
                            value = value.substring(2,value.length()-2);
                        }
                        if(models.containsKey("#/definitions/" + value)) {
                            flag = true;
                        }
                        if(improvedModels.containsKey("#/definitions/" + value)) {
                            JSONObject object = improvedModels.get("#/definitions/" + value);
                            if(flag2) {
                                List<JSONObject> list = new ArrayList<JSONObject>();
                                list.add(object);
                                jsonObject1.put(key, list);
                            }
                            else {
                                jsonObject1.put(key, object);
                            }
                        }
                    }
                    if(!flag) {
                        improvedModels.put(model, new JSONObject(jsonObject1.toString()));
                        toRemove.add(model);
                    }
                    else {
                        models.put(model, jsonObject1);
                    }
                }
                for (String toRemove1 : toRemove) {
                    models.remove(toRemove1);
                }
            }
            for(String model : models.keySet()) {
                improvedModels.put(model, models.get(model));
            }
            models = improvedModels;
            Map<String, String> responseModels = new HashMap<String, String>();
            for(String model : models.keySet()) {
                responseModels.put(model, models.get(model).toString(1));
            }
            System.out.println("while out");

            templateInput.put("swagger", swagger);
            templateInput.put("models", responseModels);
            templateInput.put("schema", schema);

            template.process(templateInput, stringWriter);
            String data = stringWriter.toString();

            flag = false;

            List<String> listOfChildPages = new ArrayList<String>();

            ChildrenGetter childPages = new ChildrenGetter();

            listOfChildPages = childPages.getAllChildren(pageId, BASE_URL, userName, password, "UTF-8");
            flag = false;
            GetContent getContent = new GetContent();
            for(String page: listOfChildPages) {
                JSONObject json = getContent.getContent(page, BASE_URL, userName, password, "UTF-8");
                if(json.getString("title").equals(apiName+" "+apiVersion)) {
                    if(!isValidUpdate(apiVersion)) {
                        flag = true;
                        break;
                    }
                    UpdatePage updatePage = new UpdatePage();
                    updatePage.updateContent(pageId, spaceKey, apiName+" "+apiVersion, page, data, BASE_URL, userName, password, "UTF-8");
                    flag = true;
                }
            }

            if(!flag) {
                PageCreator createPage = new PageCreator();
                createPage.createPage(spaceKey, apiName+" "+apiVersion, pageId, data, BASE_URL, userName, password, "UTF-8");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean isValidUpdate(String apiVersion) {
        return apiVersion.contains("alpha") || apiVersion.contains("beta");
    }

    JSONObject convert(JSONObject properties) throws JSONException {
        JSONObject newJsonObject = new JSONObject();
        newModel = "";
        Iterator<?> iter = properties.keys();
        while(iter.hasNext()) {
            String key = (String) iter.next();
            JSONObject variable = properties.getJSONObject(key);
            if(variable.has("ref")) {
                newModel += "\t" + key + "(" + variable.getString("ref") + ((variable.has("required") && variable.getBoolean("required"))?"":",optional") + ")";
                if(variable.has("example")) {
                    newJsonObject.put(key, variable.getString("example"));
                }
                else if(variable.has("_default")) {
                    newJsonObject.put(key, variable.getString("_default"));
                }
                else {
                    newJsonObject.put(key, variable.getString("ref"));
                }
            }
            else if(variable.getString("type").equals("array")) {
                String type = "";
                if(variable.getJSONObject("items").has("type")) {
                    type = variable.getJSONObject("items").getString("type");
                }
                else {
                    type = variable.getJSONObject("items").getString("ref");
                }
                if(type.equals("ref")) {
                    type = variable.getJSONObject("items").getString("ref");
                }
                newModel += "\t" + key + "(" + "array[" + type + "])";
                JSONObject keyInfo = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(type);
                if(variable.has("example")) {
                    newJsonObject.put(key, variable.getString("example"));
                }
                else if(variable.has("_default")) {
                    newJsonObject.put(key, variable.getString("_default"));
                }
                else {
                    newJsonObject.put(key, jsonArray);
                }
            }
            else if(variable.has("format") && variable.getString("format").equals("int64")) {
                newModel += "\t" + key + "(" + "long" + ")";
                JSONObject keyInfo = new JSONObject();
                if(variable.has("example")) {
                    newJsonObject.put(key, variable.getString("example"));
                }
                else if(variable.has("_default")) {
                    newJsonObject.put(key, variable.getString("_default"));
                }
                else {
                    newJsonObject.put(key, "long");
                }
            }
            else if(variable.getString("type").equals("number")) {
                newModel += "\t" + key + "(" + variable.getString("format") + ")";
                JSONObject keyInfo = new JSONObject();
                if(variable.has("example")) {
                    newJsonObject.put(key, variable.getString("example"));
                }
                else if(variable.has("_default")) {
                    newJsonObject.put(key, variable.getString("_default"));
                }
                else {
                    newJsonObject.put(key, variable.getString("format"));
                }
            }
            else {
                newModel += "\t" + key + "(" + variable.getString("type") + ")";
                newJsonObject.put(key, variable.getString("type"));
                JSONObject keyInfo = new JSONObject();
                if(variable.has("example")) {
                    newJsonObject.put(key, variable.getString("example"));
                }
                else if(variable.has("_default")) {
                    newJsonObject.put(key, variable.getString("_default"));
                }
                else {
                    newJsonObject.put(key, variable.getString("type"));
                }
            }
            if(variable.has("description")) {
                newModel += ":" + variable.getString("description");
            }
            if(variable.has("_enum")) {
                newModel += "[";
                JSONArray enums =  variable.getJSONArray("_enum");
                for(int i = 0; i < enums.length(); i++) {
                    if(i != enums.length()-1) {
                        newModel += enums.get(i) + ",";
                    }
                    else {
                        newModel += enums.get(i);
                    }
                }
                newModel += "]";
            }
            if(variable.has("minimum")) {
                newModel += "[" + variable.getString("minimum") + "-" + variable.getString("maximum") + "]";
            }
            newModel += ",\n";
        }

        newModel = " {\n" + newModel + "}";

        return new JSONObject(newJsonObject.toString(1));
    }
}
