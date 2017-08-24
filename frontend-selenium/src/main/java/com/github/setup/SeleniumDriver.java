package com.github.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Core class acting as Webdriver Factory
 */
public class SeleniumDriver {

    static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {

            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "false");

            /*
            Sooner or later a newer version of Firefox (auto-updated) will break all your tests. So:

            1. Download an older Firefox (v47)that works well with Selenium 3
            2. Install it into custom folder (e.g. Mozilla FireFox47)
            3. Disable all auto-updates in Settings -> Advanced
            4. Explicitly point to this binary as below
             */

            final String firefoxBinaryPath = "C:\\Program Files\\Mozilla Firefox47\\firefox.exe";

            capabilities.setCapability("firefox_binary", firefoxBinaryPath);
            WebDriver driver = new FirefoxDriver(capabilities);

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return driver;
        }
        return driver;
    }

}
