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

class PlainHttpNotRedirected {

    private static HttpResponse<String> response;

    @BeforeAll
    static void fetchResponseFromPlainHttp() throws IOException, InterruptedException {
        var client = newCustomClient()
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();

        var request = newBuilder(URI.create("http://api.github.com/")).build();

        response = client.send(request, BodyHandlers.ofString());
    }

    @Test
    void plainHttpResultsIn301() {

        int actualStatus = response.statusCode();
        // Assert status code
        assertEquals(actualStatus, 301);
    }

    @Test
    void plainHttpGivesRedirectToNewLocation() {

        // Assert we are given the redirect address
        String redirectLocation = response.headers()
                .firstValue("location")
                .orElse("");

        assertEquals(redirectLocation, "https://api.github.com/");
    }

    @Test
    void plainHttpResponseHasNoBody() {
        assertTrue(response.body().isEmpty());
    }


}
