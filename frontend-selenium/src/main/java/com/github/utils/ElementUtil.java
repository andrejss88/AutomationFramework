package com.github.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class ElementUtil {

    static final Logger log = LoggerFactory.getLogger(ElementUtil.class);


    public static boolean checkElementIsDisplayed(WebElement element) {
        log.info("Trying to check that an element is displayed");
        try {
            element.isDisplayed();
            log.info("Element is displayed");
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void checkElementEnabled(WebElement element, boolean shouldBeEnabled) {
        log.info("Checking that the element is enabled");
        boolean elementState = element.isEnabled();
        log.info("Element is enabled");
        Assert.assertEquals(elementState, shouldBeEnabled, "The element was expected to be Enabled: " + shouldBeEnabled + " but found Enabled: " + elementState);
    }

}
