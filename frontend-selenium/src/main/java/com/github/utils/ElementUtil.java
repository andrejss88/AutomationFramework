package com.github.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class ElementUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElementUtil.class);


    public static boolean checkElementIsDisplayed(WebElement element) {
        String elemInfo = getElementInfo(element);
        LOGGER.info("Trying to check that element '{}' is displayed", elemInfo);
        try {
            element.isDisplayed();
            LOGGER.info("Element is displayed");
            return true;
        } catch (NoSuchElementException e) {
            LOGGER.error("Finding element failed: {}", elemInfo, e);
            return false;
        }
    }

    public static void checkElementIsEnabled(WebElement element, boolean shouldBeEnabled) {
        String elemInfo = getElementInfo(element);
        LOGGER.info("Checking that the element '{}' is enabled", elemInfo);
        boolean elementState = element.isEnabled();
        LOGGER.info("Element is enabled");
        Assert.assertEquals(elementState, shouldBeEnabled, "The element was expected to be Enabled: " + shouldBeEnabled + " but found Enabled: " + elementState);
    }

    /**
     * Simple Element Info builder, i.e. "custom toString()" for WebElements
     * Can (and should) be made more complex to account for CSS values and other possibilities
     * @param element: WebElement to gather info on
     * @return Useful info on the element to easily identify it in page source code
     */
    private static String getElementInfo(WebElement element) {

        String logMsg = "";

        String id = element.getAttribute("id");
        String text = element.getText();
        String tag = element.getTagName();

        if(!(id == null || id.isEmpty())){
            logMsg += "ID: '" + id +"'; ";
        }
        if (!(text == null || text.isEmpty())){
            logMsg += "Text: '" + text +"'; ";
        }

        logMsg += "Tag: '" + tag +"' ";

        return logMsg;
    }
}
