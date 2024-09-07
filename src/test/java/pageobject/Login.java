package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    WebDriver driver;
    Constructor constructor = new Constructor(driver);

    private final String PAGE_LOGIN = constructor.getMAIN_PAGE_URL() + "/login";

    private By signUpOptionButton = By.xpath(".//*[contains(@href,'register')]");

}
