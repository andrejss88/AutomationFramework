package com.psdemo.m4headers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.number.OrderingComparison;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class _2ValidatableResponseDemo {

    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void basicValidatableExample() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
//                .header("x-ratelimit-limit", 60) - won't compile
                .header("x-ratelimit-limit", "60");
    }

    @Test
    public void syntacticSugar() {
        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                    .statusCode(200)
                .and()
                    .contentType(ContentType.JSON)
                .and()
                .assertThat()
                    .header("x-ratelimit-limit", "60");
    }

    @Test
    public void simpleHamcrestMatchers() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .statusCode(Matchers.lessThan(300))
                .header("cache-control", Matchers.containsStringIgnoringCase("max-age=60"))
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
                .header("etag", Matchers.notNullValue())
                .header("etag", Matchers.not(Matchers.emptyString()));
    }

    @Test
    public void complexHamcrestMatchers() {
        RestAssured.get(BASE_URL)
                .then()

                .header("x-ratelimit-limit", Integer::parseInt, Matchers.equalTo(60))

                .header("Date", date -> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME), OrderingComparison.comparesEqualTo(LocalDate.now()))

                .header("X-Ratelimit-Limit", response -> Matchers.greaterThanOrEqualTo(response.header("X-Ratelimit-Remaining")));

    }

    Map<String, String> expectedHeaders = Map.of("content-encoding", "gzip",
            "access-control-allow-origin", "*");

    Map<String, Matcher<?>> expectedHeadersWithMatcher = Map.of("cache-control", Matchers.containsString("public"),
            "etag", Matchers.notNullValue());

    @Test
    public void usingMapsToTestHeaders() {
        RestAssured.get(BASE_URL)
                .then()
                // or just hard-code the map with expected key-val right into the method
                .headers("content-encoding", "gzip",
                        "access-control-allow-origin", "*",
                        "cache-control", Matchers.contains("public"))

                .headers(expectedHeaders)           // passes with a partial map match. enough to supply an incomplete map.
                .headers(expectedHeadersWithMatcher);
    }
}
