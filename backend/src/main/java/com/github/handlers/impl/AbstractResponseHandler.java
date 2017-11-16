package com.github.handlers.impl;

import com.github.handlers.ResponseHandler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.TestException;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

/**
 * Class that handles Http Responses
 */

public abstract class AbstractResponseHandler implements ResponseHandler {
    @Override
    public int getStatusCode(CloseableHttpResponse response){
        return response.getStatusLine().getStatusCode();
    }

    @Override
    public String getMimeType(CloseableHttpResponse response) {
        return ContentType.getOrDefault(response.getEntity()).getMimeType();
    }

    @Override
    public String getCharSet(CloseableHttpResponse response) {
        return ContentType.getOrDefault(response.getEntity()).getCharset().toString();
    }

    @Override
    public void closeResponse(CloseableHttpResponse response) throws IOException {
        if (response == null) {
            return;
        }

        try {
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                entity.getContent().close();
            }
        } finally {
            response.close();
        }
    }

    static String getJson(HttpResponse response) {
        Objects.requireNonNull(response); // Fail fast
        String jsonFromResponse = null;

        try {
            jsonFromResponse = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFromResponse;
    }

    public static HttpResponse executeAndGetResponse(URI uri) throws IOException {

        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            String reason = response.getStatusLine().getReasonPhrase();
            throw new TestException(String.format("Expected Status 200, but got: '%d' with reason: %s ", statusCode, reason));
        }
        return Objects.requireNonNull(response);
    }
}
