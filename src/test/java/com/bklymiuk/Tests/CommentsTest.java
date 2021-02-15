package com.bklymiuk.Tests;

import com.bklymiuk.Blog.Endpoint;
import com.bklymiuk.Utils.RequestBuilder;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static org.hamcrest.Matchers.*;

public class CommentsTest extends TestConfig {

    @Test(description = "GET all comments under posts of specific user, validate email addresses format")
    public void verifyEmailsHaveValidFormat() {
        String userName = "Delphine";
        String validFormat = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";


        int userId =
                RequestBuilder.getResource("username", userName, Endpoint.USERS)
                        .then()
                        .body("size()", is(1), "[0].username", is(userName))
                        .extract()
                        .path("[0].id");

        ArrayList<Integer> postIds =
                RequestBuilder.getResource("userId", userId, Endpoint.POSTS)
                        .then()
                        .extract()
                        .path("id");

        RequestBuilder.getResource("postId", postIds, Endpoint.COMMENTS)
                .then()
                .assertThat()
                .body("postId", everyItem(in(postIds)))
                .body("email", everyItem(matchesPattern(validFormat)));
    }
}
