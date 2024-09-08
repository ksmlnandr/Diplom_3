package resources;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateUserApi {

    RestClient restClient = new RestClient();

    public void createUser(UserData userData) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(userData)
                        .when()
                        .post(restClient.getUserRegister());
    }

}
