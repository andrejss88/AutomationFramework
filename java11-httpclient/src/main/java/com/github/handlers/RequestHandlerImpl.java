package com.github.handlers;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.*;
import static java.net.http.HttpResponse.BodyHandlers;
import static java.util.Objects.requireNonNull;

public class RequestHandlerImpl implements RequestHandler {

    private final HttpClient client;

    private RequestHandlerImpl(HttpClient client) {
        this.client = client;
    }

    public static RequestHandler newInstance() {

        HttpClient httpClient = newBuilder()
                .followRedirects(Redirect.NORMAL)
                .version(Version.HTTP_2)
                .build();

        return new RequestHandlerImpl(httpClient);
    }

    @Override
    public HttpResponse<String> sendGet(String url) {
        HttpRequest request = preBuildRequest(validate(url))
                .GET()
                .build();

        return handleRequest(request);
    }

    @Override
    public HttpResponse<String> sendHead(String url) {
        HttpRequest request = preBuildRequest(validate(url))
                .method("HEAD", BodyPublishers.noBody())
                .build();

        return handleRequest(request);
    }

    @Override
    public HttpResponse<String> sendOptions(String url) {
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

    private HttpResponse<String> handleRequest(HttpRequest request) {
        try {
            return client.send(request, BodyHandlers.ofString());
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
