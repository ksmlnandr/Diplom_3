import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.Browser;
import pageobject.Login;
import pageobject.Logout;
import pageobject.ProfileToMain;
import resources.CreateUserApi;
import resources.DeleteUserApi;
import resources.RestClient;
import resources.UserData;

@RunWith(Parameterized.class)
public class FromProfileToMainTest {
    private WebDriver driver;
    private Browser browser = new Browser();
    private RestClient restClient = new RestClient();
    private CreateUserApi createUserApi = new CreateUserApi();
    private DeleteUserApi dua = new DeleteUserApi();
    private String accessToken;
    private UserData userData = new UserData("abcdef@test.com", "12345", "Test");
    private Login login = new Login(driver);
    private Logout logout = new Logout(driver);
    private ProfileToMain ptm = new ProfileToMain(driver);

    private final String browserType;
    public FromProfileToMainTest(String browserType) {
        this.browserType = browserType;
    }

    @Parameterized.Parameters
    public static Object[][] setParameters() {
        return new Object[][] {
                {"chrome"},
                {"firefox"}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = restClient.getBaseUrl();
        createUserApi.createUser(userData);
        accessToken = createUserApi.getAccessToken();

        driver = browser.getWebDriver(browserType);
        login = new Login(driver);

        driver.get(login.getPAGE_LOGIN());
        login.waitLoaderIsHidden();
        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
    }

    @Test
    @DisplayName("Тест перехода на главную по кнопке 'Конструктор'")
    public void constructorButtonToMainTest() {
        driver.get(logout.getPAGE_PROFILE());
        ptm.waitLoaderIsHidden();
        ptm.clickConstructorButton();
        ptm.waitLoaderIsHidden();
        ptm.isMainPageDisplayed();
    }

    @Test
    @DisplayName("Тест перехода на главную по кнопке логотипа")
    public void logoButtonToMainTest() {
        driver.get(logout.getPAGE_PROFILE());
        ptm.waitLoaderIsHidden();
        ptm.clickLogoButton();
        ptm.waitLoaderIsHidden();
        ptm.isMainPageDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
        dua.cleanUp(accessToken);
    }
}
