package model;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static model.CommonMethods.setApiBaseUrl;
import static model.RestClient.USER_AUTH;

public class LoginUserApi {
    public Response postUserLogin(String email, String password) {
        setApiBaseUrl();

        UserAuth ua = new UserAuth(email, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(ua)
                        .when()
                        .post(USER_AUTH);
        return response;
    }

    public int getUserLoginStatusCode(Response response) {
        int statusCode = response.getStatusCode();
        return statusCode;
    }

    public String getAccessToken(String email, String password) {
        String accessToken;

        Response response = postUserLogin(email, password);
        int statusCode = getUserLoginStatusCode(response);

        if (statusCode == 200) {
            UserResponseBody urb = response.body().as(UserResponseBody.class);
            accessToken = urb.getAccessToken();
            return accessToken;
        } else {
            accessToken = null;
            return accessToken;
        }
    }
}
