package com.github.tests.abstractpagetest;

import com.github.config.SpringConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.annotation.Resource;

@ContextConfiguration(classes = SpringConfig.class)
public abstract class AbstractPageTest extends AbstractTestNGSpringContextTests {

    @Resource(name = "driver")
    protected WebDriver driver;

    @BeforeSuite
    public void globalSetUp() {
        // Driver initialized along with Spring container in SpringConfig.class
    }

    @AfterSuite
    public void globalTearDown() {
        driver.close();
    }




}
