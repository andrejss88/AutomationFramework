package com.github.utils;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.Arrays;
import java.util.List;

public class UtilMethods {

    /**
     * Find a header from the entire response entity
     * @param response: the response object to be parsed
     * @param headerName: the header to search for
     * @return the value of the header
     */

    public static String getValueForHeader(CloseableHttpResponse response, String headerName){
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        String returnHeader = "";
        for (Header header : httpHeaders) {
            if(headerName.equalsIgnoreCase(header.getName())){
                returnHeader = header.getValue();
            }
        }

        if(returnHeader.isEmpty()){
            throw new RuntimeException("Didn't find the header: " + headerName);
        }

        return returnHeader;
    }

    /**
     * Same as above, but the Java8 way
     */

    public static String getValueForHeaderJava8Way(CloseableHttpResponse response, String headerName){
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        Header matchedHeader = httpHeaders.stream()
                .filter( header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst().orElseThrow(() -> new RuntimeException("Didn't find the header: " + headerName));

        return matchedHeader.getValue();
    }
}
