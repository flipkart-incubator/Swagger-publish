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
public class GetChildPages {

    public List<String> getAllChildren(String id, String BASE_URL, String USERNAME, String PASSWORD, String ENCODING) throws IOException, JSONException {
        List<String> children = new ArrayList<String>();
        String URL = BASE_URL + "/rest/api/content/" + id + "/child/page" + "?os_authType=basic&os_username=" + URLEncoder.encode(USERNAME, ENCODING) + "&os_password=" + URLEncoder.encode(PASSWORD, ENCODING);
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

        JSONArray jsonArray = (JSONArray) json.get("results");

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            children.add(jsonObject.get("id").toString());
        }

        return children;
    }
}
