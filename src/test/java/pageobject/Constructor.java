package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;


public class Constructor extends Loader {
    private WebDriver driver;
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    private String tab = "span";
    private String listHeader = "h2";

    public Constructor(WebDriver driver) {
        this.driver = driver;
    }

    private By elemConstructor(String pathElement, String burgerElement, boolean isCurrent, boolean isList) {
        String xPath = ".//" + pathElement + "[text() = '" + burgerElement + "']";
        By path = By.xpath(xPath);

            if (pathElement.equals("h2") && isList) {
            By listPath = By.xpath("(" + xPath + "/parent::div)/ul");
            return listPath;
        } else if (pathElement.equals("span") && !isList && !isCurrent) {
                By listPath = By.xpath("(" + xPath + "/parent::div)");
                return listPath;
            } else if ((pathElement.equals("span") && !isList) && isCurrent) {
                By listPath = By.xpath("(" + xPath + "/parent::div[contains(@class,'tab_tab_type_current__2BEPc')])");
                return listPath;
            } else {
        return path;
        }
    }

    @Step("Успешный клик по разделу Конструктора")
    public void clickTab(String burgerElement) {
        driver.findElement(elemConstructor(tab, burgerElement, false, false)).isDisplayed();
        driver.findElement(elemConstructor(tab, burgerElement, false, false)).click();
        assertTrue(driver.findElement(elemConstructor(tab, burgerElement, true,false)).isDisplayed());
    }

    @Step("Проверка на отображение заголовка раздела и списка доступных ингредиентов")
    public void ingredientsListIsAvailable(String burgerElement) {
        assertTrue(driver.findElement(elemConstructor(listHeader, burgerElement, false,false)).isDisplayed());
        assertTrue(driver.findElement(elemConstructor(listHeader, burgerElement, false,true)).isDisplayed());
    }
}
