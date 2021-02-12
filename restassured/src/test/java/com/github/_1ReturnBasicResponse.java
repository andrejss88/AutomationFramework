package com.github;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;


public class _1ReturnBasicResponse {

    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void peek() {
        RestAssured.get(BASE_URL).peek();
    }

    @Test
    public void print() {
        String print = RestAssured.get(BASE_URL).print();
        System.out.println("================");
        System.out.println(print);
    }

    @Test
    // no or almost no diff from peek, perhaps some indentation
    // otherwise prints both headers and the body
    public void prettyPeek() {
        RestAssured.get(BASE_URL).prettyPeek();
    }

//    Example output
//    ============================

//    HTTP/1.1 200 OK
//    date: Thu, 01 Oct 2020 10:27:05 GMT
//    server: GitHub.com
//    status: 304 Not Modified
//    cache-control: public, max-age=60, s-maxage=60
//    vary: Accept, Accept-Encoding, Accept, X-Requested-With, Accept-Encoding
//    [...] more headers
//
//    {
//        "current_user_url": "https://api.github.com/user",
//            "current_user_authorizations_html_url": "https://github.com/settings/connections/applications{/client_id}",
//            "authorizations_url": "https://api.github.com/authorizations",
//          [...] more values
//    }

    @Test
    public void quicktest() {
        Response response = RestAssured.get(BASE_URL);

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test
    public void quickHeaderTest() {
        Response response = RestAssured.get(BASE_URL);

        response.getHeaders().getValue("header1");
        response.getHeaders().asList();
        response.getHeaders().size();
        response.getHeaders().hasHeaderWithName("header2");
    }

    @Test
    public void timeInTest() {
        Response response = RestAssured.get(BASE_URL);

        System.out.println(response.getTime());
        System.out.println(response.getTimeIn(TimeUnit.MINUTES));

    }

    @Test
    public void andReturnThenReturn() {
        Response response = RestAssured.get(BASE_URL); // can be further chained with .andReturn(); or thenReturn()  - it is literally the same as per implementation and JavaDoc


        System.out.println("======= Headers =======");
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getContentType(), 200);
        assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
        assertEquals(response.getHeader("server"), "GitHub.com");
        assertEquals(response.getHeader("x-ratelimit-limit"), "60");
        assertEquals(Integer.parseInt(response.getHeader("x-ratelimit-limit")), 60);




        System.out.println(response.getHeaders()); // entire list of key=vale

        System.out.println("======= Time =======");
        System.out.println("The response took this long (in ms): " + response.getTime());                         // default in millis
        assertEquals(response.getTimeIn(TimeUnit.MILLISECONDS), response.getTime(), "The default TimeUnit should be in millis");
        System.out.println(response.getTimeIn(TimeUnit.SECONDS));       // 0 - gets rounded down


        System.out.println("======= Body =======");
        System.out.println(response.getBody()); // io.restassured.internal.RestAssuredResponseImpl@301aa982

        System.out.println("======= Body Pretty Peek =======");
        // prints both headers and body, hm, ok. Because it's actually the same method.
        // Both Response and ResponseBody interfaces refer to the same implementation
        response.getBody().prettyPeek();

        System.out.println("======= Body Pretty Print =======");
        // prints just the body
        response.getBody().prettyPrint();
    }
}

