package in.co.gorest.gui;

import in.co.gorest.constans.IConstants;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage implements IConstants {

    private static WebDriver driver;

    public AbstractPage(WebDriver driver) {
        setDriver(driver);
    }

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
