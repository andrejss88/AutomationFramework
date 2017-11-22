package com.github.utils;

import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for the overriden method 'sendRequestWithHeaders()' that takes a list
 * This is pretty much duplicate solution to maps.
 * I just can't decide which one is better - using maps or lists of headers
 */
public class RequestHeadersLists {

    public static List<BasicHeader> xmlContentList = new ArrayList<>();
    public static List<BasicHeader> unknownMediaTypeList = new ArrayList<>();

    static {
        xmlContentList.add(new BasicHeader(HttpHeaders.ACCEPT, "application/xml"));
        xmlContentList.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/xml"));

        unknownMediaTypeList.add(new BasicHeader(HttpHeaders.ACCEPT, "application/mySpecialFormat"));
        unknownMediaTypeList.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/mySpecialFormat"));
    }

}
