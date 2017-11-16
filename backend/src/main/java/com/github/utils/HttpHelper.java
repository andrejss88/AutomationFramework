package com.github.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static com.github.Constants.BASE_API_URL_NO_PROTOCOL;
import static com.github.handlers.impl.AbstractResponseHandler.executeAndGetResponse;

public class HttpHelper {

    private HttpHelper(){

    }

    public static JSONArray getReposForUser(String user) {

        URI uri;
        JSONArray repos = null;

        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost(BASE_API_URL_NO_PROTOCOL)
                    .setPath("users/" + user + "/repos")
                    .build();

            HttpResponse response = executeAndGetResponse(uri);

            String json = EntityUtils.toString(response.getEntity());

            repos = new JSONArray(json);

        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Supplied user is invalid");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(repos);
    }

    public static boolean valueIsPresent(JSONArray array, String key, String value) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if(obj.has(key)){
                if(value.equals(obj.optString(key))){
                    return true;
                }
            }
        }
        return false;
    }
}
