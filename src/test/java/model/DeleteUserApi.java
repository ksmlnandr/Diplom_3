package model;

import static io.restassured.RestAssured.given;
import static model.CommonMethods.setApiBaseUrl;
import static model.RestClient.USER_UPDATE;

public class DeleteUserApi {
    public void cleanUp(String accessToken) {
        setApiBaseUrl();

        if (accessToken != null) {
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(USER_UPDATE);
        } else {
            System.out.println("Удалять нечего");
        }
    }
}
