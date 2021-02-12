package com.github;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import junit.framework.Assert;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;


public class _3TestAllHeaders {

    public static final String BASE_URL = "https://api.github.com/";

    Map<String, String> expectedHeaders = Map.of("content-encoding", "gzip",
            "access-control-allow-origin", "*");

    Map<String, Matcher> expectedHeadersWithMatcher = Map.of("cache-control", Matchers.containsString("public"),
            "etag", Matchers.notNullValue());

    @Test(description = "Show how to use all possible headers")
    public void headersValidation() {

        RestAssured.get(BASE_URL)
                .then()

                // convenience headers
                .statusCode(200)
                .statusCode(equalTo(200))
                .statusCode(Matchers.is(200))
                .statusCode(Matchers.anything())
                .statusCode(Matchers.greaterThan(199))
                .statusCode(Matchers.anyOf(equalTo(200), equalTo(202)))

                .statusLine("HTTP/1.1 200 OK")
                .statusLine(Matchers.notNullValue())
                .statusLine(Matchers.not("HTTP/1.1 400 NOT OK"))

                .contentType(ContentType.JSON)
                .contentType("application/json")
                .contentType(Matchers.containsStringIgnoringCase("json"))
                .header("etag", Matchers.not(Matchers.emptyString()))

                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)

                // custom headers
                .header("cache-control", "public, max-age=60, s-maxage=60")
                .header("X-Ratelimit-Limit", Matchers.equalTo("60"))
//                .header("X-Ratelimit-Limit", Matchers.equalTo(60))

                // lambdas allow for a more complex operation, e.g.
                // Comparing two values on a single response in one go: X-Ratelimit-Limit=60 vs. X-Ratelimit-Remaining=59
                // change to lessThanOrEqualTo and see it fail
                .header("X-Ratelimit-Limit", h -> Matchers.equalTo(h.header("X-Ratelimit-Remaining")))

                .headers(expectedHeaders)           // passes with a partial map match. enough to supply an incomplete map.
                .headers(expectedHeadersWithMatcher)

                // or just hard-code the map with expected key-val right into the method
                .headers("content-encoding", "gzip",
                        "access-control-allow-origin", "*",
                        "cache-control", Matchers.containsString("public"));
    }
}

