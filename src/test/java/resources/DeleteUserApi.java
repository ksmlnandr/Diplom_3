package resources;

import static io.restassured.RestAssured.given;

public class DeleteUserApi {
    private RestClient restClient = new RestClient();
    public void cleanUp(String bearerToken) {
        if (bearerToken != null) {
            given()
                    .header("Authorization", bearerToken)
                    .when()
                    .delete(restClient.getUserUpdate());
        } else {
            System.out.println("Удалять нечего");
        }
    }
}
