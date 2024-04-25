package in.co.gorest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;

import in.co.gorest.constans.IConstants;
import in.co.gorest.gui.AbstractPage;
import in.co.gorest.utils.DriverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;

public abstract class AbstractTest implements IConstants {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @Parameters({"browserName", "startEndpoint"})
    @BeforeMethod()
    public void setUp(String browserName, String startEndpoint) {
        try {
            WebDriver webDriver = DriverUtil.setUpWebDriver(browserName);
            webDriver.get(URL + startEndpoint);
            driver.set(webDriver);
            AbstractPage.setDriver(getDriver());
        } catch (MalformedURLException e) {
            LOGGER.error("Unable to start web driver!");
        }
    }

    @AfterMethod()
    public void tearDown() {
        getDriver().quit();
    }
}
