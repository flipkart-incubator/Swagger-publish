package com.client.confluence;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by shubham.tyagi on 13/04/15.
 */
public class GetContent {

    public JSONObject getContent(String id, String BASE_URL, String USERNAME, String PASSWORD, String ENCODING) throws JSONException, UnsupportedEncodingException {
        String s = BASE_URL + "/rest/api/content/" + id + "?expand=body.storage.value,version,ancestors,children,descendants,space&os_authType=basic&os_username=" + URLEncoder.encode(USERNAME, ENCODING) + "&os_password=" + URLEncoder.encode(PASSWORD, ENCODING);
        HttpClient client = new DefaultHttpClient();
        HttpGet getPageRequest = new HttpGet(s);
        JSONObject ret = null;
        try {
            HttpResponse getPageResponse = client.execute(getPageRequest);
            String pageContent = null;
            HttpEntity pageEntity = null;

            pageEntity = getPageResponse.getEntity();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pageEntity.getContent()));
            String ss = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                ss += line;
            }

            JSONObject page = new JSONObject(ss);
            ret = page;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
};
