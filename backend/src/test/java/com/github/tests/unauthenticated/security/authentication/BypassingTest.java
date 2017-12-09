package com.github.tests.unauthenticated.security.authentication;

import com.github.dataproviders.EndPointDataProviders;
import com.github.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

public class BypassingTest extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNAUTHORIZED;

    @Test(description = "HEAD instead of GET doesn't allow to view content",
          dataProvider = "endPointsRequiringAuthorization", dataProviderClass = EndPointDataProviders.class)
    public void headFailsOnSecureEndpoints(String endpoint) throws IOException {

        response = clive.sendHead(BASE_API_URL  + endpoint);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
