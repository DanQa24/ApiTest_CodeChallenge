package Tests;

import Config.TestConfig;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class UsersTest extends TestConfig {

    @Test
    public void findUserByNickname() {
        given().log().all().when().get("/users").then().log().all();
    }
}
