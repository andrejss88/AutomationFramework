package com.github;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;


public class ReturnValidatableResponse {

    public static final String BASE_URL = "https://api.github.com/";

    @Test(description = "ValidatableResponse is just a wrapped Response")
    public void then_returnsValidatableResponse() {

        RestAssured.get(BASE_URL)
                // is what returns ValidatableResponse, and we can continue chaining methods
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("current_user_url", equalTo(BASE_URL + "user"));
    }

    @Test(description = "Using, AssertThat, and are Syntactic sugar methods - they just return 'this'." +
            "Meaning you may use them at your discretion, but they don't actually 'do' anything beyond making the code a but more readable ")
    public void syntacticSugar() {
        RestAssured.get(BASE_URL)
                .then()
                .using()                        // from JavaDoc - Syntactic sugar - just returns "this;"
                    .defaultParser(Parser.JSON)
                .assertThat()                   // also Syntactic sugar, otherwise not necessary
                    .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and().assertThat()             // heck, we can chain this endlessly... or not
                .body("current_user_url", equalTo(BASE_URL + "user"));
    }

    @Test // this test fails on purpose
    public void defaultParser() {
        RestAssured.get(BASE_URL)
                .prettyPeek()
                .then()
                .defaultParser(Parser.HTML) // use the wrong parser and RestAssured won't be able to handle unexpected content type
//                .defaultParser(Parser.JSON) // "latest one wins" - this will override the HTML setting above and the test will pass
                .body("current_user_url", equalTo(BASE_URL + "user"));
    }
}

