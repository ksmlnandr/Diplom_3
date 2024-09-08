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
import resources.CreateUserApi;
import resources.RestClient;
import resources.UserData;

@RunWith(Parameterized.class)
public class LoginTest {
    WebDriver driver;
    Login login = new Login(driver);
    private Browser browser = new Browser();
    private RestClient restClient = new RestClient();
    private CreateUserApi createUserApi = new CreateUserApi();
    private UserData userData = new UserData("ivanoff@test.ru", "1234", "Андрей");
    private final String browserType;


    public LoginTest(String browserType) {
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
    }

    @Test
    @DisplayName("Тест входа по кнопке «Войти в аккаунт» на главной")
    public void authFromLoginButtonOnMain() {
        driver.get(login.getMAIN_PAGE_URL());
        login.waitLoaderIsHidden();

        driver.findElement(login.getLoginButtonOnMain()).isDisplayed();
        driver.findElement(login.getLoginButtonOnMain()).click();

        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
    }

    @Test
    @DisplayName("Тест входа через кнопку «Личный кабинет»")
    public void authFromProfileButtonOnMain() {
        driver.get(login.getMAIN_PAGE_URL());
        login.waitLoaderIsHidden();

        driver.findElement(login.getProfileButtonOnMain()).isDisplayed();
        driver.findElement(login.getProfileButtonOnMain()).click();

        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
    }

    @Test
    @DisplayName("Тест входа через кнопку в форме регистрации")
    public void authFromLoginButtonOnRegister() {
        driver.get(login.getPAGE_REGISTER());
        login.waitLoaderIsHidden();

        driver.findElement(login.getLoginButtonOnForgot()).isDisplayed();
        driver.findElement(login.getLoginButtonOnForgot()).click();

        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
    }

    @Test
    @DisplayName("Тест входа через кнопку в форме восстановления пароля")
    public void authFromLoginButtonOnForgot() {
        driver.get(login.getPAGE_REGISTER());
        login.waitLoaderIsHidden();

        driver.findElement(login.getLoginButtonOnForgot()).isDisplayed();
        driver.findElement(login.getLoginButtonOnForgot()).click();

        login.fillUserData(userData.getEmail(), userData.getPassword());
        login.clickLoginButton();
    }

    @After
    public void tearDown() {
        driver.quit();

        //дописать метод удаления пользователя после теста
    }

}
