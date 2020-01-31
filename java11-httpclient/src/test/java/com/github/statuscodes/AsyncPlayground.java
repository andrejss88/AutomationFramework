package com.github.statuscodes;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static java.net.http.HttpResponse.BodyHandlers;
import static java.util.stream.Collectors.joining;

class AsyncPlayground {

    @Test
    void printAllDetails() {

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .GET()
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .uri(URI.create("https://api.github.com/"))
                .build();

        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, BodyHandlers.ofString());

        Function<HttpResponse<String>, String> extractBody = HttpResponse::body;
        Function<HttpResponse<String>, HttpHeaders> extractHeaders = HttpResponse::headers;
        Function<HttpResponse<String>, HttpRequest> extractRequest = HttpResponse::request;

        future.thenApply(extractBody)
                .thenApply(req -> {
                    System.out.println("Response body is: ");
                    System.out.println(req);
                    return req;
                });

        future.thenApply(extractHeaders)
                .thenApply(headers -> {
                    System.out.println("Response headers are: ");
                    prettyPrintMap(headers.map());
                    return headers;
                });

        future.thenApply(extractRequest)
                .thenApply(req -> {
                    System.out.println("Request Sent: ");
                    System.out.println(req);
                    System.out.println("Request Headers were: ");
                    prettyPrintMap(req.headers().map());
                    return req;
                });

        future.join(); // block until CompletableFuture is complete
    }

    private static void prettyPrintMap(Map<String, List<String>> req) {
        String content = req.entrySet()
                .stream()
                .map(e -> e.getKey() + "=\"" + e.getValue() + "\"")
                .collect(joining("  \n  "));

        System.out.println(content);
    }
}
