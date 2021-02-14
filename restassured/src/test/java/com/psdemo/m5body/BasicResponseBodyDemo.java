package com.psdemo.m5body;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;

public class BasicResponseBodyDemo {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void jsonPathReturnsMap() {
        Response response = RestAssured.get(BASE_URL + "rate_limit");

        ResponseBody<?> body = response.getBody();
        ResponseBody body1 = response.body();

        JsonPath jPath = body.jsonPath();
        Map<String, String> map = jPath.get();								// nested maps
        Map<String, String> subMap  = jPath.get("resources");				// nested maps
        Map<String, String> subMap2 = jPath.get("resources.core");		// map
        int value = jPath.get("resources.core.limit");	// value
        int value2 = jPath.get("resources.graphql.remaining");	// value

        System.out.println(map);
        System.out.println(subMap);
        System.out.println(subMap2);
        System.out.println(value);
        System.out.println(value2);

        assertEquals(value, 60);
        assertEquals(value2, 0);
    }

    @Test
    public void castingFailure() {
        JsonPath jPath = RestAssured.get(BASE_URL + "rate_limit").body().jsonPath();

        Map<String, String> isNull = jPath.get("incorrect.path");
        int aMap = jPath.get("resources.core");                 // ClassCastException
        String value = jPath.get("resources.core.limit");       // ClassCastException
    }

    @Test
    public void jsonPathReturnsString() {
        Response response = RestAssured.get(BASE_URL);

        String value = response.body().jsonPath().get("current_user_url");
        System.out.println(value);
    }
}
