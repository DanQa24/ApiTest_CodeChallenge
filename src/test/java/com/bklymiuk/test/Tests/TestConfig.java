package com.bklymiuk.test.Tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeTest;


public class TestConfig {

    @BeforeTest(description = "Setup Request and Response specifications")
    public static void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();


        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectContentType("application/json")
                .build();
    }
}
