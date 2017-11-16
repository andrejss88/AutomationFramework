package apache.tests.unauthenticated.statuscodes.status2xx;

import apache.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

public class Get200 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_OK;

    @Test
    public void baseUrlRequestReturns200() throws IOException {
        
        response = clive.sendGet(BASE_API_URL);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void searchRequestReturns200() throws IOException {

        response = clive.sendGet(BASE_API_URL + "search/repositories?q=java");
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void clientIsRedirected() throws IOException {
        response = clive.sendGet(BASE_API_URL  + "repos/twitter/bootstrap");
        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

}
