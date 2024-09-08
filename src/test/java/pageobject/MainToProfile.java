package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class MainToProfile {

    WebDriver driver;
    Login login = new Login(driver);
    Logout logout = new Logout(driver);

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
}
