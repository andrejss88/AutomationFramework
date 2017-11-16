package com.github.handlers.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class OldResponseHandler extends AbstractResponseHandler {

    @Override
    public String getHeaderValue(CloseableHttpResponse response, String headerName) {
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        String returnHeader = "";
        for (Header header : httpHeaders) {
            if(headerName.equalsIgnoreCase(header.getName())){
                returnHeader = header.getValue();
            }
        }

        if(returnHeader.isEmpty()){
            throw new RuntimeException("Didn't find the header: " + headerName);
        }
        return returnHeader;
    }

    @Override
    public  <T> T validateSchema(HttpResponse response, Class<T> clazz) throws IOException {

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
