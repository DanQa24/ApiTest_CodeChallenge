package com.bklymiuk.test.Utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public interface RequestBuilder {

     static <T> Response getResourceBy(String byParam, T value, String endpoint) {
        return
                given()
                        .param(byParam, value)
                        .when()
                        .get(endpoint)
                        .then()
                        .extract()
                        .response();
    }
}
