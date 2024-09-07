package pageobject;

import org.openqa.selenium.By;

public class Login {
    Constructor constructor;

    private final String PAGE_LOGIN = constructor.getMAIN_PAGE_URL() + "/login";

    private By signUpOptionButton = By.xpath(".//*[contains(@href,'register')]");

}
