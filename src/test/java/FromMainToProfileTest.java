import io.qameta.allure.junit4.DisplayName;
import model.CommonMethods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.Login;
import pageobject.MainToProfile;
import model.CreateUserApi;
import model.UserData;

import static model.CommonMethods.setApiBaseUrl;
import static pageobject.Login.PAGE_LOGIN;
import static pageobject.Constructor.MAIN_PAGE_URL;

public class FromMainToProfileTest {
    private WebDriver driver;
    private Login login;
    private MainToProfile mtp;
    private CommonMethods commonMethods = new CommonMethods();
    private CreateUserApi createUserApi = new CreateUserApi();
    private UserData userData = new UserData(commonMethods.setRandomUserEmail(), "1234567", "Test");


    @Before
    public void setUp() {
        setApiBaseUrl();
        createUserApi.createUser(userData);

        driver = commonMethods.setDriver();
        login = new Login(driver);
        mtp = new MainToProfile(driver);

        driver.get(PAGE_LOGIN);
        login.waitLoaderIsHidden(driver);
        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
    }

    @Test
    @DisplayName("Тест перехода по клику на «Личный кабинет»")
    public void fromMainToProfileTest() {
        driver.get(MAIN_PAGE_URL);
        mtp.waitLoaderIsHidden(driver);
        mtp.clickProfileButton();
        mtp.waitLoaderIsHidden(driver);
        mtp.isProfilePageDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
        commonMethods.deleteUser(userData.getEmail(), userData.getPassword());
    }
}
