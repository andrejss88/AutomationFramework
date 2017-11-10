package apache.tests.unauthenticated.statuscodes.status2xx;

import com.github.utils.ResponseUtil;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.utils.UtilMethods.getValueForHeader;

public class Get204 {

    private static final int EXPECTED_STATUS = HttpStatus.SC_NO_CONTENT;

    private CloseableHttpClient client;
    private CloseableHttpResponse response;

    @BeforeClass
    public void sendAndGetResponse() throws IOException{

        client = HttpClientBuilder.create().build();

        HttpOptions httpOptions = new HttpOptions(BASE_API_URL);
        response = client.execute(httpOptions);
    }

    @Test
    public void optionsReturns204() throws IOException{

        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void contentTypeIsOctetStream(){

        String header = HttpHeaders.CONTENT_TYPE;
        String expectedContentType = "application/octet-stream";
        String actualContentType = getValueForHeader(response, header);

        Assert.assertEquals(actualContentType, expectedContentType);
    }

    @Test
    public void allowedMethodListIsCorrect(){
        String header = "Access-Control-Allow-Methods";
        String expectedContentType = "GET, POST, PATCH, PUT, DELETE";

        String actualContentType = getValueForHeader(response, header);

        Assert.assertEquals(actualContentType, expectedContentType);
    }

    @AfterClass
    public void after() throws IllegalStateException, IOException {
        ResponseUtil.closeResponse(response);
        client.close();
    }

}
