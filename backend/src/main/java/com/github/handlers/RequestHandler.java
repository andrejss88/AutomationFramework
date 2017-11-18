package com.github.handlers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;

import java.io.IOException;
import java.util.Map;

public interface RequestHandler {

    RequestBuilder sendCustomRequest(String method) throws  IOException;

    CloseableHttpResponse sendGet(String url) throws IOException;

    CloseableHttpResponse sendGetWithHeaders(String url, Map<String, String> headers) throws IOException;

    CloseableHttpResponse sendHead(String url) throws IOException;

    CloseableHttpResponse sendOptions(String url) throws IOException;

    void close() throws IOException;
}
