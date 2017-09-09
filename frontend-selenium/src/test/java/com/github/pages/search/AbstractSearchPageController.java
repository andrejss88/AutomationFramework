package com.github.pages.search;

import org.openqa.selenium.WebDriver;

public class AbstractSearchPageController {

    protected static final String FILTER_ITEM = "//a[contains(@class, 'filter-item')";

    protected WebDriver driver;
}
