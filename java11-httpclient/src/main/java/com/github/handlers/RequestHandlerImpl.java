package com.github.handlers;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.*;
import static java.util.Objects.requireNonNull;

public class RequestHandlerImpl<T> implements RequestHandler {

    private final HttpClient client;
    private HttpResponse.BodyHandler<T> handler;

    private RequestHandlerImpl(HttpClient client, HttpResponse.BodyHandler<T> handler) {
        this.client = client;
        this.handler = handler;
    }

    public static Builder newCustomClient() {
        return newBuilder();
    }

    public static <T> RequestHandler<T> newInstance() {

        HttpClient httpClient = newBuilder()
                .version(Version.HTTP_2)
                .build();

        return new RequestHandlerImpl(httpClient, HttpResponse.BodyHandlers.ofString());
    }

    public static <T> RequestHandler<T> newInstance(HttpResponse.BodyHandler<T> handler) {
        HttpClient httpClient = newBuilder()
                .followRedirects(Redirect.NORMAL)
                .version(Version.HTTP_2)
                .build();

        return new RequestHandlerImpl(httpClient, handler);
    }

    @Override
    public HttpResponse<T> sendGet(String url) {
        HttpRequest request = preBuildRequest(validate(url))
                .GET()
                .build();

        return handleRequest(request);
    }

    @Override
    public HttpResponse<T> sendHead(String url) {
        HttpRequest request = preBuildRequest(validate(url))
                .method("HEAD", BodyPublishers.noBody())
                .build();

        return handleRequest(request);
    }

    @Override
    public HttpResponse<T> sendOptions(String url) {
        HttpRequest request = preBuildRequest(validate(url))
                .method("OPTIONS", BodyPublishers.noBody())
                .build();

        return handleRequest(request);
    }

    /**
     * Prebuild a Request with fields common to all requests
     *
     * @return a partially built Request to be completed by .build()
     */
    private HttpRequest.Builder preBuildRequest(String url) {
        return HttpRequest.newBuilder(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot");
    }

    private HttpResponse<T> handleRequest(HttpRequest request) {
        try {
            return client.send(request, handler);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Could not execute request");
        }
    }

    private static String validate(String baseUrl) {
        requireNonNull(baseUrl);

        // a more robust and generic verification is required, perhaps that of Apache Commons UrlValidator
        if (!baseUrl.contains("http") || !baseUrl.contains("//")) {
            throw new IllegalArgumentException(String.format("Invalid Base URL passed: %s. " +
                    "Expected format is http(s)://someapi.com", baseUrl));
        }
        return baseUrl;
    }
}
