package com.psdemo.m8misc;

import groovy.util.logging.Log;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.Test;

public class _4LoggingDemo {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    @Test
    public void test() {
        RestAssured
                .given()
//                    .log().all()
                    // .log().headers()
                    // .log().body()
                    // more logging of Request
                .when()
                    .get(BASE_URL)
                .then()
                // no condition
                    .log().headers()
                    .log().status()
                // with condition
                    .log().ifValidationFails(LogDetail.HEADERS) // doesn't have overriding power, repeats the operation
                    .statusCode(201);

    }
}
