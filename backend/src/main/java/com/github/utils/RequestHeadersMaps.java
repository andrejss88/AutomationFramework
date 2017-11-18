package com.github.utils;

import org.apache.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

public class RequestHeadersMaps {

    public static Map<String, String> xmlContentMap = new HashMap<>();
    public static Map<String, String> jsonContent = new HashMap<>();

    static {
        xmlContentMap.put(HttpHeaders.ACCEPT, "application/xml");
        xmlContentMap.put(HttpHeaders.CONTENT_TYPE, "application/xml");

        jsonContent.put(HttpHeaders.ACCEPT, "application/json");
        jsonContent.put(HttpHeaders.CONTENT_TYPE, "application/json");
    }

}
