import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.Browser;
import pageobject.Constructor;

@RunWith(Parameterized.class)
public class ConstructorTest {
    private Browser browser = new Browser();
    private Constructor constructor;
    private WebDriver driver;
    private final String browserType;
    private final String burgerElement;

    public ConstructorTest(String browserType, String burgerElement) {
        this.browserType = browserType;
        this.burgerElement = burgerElement;
    }


    @Parameterized.Parameters
    public static Object[][] setParameters() {
        return new Object[][] {
                {"chrome", "Булки"},
                {"chrome", "Соусы"},
                {"chrome", "Начинки"},
                {"firefox","Булки"},
                {"firefox", "Соусы"},
                {"firefox", "Начинки"}
        };
    }

    @Before
    public void setUp() {
        driver = browser.getWebDriver(browserType);
        constructor = new Constructor(driver);
        driver.get(constructor.getMAIN_PAGE_URL());
        constructor.waitLoaderIsHidden();
    }

    @Test
    @DisplayName("Тест на переход к разделу с ингредиентами на странице Конструктора")
    public void constructorTest() {
        constructor.clickTab(burgerElement);
        constructor.ingredientsListIsAvailable(burgerElement);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
