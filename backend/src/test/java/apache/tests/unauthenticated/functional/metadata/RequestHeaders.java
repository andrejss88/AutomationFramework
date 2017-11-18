package apache.tests.unauthenticated.functional.metadata;

import apache.tests.AbstractTest;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class RequestHeaders extends AbstractTest {

    @Test
    public void invalidContentEncodingDoesNotBreakApi() throws IOException{
        HttpUriRequest request = clive.buildCustomRequest("GET")
                .setUri(BASE_API_URL)
                 // you can use https://requestb.in to check that the header went through correctly
                .setHeader(HttpHeaders.CONTENT_ENCODING, "Yay")
                .build();

        response = clive.send(request);
    }

}
