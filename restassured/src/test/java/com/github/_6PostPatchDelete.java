package com.github;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class _6PostPatchDelete {

    public static final String BASE_URL = "https://api.github.com/";
    public static final String TOKEN = "your token here";

    @Test(description = "Create a repo")
    public void postTest() {
        given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme\"}")
                .post("https://api.github.com/user/repos")
                .then()
                .statusCode(201); // 401 if no token
    }

    @Test(dependsOnMethods = "postTest", description = "Update Repo name")
    public void patchTest() {
        given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme-patched\"}")
                .patch(BASE_URL + "repos/andrejss88/deleteme")
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "patchTest", description = "Delete the repo")
    public void deleteTest() {
        given()
                .header("Authorization", "token " + TOKEN)
                .delete(BASE_URL + "repos/andrejss88/deleteme-patched")
                .then()
                .statusCode(204); // 401 if no token, 404 if repo does not exist
    }

    @Test
    public void differentAuthMethods () {
//        given()
//                .auth().oauth()
//                .auth().oauth2()
//                .auth().basic()
//                .auth().certificate()
//                .auth().ntlm()
//                .auth().digest()
//                .auth().form()
    }
}
