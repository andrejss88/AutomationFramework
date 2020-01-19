package com.github.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.TestException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static com.github.Constants.BASE_API_URL_NO_PROTOCOL;
import static java.lang.String.format;

public class HttpHelper {

    private HttpHelper() {

    }

    public static JSONArray getReposForUser(String user) {

        URI uri;
        JSONArray repos;

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
            throw new IllegalArgumentException("Supplied user is invalid");
        } catch (IOException e) {
            throw new UncheckedIOException("Could not parse HttpResponse", e);
        } catch (JSONException e) {
            throw new RuntimeException("Could not create a JsonArray with extracted Json");
        }
        return Objects.requireNonNull(repos);
    }

    public static boolean valueIsPresent(JSONArray array, String key, String value) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.has(key) && value.equals(obj.optString(key))) {
                return true;
            }
        }
        return false;
    }

    private static HttpResponse executeAndGetResponse(URI uri) throws IOException {

        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            String reason = response.getStatusLine().getReasonPhrase();
            throw new TestException(format("Expected Status 200, but got: '%d' with reason: %s ", statusCode, reason));
        }
        return Objects.requireNonNull(response);
    }
}
