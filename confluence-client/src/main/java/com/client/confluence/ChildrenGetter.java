package com.client.confluence;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shubham.tyagi on 13/04/15.
 */
public class ChildrenGetter {
    public List<String> getAllChildren(String id, String BASE_URL, String USERNAME, String PASSWORD, String ENCODING) throws IOException, JSONException {
        List<String> children = new ArrayList<String>();
        String URL = BASE_URL + "/rest/api/content/" + id.toString() + "/child/page" + "?os_authType=basic&os_username=" + URLEncoder.encode(USERNAME, ENCODING) + "&os_password=" + URLEncoder.encode(PASSWORD, ENCODING);
        HttpGet getContent = new HttpGet(URL);
        HttpEntity getEntity = null;
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpResponse getPageResponse = client.execute(getContent);
        getEntity = getPageResponse.getEntity();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getEntity.getContent()));
        String response = "";
        String line;
        while((line = bufferedReader.readLine()) != null) {
            response += line;
        }
        JSONObject json = new JSONObject(response);
        Map<String, Integer> mappedID = new HashMap<String, Integer>();
        JSONArray jsonArray = (JSONArray) json.get("results");
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            children.add(jsonObject.get("id").toString());
            mappedID.put(children.get(i), 1);
        }

        for(int i = 0; i < children.size(); i++) {
            URL = BASE_URL + "/rest/api/content/" + children.get(i) + "/child/page" + "?os_authType=basic&os_username=" + URLEncoder.encode(USERNAME, ENCODING) + "&os_password=" + URLEncoder.encode(PASSWORD, ENCODING);
            getContent = new HttpGet(URL);
            getEntity = null;
            client = new DefaultHttpClient();
            getPageResponse = client.execute(getContent);
            getEntity = getPageResponse.getEntity();
            bufferedReader = new BufferedReader(new InputStreamReader(getEntity.getContent()));
            response = "";
            while((line = bufferedReader.readLine()) != null) {
                response += line;
            }
            json = new JSONObject(response);

            jsonArray = (JSONArray) json.get("results");
            for(int j = 0; j < jsonArray.length(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                mappedID.put(jsonObject.get("id").toString(), 0);
            }
        }
        List<String> directDescendants = new ArrayList<String>();

        for(int i = 0; i < children.size(); i++) {
            if(mappedID.get(children.get(i)) == 1) {
                directDescendants.add(children.get(i));
            }
        }
        return directDescendants;
    }
}