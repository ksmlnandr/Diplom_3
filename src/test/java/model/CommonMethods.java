package model;

import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import pageobject.Browser;

import java.util.Random;

import static model.RestClient.BASE_URL;

public class CommonMethods {
    private WebDriver driver;
    private Browser browser = new Browser();
    private Random random = new Random();
    private DeleteUserApi deleteUser = new DeleteUserApi();
    private LoginUserApi loginUser = new LoginUserApi();

    public String setRandomUserEmail() {
        String userEmail = String.format("test_user_%d@test.com", random.nextInt());
        return userEmail;
    }
    public static void setApiBaseUrl() {
        RestAssured.baseURI = BASE_URL;
    }
    public WebDriver setDriver() {
        String driverType = System.getenv("BROWSER");
        driver = browser.getWebDriver(driverType == null ? "chrome" : driverType);

        return driver;
    }

    public void deleteUser(String email, String password) {
        deleteUser.cleanUp(loginUser.getAccessToken(email, password));
    }
}
