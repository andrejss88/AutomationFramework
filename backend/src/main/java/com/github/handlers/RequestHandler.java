package com.github.handlers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import java.io.IOException;

public interface RequestHandler {

    RequestBuilder buildCustomRequest(String method) throws  IOException;

    /**
     * Use after having built a custom request
     * @param request: a request built with 'buildCustomRequest'
     * @return: response
     */
    CloseableHttpResponse send(HttpUriRequest request) throws  IOException;

    CloseableHttpResponse sendGet(String url) throws IOException;

    CloseableHttpResponse sendHead(String url) throws IOException;

    CloseableHttpResponse sendOptions(String url) throws IOException;

    void close() throws IOException;
}
