package resources;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateUserApi {
    RestClient restClient = new RestClient();
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
                        .post(restClient.getUserRegister());

        urb = response.body().as(UserResponseBody.class);
        accessToken = urb.getAccessToken();
    }

}
