package com.psdemo.m8misc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.AnotherUser;
import com.github.ConfigFactory;
import io.restassured.RestAssured;
import io.restassured.config.*;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import static com.github.ConfigFactory.*;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static io.restassured.config.MatcherConfig.*;
import static io.restassured.config.ObjectMapperConfig.*;
import static io.restassured.config.RedirectConfig.*;
import static org.testng.Assert.assertEquals;

public class _1ConfigDemo {

    String BASE_URL = "https://api.github.com/";

    @Test
    public void maxRedirectsZeroTestFails() {
        RestAssured.config = RestAssured.config()
                .redirect(redirectConfig().followRedirects(true).maxRedirects(0));

        RestAssured.get(BASE_URL + "repos/twitter/bootstrap")
                .then()
                .statusCode(Matchers.equalTo(200));
    }

    @Test
    public void failureConfigExample() {
        ResponseValidationFailureListener failureListener
                = (reqSpec, resSpec, response) ->
                System.out.printf("\n We have a failure, response status was '%s' \n and the body contained: \n %s%n", response.statusCode(), response.body().asPrettyString());

        RestAssured.config = RestAssured.config().failureConfig(FailureConfig.failureConfig().failureListeners(failureListener));

        RestAssured.get(BASE_URL + "users/andrejs-ps")
                .then()
                .body("some.path", Matchers.equalTo("a thing"));
    }

    @Test
    public void mapperConfigExample() {
        RestAssured.config = RestAssured.config().objectMapperConfig(objectMapperConfig().jackson2ObjectMapperFactory(
                        (type, s) -> {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        }));

        AnotherUser user = RestAssured.get("https://api.github.com/users/rest-assured")
                .as(AnotherUser.class);

        assertEquals(user.login, "rest-assured");
        assertEquals(user.id, 19369327);
    }

    @BeforeSuite
    void setup() {
        RestAssured.config = getDefaultConfig();
    }

    @Test
    public void cleanTestWithHiddenConfig() {
        AnotherUser user = RestAssured.get("https://api.github.com/users/rest-assured")
                .as(AnotherUser.class);

        assertEquals(user.login, "rest-assured");
        assertEquals(user.id, 19369327);
    }
}
