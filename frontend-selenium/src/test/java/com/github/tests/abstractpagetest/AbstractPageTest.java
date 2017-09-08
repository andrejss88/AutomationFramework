package com.github.tests.abstractpagetest;

import com.github.setup.SeleniumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractPageTest {

    protected WebDriver driver;

    @BeforeMethod
    public void globalSetUp() {
        driver = SeleniumDriver.getDriver();
    }

    @AfterMethod
    public void globalTearDown() {
        driver.close();
    }

}
