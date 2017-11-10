package com.github.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.TestException;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HeaderUtils {

    private HeaderUtils(){}

    /**
     * Find a header from the entire response entity
     * @param response: the response object to be parsed
     * @param headerName: the header to search for
     * @return the value of the header
     */

    public static String getValueForHeaderOldWay(CloseableHttpResponse response, String headerName){
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

    public static String getValueForHeader(CloseableHttpResponse response, String headerName){
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        Header matchedHeader = httpHeaders.stream()
                .filter( header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst().orElseThrow(() -> new RuntimeException("Didn't find the header: " + headerName));

        return matchedHeader.getValue();
    }

    static HttpResponse executeAndGetResponse(URI uri) throws IOException {

        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            String reason = response.getStatusLine().getReasonPhrase();
            throw new TestException(String.format("Expected Status 200, but got: '%d' with reason: %s ", statusCode, reason));
        }
        return Objects.requireNonNull(response);
    }


}
