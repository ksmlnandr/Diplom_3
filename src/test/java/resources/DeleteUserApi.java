package resources;

import static io.restassured.RestAssured.given;

public class DeleteUserApi {
    private RestClient restClient = new RestClient();
    public void cleanUp(String accessToken) {
        if (accessToken != null) {
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(restClient.getUserUpdate());
        } else {
            System.out.println("Удалять нечего");
        }
    }
}
