package apache.tests.unauthenticated.statuscodes.status2xx;

import apache.tests.AbstractTest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

public class Get204 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_NO_CONTENT;

    @BeforeClass
    public void sendAndGetResponse() throws IOException{
        response = clive.sendOptions(BASE_API_URL);
    }

    @Test
    public void optionsReturns204() throws IOException{

        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void contentTypeIsOctetStream(){

        String header = HttpHeaders.CONTENT_TYPE;
        String expectedContentType = "application/octet-stream";
        String actualContentType = rob.getHeaderValue(response, header);

        assertEquals(actualContentType, expectedContentType);
    }

    @Test
    public void allowedMethodListIsCorrect(){
        String header = "Access-Control-Allow-Methods";
        String expectedContentType = "GET, POST, PATCH, PUT, DELETE";

        String actualContentType = rob.getHeaderValue(response, header);

        assertEquals(actualContentType, expectedContentType);
    }
}
