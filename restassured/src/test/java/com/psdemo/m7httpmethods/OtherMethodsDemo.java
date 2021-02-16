package com.psdemo.m7httpmethods;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OtherMethodsDemo {

    public static final String BASE_URL = "https://api.github.com/";
    public static final String TOKEN = "your token here";

    @Test(description = "Create a repo")
    public void postTest() {

        RestAssured.given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme\"}")
        .when() // syntactic sugar
                .post("https://api.github.com/user/repos")
        .then()
                .statusCode(201); // 401 if no valid token
    }

    @Test(dependsOnMethods = "postTest", description = "Update Repo name")
    public void patchTest() {
        given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme-patched\"}")
        .when()
                .patch(BASE_URL + "repos/andrejs-ps/deleteme")
        .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "patchTest", description = "Delete the repo")
    public void deleteTest() {
        given()
                .header("Authorization", "token " + TOKEN)
        .when()
                .delete(BASE_URL + "repos/andrejs-ps/deleteme-patched")
        .then()
                .statusCode(204); // 401 if no valid token, 404 if repo does not exist
    }
}
