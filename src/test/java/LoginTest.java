import io.qameta.allure.junit4.DisplayName;
import model.CommonMethods;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.Login;
import model.CreateUserApi;
import model.UserData;
import pageobject.MainToProfile;

import static model.CommonMethods.setApiBaseUrl;
import static pageobject.Login.*;
import static pageobject.Constructor.MAIN_PAGE_URL;
import static pageobject.Register.PAGE_REGISTER;

public class LoginTest {
    WebDriver driver;
    private CommonMethods commonMethods = new CommonMethods();
    private Login login;
    private MainToProfile mtp;
    private CreateUserApi createUserApi = new CreateUserApi();
    private UserData userData = new UserData(commonMethods.setRandomUserEmail(), "1234", "Андрей");

    @Before
    public void setUp() {
        setApiBaseUrl();
        createUserApi.createUser(userData);

        driver = commonMethods.setDriver();
        login = new Login(driver);
        mtp = new MainToProfile(driver);
    }

    @Test
    @DisplayName("Тест входа по кнопке «Войти в аккаунт» на главной")
    public void authFromLoginButtonOnMain() {
        driver.get(MAIN_PAGE_URL);
        login.waitLoaderIsHidden(driver);

        driver.findElement(login.getLoginButtonOnMain()).isDisplayed();
        driver.findElement(login.getLoginButtonOnMain()).click();

        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
        login.checkInvalidPasswordNotDisplayed();
        mtp.clickProfileButton();
        login.checkEmailIsSame(userData.getEmail());
    }

    @Test
    @DisplayName("Тест входа через кнопку «Личный кабинет»")
    public void authFromProfileButtonOnMain() {
        driver.get(MAIN_PAGE_URL);
        login.waitLoaderIsHidden(driver);

        Assert.assertTrue(driver.findElement(login.getProfileButtonOnMain()).isDisplayed());
        driver.findElement(login.getProfileButtonOnMain()).click();

        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
        login.checkInvalidPasswordNotDisplayed();
        mtp.clickProfileButton();
        login.checkEmailIsSame(userData.getEmail());
    }

    @Test
    @DisplayName("Тест входа через кнопку в форме регистрации")
    public void authFromLoginButtonOnRegister() {
        driver.get(PAGE_REGISTER);
        login.waitLoaderIsHidden(driver);

        driver.findElement(login.getLoginButtonOnForgot()).isDisplayed();
        driver.findElement(login.getLoginButtonOnForgot()).click();

        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
        login.checkInvalidPasswordNotDisplayed();
        mtp.clickProfileButton();
        login.checkEmailIsSame(userData.getEmail());
    }

    @Test
    @DisplayName("Тест входа через кнопку в форме восстановления пароля")
    public void authFromLoginButtonOnForgot() {
        driver.get(PAGE_FORGOT_PASSWORD);
        login.waitLoaderIsHidden(driver);

        driver.findElement(login.getLoginButtonOnForgot()).isDisplayed();
        driver.findElement(login.getLoginButtonOnForgot()).click();

        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
        login.checkInvalidPasswordNotDisplayed();
        mtp.clickProfileButton();
        login.checkEmailIsSame(userData.getEmail());
    }

    @After
    public void tearDown() {
        driver.quit();
        commonMethods.deleteUser(userData.getEmail(), userData.getPassword());
    }

}
