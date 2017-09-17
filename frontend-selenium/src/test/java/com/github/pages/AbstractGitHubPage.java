package com.github.pages;

import com.github.setup.PropertiesFactory;
import org.openqa.selenium.WebDriver;

public abstract class AbstractGitHubPage {

    protected final static String BASE_URL = PropertiesFactory.loadBaseUrl();
    private final static String TITLE = "GitHub";

    /**
     * Shared between all pages, avoids declaring in each Page Object
     */

    protected WebDriver driver;

    public boolean isPageOpened() {
        return driver.getTitle().contains(TITLE);
    }
}
