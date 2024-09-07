import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.Browser;
import pageobject.Register;

@RunWith(Parameterized.class)
public class RegisterTest {
    private Browser browser = new Browser();
    private Register register;
    private WebDriver driver;
    private final String browserType;
    private final String name;
    private final String email;
    private final String password;

    public RegisterTest(String browserType, String name, String email, String password) {
        this.browserType = browserType;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] setParameters() {
        return new Object[][] {
                {"chrome", "Андрей", "ivanoff1211@test.ru", "123456"},
                {"chrome", "Николай", "ivanoff5611@test.ru", "1234567"},
                {"firefox", "Стас", "ivanoff8911@test.ru", "123456"},
                {"firefox", "Алексей", "ivanoff2311@test.ru", "1234567"},
        };
    }

    @Before
    public void setUp() {
        driver = browser.getWebDriver(browserType);
        register = new Register(driver);
    }

    @Test
    public void registerNewUserTest() {
        driver.get(register.getPAGE_REGISTER());
        register.waitLoaderIsHidden();
        register.fillNewUserData(name, email, password);
        register.clickSignUpButton(password);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
