package com.github;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;

import static io.restassured.config.FailureConfig.*;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class ConfigFactory {

    public static RestAssuredConfig getDefaultConfig() {
        ResponseValidationFailureListener failureListener = (reqSpec, resSpec, response) ->
                System.out.printf("\n We have a failure, response status was %s \n and body contained: \n %s%n", response.statusCode(), response.body().asString());

        return RestAssured.config()
                .redirect(redirectConfig().maxRedirects(1))
                .failureConfig(failureConfig().with().failureListeners(failureListener))
                // add later
//                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL))
                .objectMapperConfig(objectMapperConfig().jackson2ObjectMapperFactory(getDefaultMapper()));
    }

    private static Jackson2ObjectMapperFactory getDefaultMapper() {
        return (type, s) -> {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        };
    }
}
