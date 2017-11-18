package com.github.handlers.impl;

import com.github.handlers.ResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class that handles Http Responses
 */

public abstract class AbstractResponseHandler implements ResponseHandler {

    @Override
    public String getProtocolVersion(CloseableHttpResponse response){
        return response.getStatusLine().getProtocolVersion().getProtocol();
    }

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
    public boolean headerIsPresent(CloseableHttpResponse response, String headerName){

        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        return httpHeaders.stream()
               .anyMatch(header -> header.getName().equals(headerName));
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

    protected static String getJson(HttpResponse response) {
        Objects.requireNonNull(response); // Fail fast
        String jsonFromResponse = null;

        try {
            jsonFromResponse = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFromResponse;
    }

}
