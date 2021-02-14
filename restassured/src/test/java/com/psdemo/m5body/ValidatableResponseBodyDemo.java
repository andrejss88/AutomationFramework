package com.psdemo.m5body;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class ValidatableResponseBodyDemo {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void matcherExample() {

        RestAssured.get(BASE_URL)
                .then()
                // must always be a Matcher
                // path, matcher
                .body("current_user_url", equalTo(BASE_URL + "user"))
                // matcher
                .body(containsString("feeds_url"))
                // matcher, matcher
                .body(containsString("feeds_url"), containsString("current_user_url"));
    }

    @Test
    public void complexBodyExample() {
        RestAssured.get(BASE_URL + "users/andrejs-ps")
                .then()
                .body("url", response -> Matchers.containsString(response.body().jsonPath().get("login")))
                .body("url", response -> Matchers.containsString("andrejs-ps"));
    }

    @DataProvider
    public Object[][] names() {
        return new Object[][]{
                {"andrejs-ps"},
                {"rest-assured"}
        };
    }

    @Test(dataProvider = "names")
    public void complexBodyExampleWithDp(String name) {
        RestAssured.get(BASE_URL + "users/" + name)
                .then()
                .body("url", response -> Matchers.containsString(response.body().jsonPath().get("login")));
    }

}
