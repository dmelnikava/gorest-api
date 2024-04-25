package in.co.gorest.utils;

import in.co.gorest.constans.IConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverUtil implements IConstants {

    public static WebDriver setUpWebDriver(String browserName) throws MalformedURLException {
        return new RemoteWebDriver(new URL(SELENIUM_URL), Browsers.valueOf(browserName.toUpperCase()).getCapabilities());
    }
}
