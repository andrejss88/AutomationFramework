package com.github.pages.common;

import com.github.pages.AbstractGitHubPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class CommonFooterElements extends AbstractGitHubPage {

    /**
     * Implement footer elements (links) the same way CommonHeaderElements is.
     * Footer and Header classes could be a single class
     * but in big projects it's best to keep classes as small as possible
     *
     */

    // Element locators using alternative methods

    @FindBy(how = How.LINK_TEXT, using = "About")
    private WebElement aboutLink;

    /**
     * Demonstration how to grab elements by Xpath
     * Brittle, but a solution when there are no IDs or CLASSes
     * (happens a lot in real world apps)
     */
    @FindBy(how = How.XPATH, using = "//ul[contains(@class,'site-footer-links')]/li/a[contains(text(),'Privacy')]")
    private WebElement privacyLink;



}
