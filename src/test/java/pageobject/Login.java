package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static pageobject.Constructor.MAIN_PAGE_URL;

public class Login extends Loader{
    WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public static final String PAGE_LOGIN = MAIN_PAGE_URL + "login";
    public static final String PAGE_FORGOT_PASSWORD = MAIN_PAGE_URL + "forgot-password";

    private By loginButtonOnMain = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private By profileButtonOnMain = By.xpath(".//p[text() = 'Личный Кабинет']");
    private By loginButtonOnForgot = By.xpath(".//a[text() = 'Войти']");
    private By loginButtonOnLogin = By.xpath(".//button[text() = 'Войти']");
    private By emailInput = By.xpath(".//*[contains(label,'Email')]/parent::div/div/input");
    private By passwordInput = By.xpath(".//*[contains(label,'Пароль')]/parent::div/div/input");
    private By invalidPasswordWarning = By.xpath(".//p[text() = 'Некорректный пароль']");
    private By emailInputLoginPage;

    public By getLoginButtonOnMain() {
        return loginButtonOnMain;
    }

    public By getProfileButtonOnMain() {
        return profileButtonOnMain;
    }

    public By getLoginButtonOnForgot() {
        return loginButtonOnForgot;
    }


    @Step("Проверка заполнения формы авторизации пользователя")
    public void fillUserData(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Проверка возможности клика по кнопке 'Войти'")
    public void clickLoginButton() {
        Assert.assertTrue(driver.findElement(loginButtonOnLogin).isEnabled());
        driver.findElement(loginButtonOnLogin).click();
    }

    @Step("Проверка отсутствия ошибки о некорректном пароле")
    public void checkInvalidPasswordNotDisplayed() {
        Assert.assertFalse(driver.findElement(invalidPasswordWarning).isDisplayed());
    }

    @Step("Проверка совпадения email у авторизованного пользователя в личном кабинете")
    public void checkEmailIsSame(String email) {
        emailInputLoginPage = By.xpath(String.format(".//input[@type = 'text' and @name = 'name' and contains(@value,'%s')]",email));
        Assert.assertTrue(driver.findElement(emailInputLoginPage).isDisplayed());
    }
}
