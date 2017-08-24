package com.github.tests;

import com.github.setup.SeleniumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class AbstractPageTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = SeleniumDriver.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
