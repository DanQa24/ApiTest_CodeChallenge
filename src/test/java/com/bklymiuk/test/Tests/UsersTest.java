package com.bklymiuk.test.Tests;

import com.bklymiuk.test.Blog.Endpoint;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class UsersTest extends TestConfig {

    @Test(description = "Verify that User Id is returned when search by valid username")
    public void getUserIdByValidUsername() {
        String validUsername = "Delphine";

        given()
                .param("username", validUsername)
        .when()
                .log().method()
                .log().params()
                .get(Endpoint.USERS)
        .then()
                .log().status()
                .log().body()
                .assertThat()
                .statusCode(200)
                .body("size()", is(1), "[0].username", is(validUsername));
    }


    @Test(description = "Expect Status Code 404 when try to get non existing user")
    public void getUserIdByInvalidUsername() {
        String invalidUser = "Non existing username";

        given()
                .param("username", invalidUser)
        .when()
                .log().method()
                .log().params()
                .get(Endpoint.USERS)
        .then()
                .log().status()
                .assertThat()
                .statusCode(404);
    }


    @Test(description = "Verify email format is valid in comments under all posts of the user")
    public void verifyEmailsHaveValidFormat() {
        String username = "Delphine";
        String validFormat = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        int userId =
                given()
                        .param("username", username)
                .when()
                        .get(Endpoint.USERS)
                .then()
                        .extract()
                        .path("[0].id");

        ArrayList<Integer> postIds =
                given()
                        .param("userId", userId)
                .when()
                        .get(Endpoint.POSTS)
                .then()
                        .extract()
                        .path("id");

        given()
                .param("postId", postIds)
        .when()
                .get(Endpoint.COMMENTS)
        .then()
                .assertThat()
                .body("email",
                        everyItem(matchesPattern(validFormat)));
    }
}

