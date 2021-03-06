package com.github.pages;

import com.github.config.PropertiesFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Resource;

public abstract class AbstractGitHubPage {

    public final static String BASE_URL = new PropertiesFactory().loadBaseUrl();

    @Resource(name= "driver")
    protected WebDriver driver;

    @Resource(name = "wait")
    protected WebDriverWait wait;

}
