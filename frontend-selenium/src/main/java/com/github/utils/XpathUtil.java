package com.github.utils;

public class XpathUtil {

    public static String tabXpath(String tabName){
        return "//a[contains(@class, 'UnderlineNav-item')" +
                "  and text()[normalize-space() = '" + tabName + "']]";
    }
}
