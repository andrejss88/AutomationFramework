package com.github.statuscodes;

import com.github.AbstractTestClass;
import org.junit.jupiter.api.Test;

import static com.github.Constants.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;


class Get200 extends AbstractTestClass {
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