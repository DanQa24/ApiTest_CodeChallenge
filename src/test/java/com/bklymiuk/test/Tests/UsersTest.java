package com.bklymiuk.test.Tests;

import com.bklymiuk.test.Blog.Endpoint;
import com.bklymiuk.test.Utils.RequestBuilder;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;


public class UsersTest extends TestConfig implements RequestBuilder {


    @Test(description = "GET user by valid username")
    public void getUserByValidUsername() {
        String userName = "Delphine";

        RequestBuilder.getResourceBy("username", userName, Endpoint.USERS)
                .then()
                .assertThat()
                .body("size()", is(1))
                .body("[0].username", is(userName));
    }


    @Test(description = "GET user by invalid username")
    public void getUserByInvalidUsername() {
        String invalidUsername = "Non existing username";

        RequestBuilder.getResourceBy("username", invalidUsername, Endpoint.USERS)
                .then()
                .assertThat()
                .body("size()", is(0));
    }


    @Test(description = "GET all posts by valid User Id")
    public void getPostsByUserId() {
        int userId = 5;

        RequestBuilder.getResourceBy("userId", userId, Endpoint.POSTS)
                .then()
                .assertThat()
                .body("[0].userId", is(userId));
    }


    @Test(description = "GET all comments under posts of specific user, validate email addresses format")
    public void verifyEmailsHaveValidFormat() {
        String userName = "Delphine";
        String validFormat = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";


        int userId =
                RequestBuilder.getResourceBy("username", userName, Endpoint.USERS)
                .then()
                        .body("size()", is(1), "[0].username", is(userName))
                        .extract()
                        .path("[0].id");

        ArrayList<Integer> postIds =
                RequestBuilder.getResourceBy("userId", userId, Endpoint.POSTS)
                        .then()
                        .extract()
                        .path("id");

        RequestBuilder.getResourceBy("postId", postIds, Endpoint.COMMENTS)
                .then()
                .assertThat()
                .body("postId", everyItem(in(postIds)))
                .body("email", everyItem(matchesPattern(validFormat)));
    }

}

