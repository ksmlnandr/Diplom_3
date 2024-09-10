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
import pageobject.MainToProfile;
import resources.CreateUserApi;
import resources.DeleteUserApi;
import resources.RestClient;
import resources.UserData;

@RunWith(Parameterized.class)
public class FromMainToProfileTest {
    private WebDriver driver;
    private Browser browser = new Browser();
    private RestClient restClient = new RestClient();
    private CreateUserApi createUserApi = new CreateUserApi();
    private DeleteUserApi dua = new DeleteUserApi();
    private String accessToken;
    private UserData userData = new UserData("abcde@test.com", "12345", "Test");
    private Login login = new Login(driver);
    private MainToProfile mtp = new MainToProfile(driver);

    private final String browserType;

    public FromMainToProfileTest(String browserType) {
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
    @DisplayName("Тест перехода по клику на «Личный кабинет»")
    public void fromMainToProfileTest() {
        driver.get(login.getMAIN_PAGE_URL());
        mtp.waitLoaderIsHidden();
        mtp.clickProfileButton();
        mtp.waitLoaderIsHidden();
        mtp.isProfilePageDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
        dua.cleanUp(accessToken);
    }
}
