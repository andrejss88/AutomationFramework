package com.github.handlers.impl;

import com.github.handlers.RequestHandler;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public <T extends HttpRequestBase> CloseableHttpResponse sendRequestWithHeaders(Class<T> clazz, String url, Map<String, String> headers) throws IOException {
        CloseableHttpResponse response = null;
        Objects.requireNonNull(headers);
        try {
            HttpRequestBase request = clazz.getConstructor(String.class).newInstance(url);
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    request.setHeader(header.getKey(), header.getValue());
            }
            response = client.execute(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }


    public <T extends HttpRequestBase> CloseableHttpResponse sendRequestWithHeaders(Class<T> clazz, String url, List<BasicHeader> headers) throws IOException {
        CloseableHttpResponse response = null;
        Objects.requireNonNull(headers);
        try {
            HttpRequestBase request = clazz.getConstructor(String.class).newInstance(url);
            for (BasicHeader header : headers) {
                request.setHeader(header.getName(), header.getValue());
            }
            response = client.execute(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
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
