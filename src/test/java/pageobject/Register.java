package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Register extends Loader {
    WebDriver driver;
    Constructor constructor = new Constructor(driver);
    private final String PAGE_REGISTER = constructor.getMAIN_PAGE_URL() + "/register";
    private By nameInput = By.xpath(".//*[contains(label,'Имя')]/parent::div/div/input");
    private By emailInput = By.xpath(".//*[contains(label,'Email')]/parent::div/div/input");
    private By passwordInput = By.xpath(".//*[contains(label,'Пароль')]/parent::div/div/input");
    private By signUpButton = By.xpath(".//button[text() = 'Зарегистрироваться']");

    public Register(WebDriver driver) {
        this.driver = driver;
    }

    public String getPAGE_REGISTER() {
        return PAGE_REGISTER;
    }

    @Step("Ожидание скрытия анимации лоадера")
    @Override
    public void waitLoaderIsHidden() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(getLoader()));
    }

    @Step("Проверка заполнения формы регистрации пользователя")
    public void fillNewUserData(String name, String email, String password) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Проверка возможности клика по кнопке регистрации")
    public void clickSignUpButton(String password) {
        if (password.length() <= 6) {
            Assert.assertTrue(driver.findElement(signUpButton).isEnabled());
            driver.findElement(signUpButton).click();
        } else if (password.length() >= 7) {
            Assert.assertFalse(driver.findElement(signUpButton).isEnabled());
        }
    }
}
