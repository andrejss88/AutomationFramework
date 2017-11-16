package com.github.handlers.impl;

import com.github.handlers.RequestHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.Map;

import static com.github.factories.ClientFactory.getDefaultClient;

public class DefaultRequestHandler implements RequestHandler {

    protected CloseableHttpClient client = getDefaultClient();

    @Override
    public CloseableHttpResponse sendGet(String url) throws IOException {
        HttpGet httpget = new HttpGet(url);
        return client.execute(httpget);
    }

    @Override
    public CloseableHttpResponse sendGetWithHeaders(String url, Map<String, String> headers) throws IOException {

        HttpGet httpget = new HttpGet(url);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            httpget.setHeader(key, value);
        }

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
