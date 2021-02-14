package com.psdemo.m5body;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ValidatableNestedBodyDemo {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void nestedBodyValidation() {
        RestAssured.get(BASE_URL + "rate_limit")
                .then()
                .body("resources.core.limit", equalTo(60)) // here it has to be an int? why?
                .body("resources.core.remaining", lessThanOrEqualTo(60))
                .body("resources.core.reset", notNullValue());
    }

    @Test
    public void nestedBodyValidationWithRoot() {
        RestAssured.get(BASE_URL + "rate_limit")
                .then()
                .rootPath("resources.core")
                .body("limit", equalTo(60)) // here it has to be an int? why?
                .body("remaining", lessThanOrEqualTo(60))
                .body("reset", notNullValue())

                .rootPath("resources.search")
                .body("limit", equalTo(10))
                .body("remaining", lessThanOrEqualTo(10))

                .noRootPath() // reset the path
                .body("resources.graphql.limit", is(0));
    }
}
