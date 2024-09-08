package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainToProfile extends Loader {

    private WebDriver driver;
    private Login login = new Login(driver);
    private Logout logout = new Logout(driver);

    public MainToProfile(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка перехода в кабинет пользователя по клику на кнопку 'Личный кабинет'")
    public void clickProfileButton() {
        Assert.assertTrue(driver.findElement(login.getProfileButtonOnMain()).isDisplayed());
        driver.findElement(login.getProfileButtonOnMain()).click();
    }

    @Step("Проверка открытия страницы личного кабинета")
    public void isProfilePageDisplayed() {
        String expected = logout.getPAGE_PROFILE();
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(expected, actual);
    }

    @Step("Ожидание скрытия анимации лоадера")
    @Override
    public void waitLoaderIsHidden() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(getLoader()));
    }
}
