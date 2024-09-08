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
import resources.CreateUserApi;
import resources.RestClient;
import resources.UserData;

@RunWith(Parameterized.class)
public class LogoutTest {

    private WebDriver driver;
    private Browser browser = new Browser();
    private RestClient restClient = new RestClient();
    private CreateUserApi createUserApi = new CreateUserApi();
    private UserData userData = new UserData("abcd@test.com", "12345", "Test");
    private Login login = new Login(driver);
    private Logout logout = new Logout(driver);

    private final String browserType;

    public LogoutTest(String browserType) {
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

        driver = browser.getWebDriver(browserType);
        login = new Login(driver);

        driver.get(login.getPAGE_LOGIN());
        logout.waitLoaderIsHidden();
        login.fillUserData(userData.getEmail(), userData.getPassword());
    }

    @Test
    public void logoutButtonTest() {
        driver.get(logout.getPAGE_PROFILE());
        logout.clickLogoutButton();
    }

    @After
    public void tearDown() {
        driver.quit();

        //дописать метод удаления пользователя после теста
    }
}
