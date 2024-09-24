package model;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static model.RestClient.USER_REGISTER;

public class CreateUserApi {
    private UserResponseBody urb = new UserResponseBody();
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void createUser(UserData userData) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(userData)
                        .when()
                        .post(USER_REGISTER);

        urb = response.body().as(UserResponseBody.class);
        accessToken = urb.getAccessToken();
    }

}
