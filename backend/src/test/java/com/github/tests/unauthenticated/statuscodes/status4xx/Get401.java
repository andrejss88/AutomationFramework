package com.github.tests.unauthenticated.statuscodes.status4xx;

import com.github.dataproviders.EndPointDataProviders;
import com.github.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

/**
 * 'Unauthorized' actually means here 'Unauthenticated'
 * See https://stackoverflow.com/questions/3297048/403-forbidden-vs-401-unauthorized-http-responses
 */
public class Get401 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNAUTHORIZED;

    @Test(dataProvider = "endPointsRequiringAuthorization", dataProviderClass = EndPointDataProviders.class)
    public void unauthorizedEndpointsReturn401(String endpoint) throws IOException{
        
        response = clive.sendGet(BASE_API_URL  + endpoint);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }




}
