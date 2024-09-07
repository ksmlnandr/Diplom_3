package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Register {
    WebDriver driver;
    Constructor constructor = new Constructor(driver);
    private final String PAGE_REGISTER = constructor.getMAIN_PAGE_URL() + "/register";
    private By nameInput = By.xpath(".//*[contains(label,'Имя')]");
    private By emailInput = By.xpath(".//*[contains(label,'Email')]");
    private By passwordInput = By.xpath(".//*[contains(label,'Пароль')]");
    private By signUpButton = By.xpath(".//*[text() = 'Зарегистрироваться']");

    public void fillNewUserData(String name, String email, String password) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }
}
