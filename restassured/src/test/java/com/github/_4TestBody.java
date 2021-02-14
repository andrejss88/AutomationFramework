package com.github;


import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;


public class _4TestBody {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void bodyValidation() {

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
    public void complexBodyValidation() {
        String user = "andrejs-ps";
        RestAssured.get(BASE_URL + "users/" + user)
                .then()
                .body("login", Matchers.equalTo(user))
                .body("url", response -> Matchers.equalTo(BASE_URL + "users/" + response.path("login")));
    }

    @Test
    public void complexBodyExample() {
        RestAssured.get("https://reqres.in/api/users?page=2")
                .then()
                // IDs are incremented by one
                .body("data.id[0]", response -> Matchers.equalTo((int) response.body().jsonPath().get("data.id[1]") - 1));
    }

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

    @Test(expectedExceptions = AssertionError.class)
    public void failBecauseMultipleItemsMatched() {

        RestAssured.get(BASE_URL + "search/repositories?q=java&per_page=2")
                .then()
                .rootPath("items")
                .body("id", is(63477660))
                .body("name", is("Java"));

//        java.lang.AssertionError: 1 expectation failed.
//        JSON path items.id doesn't match.
//        Expected: is <63477660>           < -- we specify one
//          Actual: [63477660, 46844958]    < -- but many found
    }

    @Test
    public void repeatedItemsInBody() {

        RestAssured.get(BASE_URL + "search/repositories?q=java&per_page=2")
                .then()
//                .body("items.id", is(46844958)) - fail. Expected: X, Actual: [x, y], i.e. found multiple
                .body("items.id[0]", is(63477660))
                .body("items.id[1]", is(46844958));
    }

    // but even when we fetch a single item
    @Test(expectedExceptions = AssertionError.class)
    public void failBecauseWeTryToMatchOneItemWithArrayOfItems() {

        RestAssured.get(BASE_URL + "search/repositories?q=java&per_page=1")
                .then()
                .rootPath("items")
                .body("id", is(63477660))
                .body("name", is("Java"));

//        org.testng.TestException:
//        Expected: is <63477660>   <-- one item
//          Actual: [63477660]      <-- array, as specified in JSON items: []
    }


    @Test
    public void properlyMatchItemContainedInArray() {
        RestAssured.get(BASE_URL + "search/repositories?q=java&per_page=2")
                .then()
                .rootPath("items")
                .body("id", hasItem(63477660))                              // loose match
                .body("id", containsInAnyOrder(63477660, 46844958)); // strict match
    }
}

