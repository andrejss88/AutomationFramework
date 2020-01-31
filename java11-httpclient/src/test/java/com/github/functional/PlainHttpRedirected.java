package com.github.functional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static com.github.handlers.RequestHandlerImpl.newCustomClient;
import static java.net.http.HttpRequest.newBuilder;
import static java.net.http.HttpResponse.BodyHandlers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlainHttpRedirected {

    private static HttpResponse<String> prevResponse;

    @BeforeAll
    static void fetchResponseFromPlainHttp() throws IOException, InterruptedException {
        var client = newCustomClient()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        var request = newBuilder(URI.create("http://api.github.com/")).build();

        prevResponse = client.send(request, BodyHandlers.ofString())
                .previousResponse()
                .orElseThrow();
    }

    @Test
    void prevResponseContainsPlainHttp() {
        assertEquals(prevResponse.uri().toString(), "http://api.github.com/");
    }

    @Test
    void prevResponseCodeIs301() {
        assertEquals(prevResponse.statusCode(), 301);
    }

    @Test
    void plainHttpRedirectedOnlyOnce() {
        // If the prev response of the prev response is empty, then we were redirected only once
        // if we were redirected multiple times, then we'd a longer chain of nested previous responses
        assertTrue(prevResponse.previousResponse().isEmpty());
    }
}
