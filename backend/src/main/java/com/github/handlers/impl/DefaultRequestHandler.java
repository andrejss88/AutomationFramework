package com.github.handlers.impl;

import com.github.handlers.RequestHandler;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

import static com.github.factories.ClientFactory.getDefaultClient;

public class DefaultRequestHandler implements RequestHandler {

    protected CloseableHttpClient client = getDefaultClient();

    @Override
    public RequestBuilder buildCustomRequest(String method) throws IOException {
        return RequestBuilder.create(method);
    }

    @Override
    public CloseableHttpResponse send(HttpUriRequest request) throws IOException {
        return client.execute(request);
    }

    @Override
    public CloseableHttpResponse sendGet(String url) throws IOException {
        HttpGet httpget = new HttpGet(url);
        return client.execute(httpget);
    }

    @Override
    public CloseableHttpResponse sendHead(String url) throws IOException {
        HttpHead httpHead = new HttpHead(url);
        return client.execute(httpHead);
    }

    @Override
    public CloseableHttpResponse sendOptions(String url) throws IOException {
        HttpOptions httpOptions = new HttpOptions(url);
        return client.execute(httpOptions);
    }

    @Override
    public void close() throws IOException {
        client.close();
    }
}
