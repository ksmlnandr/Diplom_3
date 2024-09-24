import io.qameta.allure.junit4.DisplayName;
import model.CommonMethods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.Constructor;

import static pageobject.Constructor.MAIN_PAGE_URL;

@RunWith(Parameterized.class)
public class ConstructorTest {
    CommonMethods commonMethods = new CommonMethods();
    private Constructor constructor;
    private WebDriver driver;
    private final String burgerElement;

    public ConstructorTest(String burgerElement) {
        this.burgerElement = burgerElement;
    }


    @Parameterized.Parameters
    public static Object[][] setParameters() {
        return new Object[][] {
                {"Булки"},
                {"Соусы"},
                {"Начинки"}
        };
    }

    @Before
    public void setUp() {
        driver = commonMethods.setDriver();
        constructor = new Constructor(driver);
    }

    @Test
    @DisplayName("Тест на переход к разделу с ингредиентами на странице Конструктора")
    public void constructorTest() {
        driver.get(MAIN_PAGE_URL);
        constructor.waitLoaderIsHidden(driver);
        constructor.clickTab(burgerElement);
        constructor.ingredientsListIsAvailable(burgerElement);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
