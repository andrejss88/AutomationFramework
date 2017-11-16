package apache.tests.unauthenticated.functional.metadata;

import apache.tests.AbstractTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.*;

public class HeadersTest extends AbstractTest {

    private static final String VALID_ENDPOINT = "users/andrejss88";
    private static final String URL = BASE_API_URL  + VALID_ENDPOINT;

    @BeforeClass
    public void sendAndGetResponse() throws IOException{ 
        response = clive.sendGet(URL);
    }

    @Test
    public void mediaTypeIsJson() {

        String jsonMimeType = "application/json";
        String actualMimeType = rob.getMimeType(response);

        assertEquals( jsonMimeType, actualMimeType );
    }

    @Test
    public void mediaTypeIsNotXml() {

        String jsonMimeType = "application/xml";
        String actualMimeType = rob.getMimeType(response);

        assertNotEquals( jsonMimeType, actualMimeType );
    }

    @Test
    public void charSetIsUtf8() {

        String expectedCharset = "utf-8";
        String actualCharset = rob.getCharSet(response);

        assertTrue(expectedCharset.equalsIgnoreCase(actualCharset), "Returned charset is not utf-8");
    }
}
