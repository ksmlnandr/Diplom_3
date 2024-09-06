package pageobject;

import org.openqa.selenium.WebDriver;

public class CommonLinks {
    private WebDriver driver;
    private final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site";

    public String getMAIN_PAGE_URL() {
        return MAIN_PAGE_URL;
    }
}
