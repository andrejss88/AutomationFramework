package com.github.tests.unauthenticated.statuscodes.status4xx;

import com.github.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

public class Get405 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_METHOD_NOT_ALLOWED;

    // https://security.stackexchange.com/questions/56955/is-the-http-trace-method-a-security-vulnerability
    @Test(description = "Trace is a valid HTTP method but allowing it is considered a security vulnerability")
    public void traceNotAllowed() throws IOException{
        
        response = clive.sendTrace(BASE_API_URL);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test(description = "Non-standard or random methods must not break the API")
    public void nonStandardMethodNotAllowed() throws IOException{

        response = clive.sendTrace(BASE_API_URL);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }




}
