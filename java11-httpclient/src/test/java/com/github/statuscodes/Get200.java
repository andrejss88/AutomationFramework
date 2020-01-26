package com.github.statuscodes;

import com.github.handlers.RequestHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.handlers.RequestHandlerImpl.newInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;


class Get200 {

    private RequestHandler sender;
    private static final String BASE_URL = "https://api.github.com/";

    @BeforeEach
    void setupClient() {
        sender = newInstance();
    }

    @Test
    void baseUrlRequestReturns200() {

        var response = sender.sendGet(BASE_URL);

        assertEquals(response.statusCode(), 200);
    }

    @Test
    void searchRequestReturns200() {

        var response = sender.sendGet(BASE_URL + "search/repositories?q=java");

        assertEquals(response.statusCode(), 200);
    }

    @Test
    void headAllowedOnResourcesNotRequiringAuthorizations() {

        var response = sender.sendHead(BASE_URL + "search/repositories?q=java");

        assertEquals(response.statusCode(), 200);
    }
}




