import io.qameta.allure.junit4.DisplayName;
import model.CommonMethods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.Login;
import pageobject.Logout;
import model.CreateUserApi;
import model.UserData;
import pageobject.MainToProfile;

import static model.CommonMethods.setApiBaseUrl;
import static pageobject.Login.PAGE_LOGIN;

public class LogoutTest {
    private WebDriver driver;
    private CommonMethods commonMethods = new CommonMethods();
    private CreateUserApi createUserApi = new CreateUserApi();
    private UserData userData = new UserData(commonMethods.setRandomUserEmail(), "1234567", "Test");
    private Login login;
    private Logout logout;
    private MainToProfile mtp;

    @Before
    public void setUp() {
        setApiBaseUrl();
        createUserApi.createUser(userData);

        driver = commonMethods.setDriver();
        login = new Login(driver);
        logout = new Logout(driver);
        mtp = new MainToProfile(driver);

        driver.get(PAGE_LOGIN);
        logout.waitLoaderIsHidden(driver);
        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
    }

    @Test
    @DisplayName("Тест логаута пользователя по кнопке из личного кабинета")
    public void logoutButtonTest() {
        mtp.clickProfileButton();
        logout.waitLoaderIsHidden(driver);
        logout.clickLogoutButton();
        logout.waitHideLogoutButton();
        logout.checkAuthPageAfterLogout();
    }

    @After
    public void tearDown() {
        driver.quit();
        commonMethods.deleteUser(userData.getEmail(), userData.getPassword());
    }
}
