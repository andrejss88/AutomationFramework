package com.psdemo.m5body;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class ValidatableResponseRepeatingItemsDemo {

    public static final String BASE_URL = "https://reqres.in/api/users?page=1";

    @Test
    public void repeatingItems() {
        RestAssured.get(BASE_URL)
                .then()
// 1) Start with failure
//                Fail. Expected: X, Actual: [x, y, z], i.e. found multiple
                .body("data.id", Matchers.equalTo(1))

                // 2) How to select elements - strict verification
                .body("data.id[0]", Matchers.equalTo(1))
                .body("data.id[1]", Matchers.equalTo(2))

                .body("data.first_name[2]", Matchers.equalTo("Emma"))
                .body("data.first_name[3]", Matchers.equalTo("Eve"))

                // 3) Strict check of the entire array
//                fail, must specify all names - [George, Janet, Emma, Eve, Charles, Tracey]
                .body("data.first_name", Matchers.contains("Eve", "Emma"))
                .body("data.first_name", Matchers.containsInAnyOrder("Eve", "Emma"))

                // 4) Loose checking
                .body("data.first_name", Matchers.hasItem("Emma"))
                .body("data.first_name", Matchers.hasItems("Emma","Eve"))
                .body("data.first_name", Matchers.hasItems("Eve", "Emma"))


                // 5) Nested Matchers - feature of Hamcrest, not RestAssured
                .body("data.first_name", Matchers.hasItem(Matchers.endsWith("ma")));
    }
}
