package com.github.pages.signin.controllers;

import org.openqa.selenium.WebDriver;

import javax.annotation.Resource;

public class AbstractSignInPageController {

    @Resource(name = "driver")
    protected WebDriver driver;

}
