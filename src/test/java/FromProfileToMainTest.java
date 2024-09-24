import io.qameta.allure.junit4.DisplayName;
import model.CommonMethods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.Login;
import pageobject.MainToProfile;
import pageobject.ProfileToMain;
import model.CreateUserApi;
import model.UserData;

import static model.CommonMethods.setApiBaseUrl;
import static pageobject.Login.PAGE_LOGIN;

public class FromProfileToMainTest {
    private WebDriver driver;
    private Login login;
    private ProfileToMain ptm;
    private CommonMethods commonMethods = new CommonMethods();
    private CreateUserApi createUserApi = new CreateUserApi();
    private MainToProfile mtp;
    private UserData userData = new UserData(commonMethods.setRandomUserEmail(), "1234567", "Test");

    @Before
    public void setUp() {
        setApiBaseUrl();
        createUserApi.createUser(userData);

        driver = commonMethods.setDriver();
        login = new Login(driver);
        ptm = new ProfileToMain(driver);
        mtp = new MainToProfile(driver);

        driver.get(PAGE_LOGIN);
        login.waitLoaderIsHidden(driver);
        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
        mtp.clickProfileButton();
    }

    @Test
    @DisplayName("Тест перехода на главную по кнопке 'Конструктор'")
    public void constructorButtonToMainTest() {

        ptm.waitLoaderIsHidden(driver);
        ptm.clickConstructorButton();
        ptm.waitLoaderIsHidden(driver);
        ptm.isMainPageDisplayed();
    }

    @Test
    @DisplayName("Тест перехода на главную по кнопке логотипа")
    public void logoButtonToMainTest() {
        ptm.waitLoaderIsHidden(driver);
        ptm.clickLogoButton();
        ptm.waitLoaderIsHidden(driver);
        ptm.isMainPageDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
        commonMethods.deleteUser(userData.getEmail(), userData.getPassword());
    }
}
