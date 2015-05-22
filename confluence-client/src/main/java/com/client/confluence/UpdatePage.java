package com.client.confluence;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DecompressingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shubham.tyagi on 14/04/15.
 */
public class UpdatePage {

    public void updateContent(String parent, String key, String title, String id, String html, String BASE_URL, String USERNAME, String PASSWORD, String ENCODING) throws IOException, JSONException {
        String URl = BASE_URL + "/rest/api/content/" + id + "?os_authType=basic&os_username=" + URLEncoder.encode(USERNAME, ENCODING) + "&os_password=" + URLEncoder.encode(PASSWORD, ENCODING);
        GetContent content = new GetContent();
        JSONObject json = content.getContent(id, BASE_URL, USERNAME, PASSWORD, ENCODING);

        json.getJSONObject("body").getJSONObject("storage").put("value", html);
        json.put("title", title);
        json.getJSONObject("version").put("number", json.getJSONObject("version").getInt("number") + 1);
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "page");
        map.put("id", parent);
        List<Map<String, String> > list = new ArrayList<Map<String, String>>();
        list.add(map);
        json.put("ancestors",list);
        HttpPut putPageRequest = new HttpPut(URl);
        HttpClient client = new DefaultHttpClient();
        StringEntity params =new StringEntity(json.toString());

        putPageRequest.addHeader("content-type", "application/json");
        putPageRequest.addHeader("Accept", "application/json");
        putPageRequest.setEntity(params);
        client.execute(putPageRequest);
    }
}
