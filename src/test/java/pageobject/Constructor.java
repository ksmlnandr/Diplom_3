package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;


public class Constructor {
    private WebDriver driver;
    private final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site";

    public Constructor(WebDriver driver) {
        this.driver = driver;
    }

    public String getMAIN_PAGE_URL() {
        return MAIN_PAGE_URL;
    }

    private By profileButton = By.xpath(".//*[contains(@href,'account')]");
    private By loader = By.className("Modal_modal_overlay__x2ZCr");

    private String tab = "span";
    private String listHeader = "h2";
    private boolean isList;

    private By elemConstructor(String pathElement, String burgerElement, boolean isList) {
        String xPath = String.format(".//%s[text() ", pathElement);
        xPath = xPath + String.format("= '%s']",burgerElement);
        By path = By.xpath(xPath);

            if (pathElement == "h2" && isList) {
            By listPath = By.xpath("(" + xPath + "/parent::div)/ul");
            return listPath;
        } else {
        return path;
        }
    }

    @Step("Ожидание скрытия анимации лоадера")
    public void waitLoaderIsHidden() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    @Step("Успешный клик по разделу Конструктора")
    public void clickTab(String burgerElement) {
        driver.findElement(elemConstructor(tab, burgerElement, false)).isDisplayed();
        driver.findElement(elemConstructor(tab, burgerElement, false)).click();
    }

    @Step("Проверка на отображение заголовка раздела и списка доступных ингредиентов")
    public void ingredientsListIsAvailable(String burgerElement) {
        assertTrue(driver.findElement(elemConstructor(listHeader, burgerElement, false)).isDisplayed());
        assertTrue(driver.findElement(elemConstructor(listHeader, burgerElement, true)).isDisplayed());
    }
}
