package com.psdemo.m6objectmapping;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.AnotherUser;
import com.github.User;
import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
//import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

import static org.testng.Assert.*;

public class ObjectMappingDemo {

    public static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @Test
    public void objectMappingRelyingOnClassAnnotation() {

        User user = RestAssured.get(BASE_URL)
                // 1) got to have 'jackson-databind' dependency
                // 2) Your class fields must be public
                .as(User.class);

        assertEquals(user.login, "rest-assured");
        assertEquals(user.id, 19369327);
        assertEquals(user.publicRepos, 2);
    }

    @Test
    public void objectMappingRelyingOnMapperType() {

        User user = RestAssured.get(BASE_URL)
                // 1) got to have 'jackson-databind' dependency
                // 2) Your class fields must be public
                .as(User.class, ObjectMapperType.JACKSON_2);

        assertEquals(user.login, "rest-assured");
        assertEquals(user.id, 19369327);
    }

    @Test
    public void objectMappingUsingSpecifiedMapper() {

//          Doesn't compile - Jackson's ObjectMapper != RestAssured's Mapper
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        io.restassured.mapper.ObjectMapper mapper = new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
            @Override
            public ObjectMapper create(Type type, String s) {
                ObjectMapper om = new ObjectMapper();
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return om;
            }
        });

        AnotherUser user = RestAssured.get(BASE_URL)
                .as(AnotherUser.class, mapper);

        assertEquals(user.login, "rest-assured");
        assertEquals(user.id, 19369327);
    }

    private Jackson2Mapper getMapper() {
        return new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
            @Override
            public ObjectMapper create(Type type, String s) {
                ObjectMapper om = new ObjectMapper();
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return om;
            }
        });
    }

    private Jackson2Mapper getMapperLambda() {
        return new Jackson2Mapper((type, s) -> {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        });
    }
}
