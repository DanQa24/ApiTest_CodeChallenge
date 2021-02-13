package Tests;

import Config.*;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class UsersTest extends TestConfig {

    private int getUserIdBy(String username) {
        return given()
                .param("username", username)
                .when()
                .get(BlogEndpoints.USERS)
                .then()
                .body("size()", is(1), "[0].username", is(username))
                .extract()
                .path("[0].id");
    }

    private ArrayList<Integer> getPostsByUserId(int userId) {
        return given()
                .param("userId", userId)
                .when()
                .get(BlogEndpoints.POSTS)
                .then()
                .extract()
                .path("id");
    }

    @Test(description = "Verify emails have valid format")
    @Description("E2E: Verify that email format is valid in all comments under all User's posts")
    public void verifyEmailsHaveValidFormat() {
        String username = "Delphine";

        int userId = getUserIdBy(username);
        ArrayList<Integer> userPosts =getPostsByUserId(userId);

        given()
                .param("postId", userPosts)
        .when()
                .get(BlogEndpoints.COMMENTS)
        .then()
                .assertThat()
                .body("email",
                        everyItem(matchesPattern("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")));
    }

}

