package com.github.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Resource;

public abstract class AbstractPageController {

    @Resource(name = "driver")
    protected WebDriver driver;

    @Resource(name = "wait")
    protected WebDriverWait wait;

}
