package com.github.pages.search.controllers;

import org.openqa.selenium.WebDriver;

import javax.annotation.Resource;

public class AbstractSearchPageController {

    protected static final String FILTER_ITEM = "//a[contains(@class, 'filter-item')";

    @Resource(name = "driver")
    protected WebDriver driver;

}
