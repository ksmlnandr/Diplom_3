package resources;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class CreateUserApi {

    RestClient restClient = new RestClient();

    private void setBaseUrl() {

    }


    public void createUser(UserData userData) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(userData)
                        .when()
                        .post(restClient.getUserRegister());
        Assert.assertEquals(true, response.then().statusCode(200));
    }

}
