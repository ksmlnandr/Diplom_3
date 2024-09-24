package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
    WebDriver driver;
    public WebDriver getWebDriver(String browser) {
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                return driver;
            case "firefox":
                driver = new FirefoxDriver();
                return driver;
        }
        return driver;
    }
}
