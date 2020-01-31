package com.github.statuscodes;

import com.github.AbstractTestClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.github.Constants.BASE_URL;
import static java.net.http.HttpResponse.BodyHandlers.discarding;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class Get401 extends AbstractTestClass {

    @ParameterizedTest
    @ValueSource(strings = {
            "user",
            "authorizations",
            "notifications"})
    void headFailsOnSecureEndpoints(String endpoint) {

        var response = sender.sendGet(BASE_URL + endpoint);

        assertEquals(response.statusCode(), 401);
    }

    // ca. 3 times faster than sync
    @Test
    void asyncHeadFailsOnSecureEndpoints() {

        HttpClient client = HttpClient.newHttpClient();

        var endpoints = List.of("user", "authorizations", "notifications");

        List<HttpRequest> requests = endpoints.stream()
                .map(endpoint -> BASE_URL + endpoint)
                .map(URI::create)
                .map(u -> HttpRequest.newBuilder(u).build())
                .collect(toList());

        List<CompletableFuture<HttpResponse<Void>>> futures = requests.stream()
                .map(request -> client.sendAsync(request, discarding()))
                .collect(toList());

        List<Integer> codes = futures.stream()
                .map(CompletableFuture::join)
                .map(HttpResponse::statusCode)
                .collect(toList());

        assertThat(codes)
                .containsOnly(401);

        /* or all together in a single pipe :)
        List<Integer> codes2  =
                endpoints.stream()
                .map(endpoint -> BASE_URL + endpoint)
                .map(URI::create)                                           // URI -> link
                .map(u -> HttpRequest.newBuilder(u).build())                // Request -> URI -> Link
                .map(request -> client.sendAsync(request, discarding()))    // sendAsync(request), return CompletableFuture
                .map(CompletableFuture::join)                               // force to complete and return the contained value - HttpResponse
                .map(HttpResponse::statusCode)                              // HttResponse -> int code
                .collect(toList());
         */
    }
}