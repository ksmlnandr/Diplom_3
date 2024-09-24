package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import static pageobject.Logout.PAGE_PROFILE;

public class MainToProfile extends Loader {

    private WebDriver driver;
    private Login login = new Login(driver);

    public MainToProfile(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка перехода в кабинет пользователя по клику на кнопку 'Личный кабинет'")
    public void clickProfileButton() {
        Assert.assertTrue(driver.findElement(login.getProfileButtonOnMain()).isEnabled());
        driver.findElement(login.getProfileButtonOnMain()).click();
    }

    @Step("Проверка открытия страницы личного кабинета")
    public void isProfilePageDisplayed() {
        String expected = PAGE_PROFILE;
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(expected, actual);
    }
}
