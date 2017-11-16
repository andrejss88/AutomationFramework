package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

public class Get404 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_NOT_FOUND;

    @Test
    public void getTeamsWhenUnauthorized() throws IOException {

        response = clive.sendGet(BASE_API_URL  + "teams");
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
