package com.github.utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertUtil {

    public static List<String> convertToStringList(List<WebElement> list) {
        return list.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public static List<Double> convertToDoubleList(List<WebElement> list) {
        return list.stream()
                .map(WebElement::getText)
                .map(s -> s.replaceAll("[^0-9]", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public static int stringToNumber(String str) {
        return Integer.parseInt(str.replaceAll("[^0-9]", ""));
    }
}
