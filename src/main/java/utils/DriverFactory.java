package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class DriverFactory {
    private WebDriver driver;
    private WebDriverWait driverWait;

    public DriverFactory() {
    }

    public WebDriver GetDriver() {
        if (driver == null) {
            throw new NullPointerException("The WebDriver browser instance was not initialized.");
        }
        return driver;
    }

    public WebDriverWait getDriverWait() {
        if (driver == null || driverWait == null) {
            throw new NullPointerException("The WebDriver browser instance was not initialized.");
        }
        return driverWait;
    }

    public void StartBrowser(BrowserTypes browserType, int defaultTimeOut) throws MalformedURLException {
        switch (browserType) {
            case Chrome:
                driver = BuildWebDriver.BuildChromeDriver();
                break;
            case FireFox:
                driver = BuildWebDriver.BuildFirefoxDriver();
                break;
            case Remote:
                driver = BuildWebDriver.remoteDriver();
            default:
                driver = BuildWebDriver.BuildChromeDriver();
                break;
        }
        driver.manage().window().maximize();
        driverWait = new WebDriverWait(driver, defaultTimeOut);
    }

    public void StopDriver() {
        driver.quit();
        driver = null;
        driverWait = null;
    }
}
