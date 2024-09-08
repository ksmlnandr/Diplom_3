package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login extends Loader{
    WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }
    Constructor constructor = new Constructor(driver);
    Register register = new Register(driver);

    private final String PAGE_LOGIN = constructor.getMAIN_PAGE_URL() + "/login";
    private final String PAGE_FORGOT_PASSWORD = constructor.getMAIN_PAGE_URL() + "/forgot-password";

    public String getMAIN_PAGE_URL() {
        return constructor.getMAIN_PAGE_URL();
    }

    public String getPAGE_REGISTER() {
        return register.getPAGE_REGISTER();
    }

    public String getPAGE_LOGIN() {
        return PAGE_LOGIN;
    }

    public String getPAGE_FORGOT_PASSWORD() {
        return PAGE_FORGOT_PASSWORD;
    }

    private By loginButtonOnMain = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private By profileButtonOnMain = By.xpath(".//p[text() = 'Личный Кабинет']");
    private By loginButtonOnForgot = By.xpath(".//a[text() = 'Войти']");
    private By loginButtonOnLogin = By.xpath(".//button[text() = 'Войти']");
    private By emailInput = By.xpath(".//*[contains(label,'Email')]/parent::div/div/input");
    private By passwordInput = By.xpath(".//*[contains(label,'Пароль')]/parent::div/div/input");

    public By getLoginButtonOnMain() {
        return loginButtonOnMain;
    }

    public By getProfileButtonOnMain() {
        return profileButtonOnMain;
    }

    public By getLoginButtonOnForgot() {
        return loginButtonOnForgot;
    }

    @Step("Ожидание скрытия анимации лоадера")
    @Override
    public void waitLoaderIsHidden() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(getLoader()));
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
}
