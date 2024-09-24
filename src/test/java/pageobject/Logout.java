package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pageobject.Constructor.MAIN_PAGE_URL;
import static pageobject.Login.PAGE_LOGIN;

public class Logout extends Loader {
    WebDriver driver;
    public static final String PAGE_PROFILE = MAIN_PAGE_URL + "account/profile";
    private By logoutButton = By.xpath(".//button[text() = 'Выход']");

    public Logout(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка клика по кнопке 'Выход'")
    public void clickLogoutButton() {
        Assert.assertTrue(driver.findElement(logoutButton).isDisplayed());
        driver.findElement(logoutButton).click();
    }

    public void waitHideLogoutButton() {
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOf(driver.findElement(logoutButton))));
    }

    @Step("Проверка автоматического перехода на страницу авторизации")
    public void checkAuthPageAfterLogout() {
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(PAGE_LOGIN, actual);
    }

}
