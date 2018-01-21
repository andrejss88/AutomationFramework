package com.github.utils;

public class XpathUtil {

    public static String sideNav(String tabName){
        return "//a[contains(@class, 'menu-item')" +
                "  and text()[normalize-space() = '" + tabName + "']]";
    }

    public static String topNav(String tabName){
        return "//a[contains(@class, 'UnderlineNav-item')" +
                "  and text()[normalize-space() = '" + tabName + "']]";
    }
}
