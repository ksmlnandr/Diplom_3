import io.qameta.allure.junit4.DisplayName;
import model.CommonMethods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.Register;

import static pageobject.Register.PAGE_REGISTER;

@RunWith(Parameterized.class)
public class RegisterTest {
    private CommonMethods commonMethods = new CommonMethods();
    private Register register;
    private WebDriver driver;
    private final String name;
    private final String email;
    private final String password;

    public RegisterTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] setParameters() {
        return new Object[][] {
                {"Андрей", "new123@test.ru", "123456"},
                {"Николай", "new234@test.ru", "1234567"},
        };
    }

    @Before
    public void setUp() {
        driver = commonMethods.setDriver();
        register = new Register(driver);
    }

    @Test
    @DisplayName("Тест на регистрацию нового пользователя")
    public void registerNewUserTest() {
        driver.get(PAGE_REGISTER);
        register.waitLoaderIsHidden(driver);
        register.fillNewUserData(name, email, password);
        register.clickSignUpButton(password);

        register.checkApiAuthForRegUser(email, password);
    }

    @After
    public void tearDown(){
        driver.quit();
        commonMethods.deleteUser(email, password);
    }
}
