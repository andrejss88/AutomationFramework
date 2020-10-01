package com.github;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import static org.hamcrest.Matchers.*;

public class _5Miscellaneous {

    public static final String BASE_URL = "https://api.github.com/";

    @Test(description = "Extract is used after validation to get things out of the response for later usage")
    public void howExtractWorks() {

        ExtractableResponse<Response> er = RestAssured.get(BASE_URL + "rate_limit")
                .then()
                .statusCode(200)
                .extract();

        JsonPath jPath = er.body().jsonPath();

        LinkedHashMap<String, String> map = jPath.get(); // nested LinkedHashMap JSON representation
        System.out.println(map);
        int limitVal = jPath.get("resources.search.limit"); // drill down and get the specific value
        int val = er.body().path("resources.core.limit"); // alternative

        System.out.println(limitVal);
        System.out.println(val);
    }

    // can be packaged into public utility methods
    ResponseSpecification commonResponseSpec =
            new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.JSON)
                    // etc.
                    .build();

    @Test(description = "Test without duplication thanks to spec()")
    public void testWithSpecOne() {

        RestAssured.get(BASE_URL)
                .then()
                .spec(commonResponseSpec);
        // + custom verifs relevant to just this test
    }

    @Test(description = "Another test without duplication thanks to spec()")
    public void testWithSpecTwo() {

        RestAssured.get(BASE_URL + "rate_limit")
                .then()
                .spec(commonResponseSpec);
        // + custom verifs relevant to just this test
    }

    RequestSpecification requestSpec = new RequestSpecBuilder()
                            .addHeader("Random Header", "Some Value")
                            .setBaseUri("https://reqres.in/api/users")
                            .build();

    @Test
    public void testWithRequestSpec() {
        RestAssured.requestSpecification = requestSpec;

        RestAssured.get()
                .then()
                .body("data.id[0]", is(1));
    }

    @Test
    public void objectMappingRelyingOnClassAnnotation() {

        User user = RestAssured.get(BASE_URL + "andrejss88")
                // 1) got to have 'jackson-databind' dependency
                // 2) Your class fields must be public
                .as(User.class);

        Assert.assertEquals(user.login, "andrejss88");
        Assert.assertEquals(user.id, 11834443);
    }

    @Test
    public void objectMappingUsingSpecifiedMapper() {

        //  Doesn't compile - Jackson's ObjectMapper != RestAssured's Mapper
        //  ObjectMapper objectMapper = new ObjectMapper();
        //  objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        AnotherUser user = RestAssured.get("https://api.github.com/users/andrejss88")
                .as(AnotherUser.class, getMapperLambda());

        Assert.assertEquals(user.login, "andrejss88");
        Assert.assertEquals(user.id, 11834443);
    }

    private Jackson2Mapper getMapper() {
        return new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
            @Override
            public ObjectMapper create(Type type, String s) {
                ObjectMapper om = new ObjectMapper().findAndRegisterModules();
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return om;
            }
        });
    }

    private Jackson2Mapper getMapperLambda() {
        return new Jackson2Mapper((type, s) -> {
            ObjectMapper om = new ObjectMapper().findAndRegisterModules();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        });
    }
}
