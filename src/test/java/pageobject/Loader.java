package pageobject;

import org.openqa.selenium.By;
public class Loader {
    private final By loader = By.className("Modal_modal_overlay__x2ZCr");
    public By getLoader() {
        return loader;
    }

    public void waitLoaderIsHidden() {

    }
}
