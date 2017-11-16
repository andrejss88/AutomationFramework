package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.AbstractTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

public class Get414 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_REQUEST_URI_TOO_LONG;

    @Test
    public void uriTooLong() throws IOException {
        String uri = RandomStringUtils.randomAlphabetic(5000);

        response = clive.sendGet(BASE_API_URL  + "users/" + uri);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
