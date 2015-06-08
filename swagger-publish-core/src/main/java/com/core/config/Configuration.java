package com.core.config;

import com.core.reader.JaxrsReader;
import com.google.gson.Gson;
import com.wordnik.swagger.config.FilterFactory;
import com.wordnik.swagger.core.filter.SpecFilter;
import com.wordnik.swagger.models.Swagger;

import java.net.URL;
import java.util.*;

/**
 * Created by shubham.tyagi on 08/05/15.
 */
public class Configuration {
    private String locations;
    private String apiVersion;
    private String templateLocation;
    private String apiName;
    private String spaceKey;
    private String pageId;
    private String templateName;
    private String userName;
    private String password;
    private String swaggerApiReader;
    private String BASE_URL;

    private Swagger swagger;

    private final SpecFilter specFilter = new SpecFilter();

    public String getLocations() { return locations; }

    public void setLocations(String locations) { this.locations = locations; }

    public String getApiVersion() { return apiVersion; }

    public void setApiVersion(String apiVersion) { this.apiVersion = apiVersion; }

    public String getTemplateLocation() { return templateLocation; }

    public void setTemplateLocation(String templateLocation) { this.templateLocation = templateLocation; }

    public String getSpaceKey() { return spaceKey; }

    public void setSpaceKey(String spaceKey) { this.spaceKey = spaceKey; }

    public String getPageId() { return pageId; }

    public void setPageId(String pageId) { this.pageId = pageId; }

    public String getTemplateName() { return templateName; }

    public void setTemplateName(String templateLocation) { this.templateLocation = templateLocation; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getBASE_URL() { return BASE_URL; }

    public void setBASE_URL(String BASE_URL) { this.BASE_URL = BASE_URL; }

    public String getSwaggerApiReader() { return swaggerApiReader; }

    public String getApiName() { return apiName; }

    public void setApiName(String apiName) { this.apiName = apiName; }

    public Configuration(String locations, String apiVersion, String apiName, String templateLocation, String templateName, String spaceKey, String pageId, String BASE_URL, String userName, String password) {
        this.locations = locations;
        this.apiVersion = apiVersion;
        this.apiName = apiName;
        if(templateLocation.equals("null")) {
            if(pageId != null) {
                this.templateName = "Template.ftl";
            }
            else {
                this.templateName = "Template2.ftl";
            }
            this.templateLocation = templateName;
        }
        else {
            this.templateLocation = templateLocation;
            this.templateName = templateName;
        }
        this.spaceKey = spaceKey;
        this.pageId = pageId;
        this.BASE_URL = BASE_URL;
        this.userName = userName;
        this.password = password;
    }

    public void generateDoc() throws Exception {
        Set<Class<?> > validClasses = new GetValidClasses().getValidClasses(locations);
        swagger = new JaxrsReader(swagger).read(validClasses);

        if (FilterFactory.getFilter() != null) {
            swagger = new SpecFilter().filter(swagger, FilterFactory.getFilter(),
                    new HashMap<String, List<String>>(), new HashMap<String, String>(),
                    new HashMap<String, List<String>>());
        }

        Gson gson = new Gson();
        String json = gson.toJson(swagger);
        System.out.println(json);
        PresentDocumentation presentDocumentation = new PresentDocumentation(json, swagger);
        presentDocumentation.generate(apiName, apiVersion, templateName, templateLocation, BASE_URL, spaceKey, pageId, userName, password);
    }
}
