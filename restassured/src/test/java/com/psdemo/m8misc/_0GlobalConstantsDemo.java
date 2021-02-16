package com.psdemo.m8misc;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

public class _0GlobalConstantsDemo {

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "api/users";
//        RestAssured.rootPath = "data"; - if we uncomment - need to remove the "data." prefix from other tets
        RestAssured.authentication = RestAssured.basic("usr", "pwd");
//        RestAssured.requestSpecification - in a separate clip
//        RestAssured.responseSpecification - in a separate clip
//        RestAssured.config = new RestAssuredConfig(); - in a separate clip

    }

    @Test
    public void testOneUsingGlobalConstants() {

        RestAssured.get()
                .then()
                .body("data.id[0]", is(1));
    }

    @Test
    public void testTwoUsingGlobalConstants() {

        RestAssured.get()
                .then()
                .body("data.id[1]", is(2));
    }
}
