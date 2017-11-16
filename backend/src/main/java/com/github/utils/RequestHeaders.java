package com.github.utils;

import org.apache.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

public class RequestHeaders {

    public static Map<String, String> xmlContent = new HashMap<>();
    public static Map<String, String> jsonContent = new HashMap<>();

    static {
        xmlContent.put(HttpHeaders.ACCEPT, "application/xml");
        xmlContent.put(HttpHeaders.CONTENT_TYPE, "application/xml");

        jsonContent.put(HttpHeaders.ACCEPT, "application/json");
        jsonContent.put(HttpHeaders.CONTENT_TYPE, "application/json");
    }

}
