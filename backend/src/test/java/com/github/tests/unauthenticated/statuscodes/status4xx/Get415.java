package com.github.tests.unauthenticated.statuscodes.status4xx;

import com.github.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.utils.RequestHeadersLists.unknownMediaTypeList;
import static com.github.utils.RequestHeadersLists.xmlContentList;
import static com.github.utils.RequestHeadersMaps.xmlContentMap;
import static org.testng.Assert.assertEquals;

public class Get415 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE;
    private static final String URL = BASE_API_URL  + "users/andrejss88";

    @Test(description = "Use generic send request and pass in a map")
    public void xmlIsUnsupportedUseMap() throws IOException {

        response = clive.sendRequestWithHeaders(HttpGet.class, URL, xmlContentMap);

        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test(description = "Use generic send request and pass in a List of BasicHeaders defined by Apache")
    public void xmlIsUnsupportedUseList() throws IOException {

        response = clive.sendRequestWithHeaders(HttpGet.class, URL, xmlContentList);

        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test(description = "Send an unknown media type as accepted content type")
    public void unknownMediaTypeUnsupported() throws IOException {

        response = clive.sendRequestWithHeaders(HttpGet.class, URL, unknownMediaTypeList);

        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
