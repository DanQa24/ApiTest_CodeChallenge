package com.bklymiuk.Tests;

import com.bklymiuk.Blog.Endpoint;
import com.bklymiuk.Utils.RequestBuilder;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class PostsTest {

    @Test(description = "Validate the structure of each Post in response")
    @Description("Verify that all posts have UserId, Id, Title and Body")
    public void verifyPostsBody() {
        RequestBuilder.getResource(Endpoint.POSTS)
                .then()
                .assertThat()
                .body("$", everyItem(hasKey("userId")))
                .body("$", everyItem(hasKey("id")))
                .body("$", everyItem(hasKey("title")))
                .body("$", everyItem(hasKey("body")));
    }


    @Test(description = "GET posts by valid User Id")
    @Description("Find all posts of valid user, verify only posts of that user were returned")
    public void getPostsByValidUserId() {
        int userId = 5;

        RequestBuilder.getResource("userId", userId, Endpoint.POSTS)
                .then()
                .assertThat()
                .body("userId", everyItem(is(userId)));
    }


    @Test(description = "GET all posts by invalid User Id")
    @Description("Try to get posts for invalid user, verify no post was returned")
    public void getPostsByInvalidUserId() {
        int userId = 23;

        RequestBuilder.getResource("userId", userId, Endpoint.POSTS)
                .then()
                .assertThat() // 404 would be expected here as resource doesn't exist
                .body("size()", is(0));
    }
}
