package com.bklymiuk.Tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeTest;


public abstract class TestConfig {

    @BeforeTest(description = "Setup Request and Response specifications")
    public static void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();


        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectContentType("application/json")
                .expectStatusCode(200)
                .build();
    }
}
