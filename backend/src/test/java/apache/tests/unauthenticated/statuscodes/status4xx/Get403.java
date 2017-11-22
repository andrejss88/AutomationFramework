package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.AbstractTest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpUriRequest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;


public class Get403 extends AbstractTest{

    private static final int EXPECTED_STATUS = HttpStatus.SC_FORBIDDEN;

    @DataProvider
    public static Object[][] endPoints() {
        return new Object[][]{
                // requiring authorization
                {"user"},

                // not requiring authorization
                {"users/andrejss88"}
        };
    }

    @Test(dataProvider = "endPoints")
    public void debugMethodIsForbidden(String endpoint) throws IOException {

        HttpUriRequest request = clive.buildCustomRequest("DEBUG")
                .setUri(BASE_API_URL + endpoint)
                .setHeader(HttpHeaders.ACCEPT, "application/json")
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();

        response = clive.send(request);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void randomMethodIsForbidden() throws IOException {

        HttpUriRequest request = clive.buildCustomRequest("MUSHROOM") // because why not
                .setUri(BASE_API_URL)
                .setHeader(HttpHeaders.ACCEPT, "application/json")
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();

        response = clive.send(request);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
