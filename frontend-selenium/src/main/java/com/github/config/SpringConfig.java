package com.github.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan("com.github")
public class SpringConfig {

    @Bean(name = "driver")
    public WebDriver getDriver(){
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
}
