package com.bklymiuk.Tests;

import com.bklymiuk.Blog.Endpoint;
import com.bklymiuk.Utils.RequestBuilder;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


public class UsersTest extends TestConfig implements RequestBuilder {

    @Test(description = "Validate the structure of each User in response")
    @Description("Verify that all posts have UserId, Id, Title and Body")
    public void validateUsersBody() {
        RequestBuilder.getResource(Endpoint.POSTS)
                .then()
                .assertThat()
                .body("$", everyItem(hasKey("id")))
                .body("$", everyItem(hasKey("name")))
                .body("$", everyItem(hasKey("username")))
                .body("$", everyItem(hasKey("email")))
                .body("$", everyItem(hasKey("address")))
                .body("$", everyItem(hasKey("phone")))
                .body("$", everyItem(hasKey("website")))
                .body("$", everyItem(hasKey("company")));

    }


    @Test(description = "GET user by valid username")
    public void getUserByValidUsername() {
        String userName = "Delphine";

        RequestBuilder.getResource("username", userName, Endpoint.USERS)
                .then()
                .assertThat()
                .body("size()", is(1))
                .body("[0].username", is(userName));
    }


    @Test(description = "GET user by invalid username")
    public void getUserByInvalidUsername() {
        String invalidUsername = "Non existing username";

        RequestBuilder.getResource("username", invalidUsername, Endpoint.USERS)
                .then()
                .assertThat()
                .body("size()", is(0));
    }
}

