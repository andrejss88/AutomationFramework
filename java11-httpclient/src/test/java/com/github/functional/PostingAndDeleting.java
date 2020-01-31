package com.github.functional;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;

import static com.github.Constants.BASE_URL;
import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpRequest.newBuilder;
import static java.net.http.HttpResponse.BodyHandlers;

public class PostingAndDeleting {

    private static HttpClient client = HttpClient.newHttpClient();
    private static final String TOKEN = "generate your own token on Github> Settings > Developer Settings > Personal access tokens";

    @Test
    void postWithoutCredentialsIsUnauthorized() throws IOException, InterruptedException {

        var request = newBuilder()
                .POST(BodyPublishers.ofString("this is a text"))
                .uri(URI.create(BASE_URL + "user/repos"))
                .header("Content-Type", "application/json")
                .build();

        var response = client.send(request, BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test
    void postWithCredentialsAllowed() throws IOException, InterruptedException {

        String json = "{\"name\": \"deleteme\"}";

        var request = newBuilder(URI.create(BASE_URL + "user/repos"))
                .header("Authorization", "token " + TOKEN)
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();

        var response = client.send(request, BodyHandlers.discarding());

        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test
    void deleteWithCredentialsAllowed() throws IOException, InterruptedException {

        var request = newBuilder(URI.create(BASE_URL + "repos/andrejss88/deleteme"))
                .headers("Authorization", "token " + TOKEN)
                .DELETE()
                .build();

        var response = client.send(request, BodyHandlers.discarding());

        Assert.assertEquals(response.statusCode(), 204);
    }
}
