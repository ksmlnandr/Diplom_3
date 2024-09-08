package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Logout extends Loader {
    WebDriver driver;
    Constructor constructor = new Constructor(driver);

    private final String PAGE_PROFILE = constructor.getMAIN_PAGE_URL() + "/account/profile";
    private By logoutButton = By.xpath(".//button[text() = 'Выход']");

    public Logout(WebDriver driver) {
        this.driver = driver;
    }

    public String getPAGE_PROFILE() {
        return PAGE_PROFILE;
    }

    @Step("Проверка клика по кнопке 'Выход'")
    public void clickLogoutButton() {
        Assert.assertTrue(driver.findElement(logoutButton).isDisplayed());
        driver.findElement(logoutButton).click();
    }

    @Step("Ожидание скрытия анимации лоадера")
    @Override
    public void waitLoaderIsHidden() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(getLoader()));
    }
}
