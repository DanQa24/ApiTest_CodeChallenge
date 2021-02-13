package Tests;

import Config.*;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class UsersTest extends TestConfig {

    @Test
    public void verifyEmailsHaveCorrectFormat() {

        int userId =
                given()
                        .param("username", "Non existing user")
                .when()
                        .get("/users")
                .then()
                        .body("size()", is(1), "[0].username", is("Delphine"))
                        .extract()
                        .path("[0].id");


        ArrayList<Integer> postIds =
                given()
                        .param("userId", userId)
                .when()
                        .get("/posts")
                .then()
                        .extract()
                        .path("id");


        given()
                .param("postId", postIds)
        .when()
                .get("/comments")
        .then()
                .assertThat()
                .body("email",
                        everyItem(matchesPattern("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")));
    }

}

