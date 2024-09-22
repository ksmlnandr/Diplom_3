package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Loader {
    private final By loader = By.xpath(".//div[contains(@class, 'Modal_modal_overlay__x2ZCr')]");

    public void waitLoaderIsHidden(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(loader));

    }
}
