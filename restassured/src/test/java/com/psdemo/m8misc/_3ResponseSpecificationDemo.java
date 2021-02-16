package com.psdemo.m8misc;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _3ResponseSpecificationDemo {

    public static final String BASE_URL = "https://api.github.com/";

    @BeforeClass
    public void setup() {
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200) // change this and see tests fail
                .build();
    }

    @Test
    public void testWithSpecFromConstant() {
        // just this is enough if we verify stuff with spec
        RestAssured.get(BASE_URL);
//        RestAssured.responseSpecification = null; // must cleanup, or custom spec won't override
    }

    // can be packaged into public utility methods
    ResponseSpecification commonResponseSpec =
            new ResponseSpecBuilder()
                    .expectStatusCode(404) // change this and see tests fail
                    .expectContentType(ContentType.JSON)
                    // etc.
                    .build();

    @Test(description = "Test without duplication thanks to spec()")
    public void testWithSpecOne() {

        RestAssured.get(BASE_URL + "non/existing/url")
                .then()
                .spec(commonResponseSpec);
        // + custom verifs relevant to just this test
    }

    @Test(description = "Another test without duplication thanks to spec()")
    public void testWithSpecTwo() {

        RestAssured.get(BASE_URL + "non/existing/url")
                .then()
                .spec(commonResponseSpec);
        // + custom verifs relevant to just this test
    }
}
