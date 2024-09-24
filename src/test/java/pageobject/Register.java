package pageobject;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.LoginUserApi;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static pageobject.Constructor.MAIN_PAGE_URL;

public class Register extends Loader {
    WebDriver driver;
    public static final String PAGE_REGISTER = MAIN_PAGE_URL + "register";
    private By nameInput = By.xpath(".//*[contains(label,'Имя')]/parent::div/div/input");
    private By emailInput = By.xpath(".//*[contains(label,'Email')]/parent::div/div/input");
    private By passwordInput = By.xpath(".//*[contains(label,'Пароль')]/parent::div/div/input");
    private By signUpButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private int maxPasswordLength = 6;
    LoginUserApi loginUserApi = new LoginUserApi();

    public Register(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка заполнения формы регистрации пользователя")
    public void fillNewUserData(String name, String email, String password) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Проверка возможности клика по кнопке регистрации")
    public void clickSignUpButton(String password) {
        if (password.length() <= maxPasswordLength) {
            Assert.assertTrue(driver.findElement(signUpButton).isEnabled());
            driver.findElement(signUpButton).click();
        } else if (password.length() > maxPasswordLength) {
            Assert.assertFalse(driver.findElement(signUpButton).isEnabled());
        }
    }

    @Step("Проверка возможности логина по API для успешно зарегистрированного пользователя")
    public void checkApiAuthForRegUser(String email, String password) {
        if (password.length() <= maxPasswordLength) {
            Response response = loginUserApi.postUserLogin(email, password);
            response.then().statusCode(200);
        }
    }
}
