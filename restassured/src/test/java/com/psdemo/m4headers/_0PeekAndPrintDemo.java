package com.psdemo.m4headers;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class _0PeekAndPrintDemo {

    public static final String BASE_URL = "https://api.github.com";

    @Test
    // headers printed in Key=Value format
    // body as a string on a single line (not formatted)
    public void peek() {
        RestAssured.get(BASE_URL).peek();
    }

    @Test
    // headers: no difference from peek
    // body: with indentation, readable
    public void prettyPeek() {
        RestAssured.get(BASE_URL).prettyPeek();
    }

    @Test
    // prints to console too, but returns a string
    public void print() {
        String response = RestAssured.get(BASE_URL).print();
        addToReport(response);
    }

    @Test
    public void prettyPrint() {
        String response = RestAssured.get(BASE_URL).prettyPrint();
        addToReport(response);
    }

    private void addToReport(String response) {
        // impl
    }


}
