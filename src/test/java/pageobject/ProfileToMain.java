package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static pageobject.Constructor.MAIN_PAGE_URL;

public class ProfileToMain extends Loader {
    private WebDriver driver;
    private By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    private By logoButton = By.className("AppHeader_header__logo__2D0X2");


    public ProfileToMain(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка перехода на главную по клику на Конструктор")
    public void clickConstructorButton() {
        Assert.assertTrue(driver.findElement(constructorButton).isDisplayed());
        driver.findElement(constructorButton).click();
    }

    @Step("Проверка перехода на главную по клику на лого")
    public void clickLogoButton() {
        Assert.assertTrue(driver.findElement(logoButton).isDisplayed());
        driver.findElement(logoButton).click();
    }

    @Step("Проверка открытия главной страницы")
    public void isMainPageDisplayed() {
        String expected = MAIN_PAGE_URL;
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(expected, actual);
    }
}
