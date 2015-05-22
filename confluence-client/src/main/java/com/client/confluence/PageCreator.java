package com.client.confluence;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shubham.tyagi on 13/04/15.
 */
public class PageCreator {

    public void createPage(String spaceKey, String title, String content, String BASE_URL, String USERNAME, String PASSWORD, String ENCODING) throws JSONException, IOException {
        String URl = BASE_URL + "/rest/api/content" + "?os_authType=basic&os_username=" + URLEncoder.encode(USERNAME, ENCODING) + "&os_password=" + URLEncoder.encode(PASSWORD, ENCODING);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "page");
        jsonObject.put("title", title);
        List<Map<String, String> > list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", spaceKey);
        jsonObject.put("space", map);
        Map<String, String> value = new HashMap<String, String>();
        value.put("value", content);
        value.put("representation", "storage");
        Map<String, Map<String, String> > storage = new HashMap<String, Map<String, String>>();
        storage.put("storage", value);
        jsonObject.put("body", storage);
        HttpPost request = new HttpPost(URl);
        StringEntity params =new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept","application/json");
        request.setEntity(params);
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        client.execute(request);
    }

    public void createPage(String spaceKey, String title, String id, String content, String BASE_URL, String USERNAME, String PASSWORD, String ENCODING) throws JSONException, IOException {
        String URl = BASE_URL + "/rest/api/content" + "?os_authType=basic&os_username=" + URLEncoder.encode(USERNAME, ENCODING) + "&os_password=" + URLEncoder.encode(PASSWORD, ENCODING);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "page");
        jsonObject.put("title", title);
        List<Map<String, String> > list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        Map<String, Map<String, String> >  map2 = new HashMap<String, Map<String, String>>();
        map.put("key", spaceKey);
        jsonObject.put("space", map);
        map.clear();
        map.put("type", "page");
        map.put("id", id);
        Map<String, String> value = new HashMap<String, String>();
        value.put("value", content);
        value.put("representation", "storage");
        Map<String, Map<String, String> > storage = new HashMap<String, Map<String, String>>();
        storage.put("storage", value);
        jsonObject.put("body", storage);
        list.add(map);
        jsonObject.put("ancestors", list);
        HttpPost request = new HttpPost(URl);
        StringEntity params =new StringEntity(jsonObject.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept","application/json");
        request.setEntity(params);
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        client.execute(request);
    }
}
