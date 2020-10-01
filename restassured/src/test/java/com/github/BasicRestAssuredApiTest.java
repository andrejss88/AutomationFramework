package com.github;


import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class BasicRestAssuredApiTest {

    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void getStatusCodeIs200() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200);
    }


    @Test
    public void headersContainCorrectValues() {

        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                .header("content-type","application/json; charset=utf-8")
                .header("X-Ratelimit-Limit", "60");
    }


    @Test
    public void bodyContainsCorrectValues() {

        RestAssured.get("https://api.github.com/users/andrejs-ps")
                .then()
                .assertThat()
                .body("login", equalTo("andrejs-ps"))
                .body("type", equalTo("User"));
    }
}

