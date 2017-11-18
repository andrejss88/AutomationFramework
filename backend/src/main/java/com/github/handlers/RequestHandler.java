package com.github.handlers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface RequestHandler {

    /**
     * For complete flexibility provided by the RequestBuilder
     */
    RequestBuilder buildCustomRequest(String method) throws  IOException;

    /**
     * Use after having built a custom request with buildCustomRequest
     * @param request: a request built with 'buildCustomRequest'
     */
    CloseableHttpResponse send(HttpUriRequest request) throws  IOException;

    /**
     * Saves the pain to create methods "sendGetWithHeaders", "sendHeadWithHeaders", etc.
     */
    <T extends HttpRequestBase> CloseableHttpResponse sendRequestWithHeaders(Class<T> clazz, String url, Map<String, String> headers) throws IOException;

    <T extends HttpRequestBase> CloseableHttpResponse sendRequestWithHeaders(Class<T> clazz, String url, List<BasicHeader> headers) throws IOException;

    CloseableHttpResponse sendGet(String url) throws IOException;

    CloseableHttpResponse sendHead(String url) throws IOException;

    CloseableHttpResponse sendOptions(String url) throws IOException;

    void close() throws IOException;
}
