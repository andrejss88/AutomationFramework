package com.psdemo.m8misc;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.github.ConfigFactory.getDefaultConfig;
import static org.hamcrest.Matchers.is;

public class _2RequestSpecificationDemo {

    @BeforeSuite
    public void setup() {
        RestAssured.requestSpecification = getDefaultRequestSpec();

        // intentional typo to verify that RequestSpec overrides this
        RestAssured.baseURI = "https://reqres.inn/";
        RestAssured.basePath = "api/userss";
    }

    @Test
    public void testWithRequestSpec() {

        RestAssured.get()
                .then()
                .body("data.id[0]", is(1));
    }

    public static RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder()
                .addHeader("Default Header 1", "Value 1") // e.g. Auth header
                // override RestAssured.(...) constants
                .setBaseUri("https://reqres.in/")
                .setBasePath("api/users") // though could be part of BaseUri
                .setConfig(getDefaultConfig()) // and get the benefit of that config (failure listener)
                .build();
    }
}
