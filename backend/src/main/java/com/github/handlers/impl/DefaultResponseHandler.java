package com.github.handlers.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

import static java.util.Arrays.stream;

public class DefaultResponseHandler extends AbstractResponseHandler {

    @Override
    public String getHeaderValue(CloseableHttpResponse response, String headerName) {
        Header[] headers = response.getAllHeaders();

        Header matchedHeader = stream(headers)
                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst().orElseThrow(() -> new RuntimeException("Didn't find the header: " + headerName));

        return matchedHeader.getValue();
    }

    @Override
    public <T> T validateSchema(HttpResponse response, Class<T> clazz) throws IOException {

        String jsonFromResponse = getJson(response);

        return new ObjectMapper().readValue(jsonFromResponse, clazz);
    }

    @Override
    public <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz) throws IOException {

        String jsonFromResponse = getJson(response);

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonFromResponse, clazz);
    }

}
