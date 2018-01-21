package unit;

import com.github.handlers.impl.DefaultResponseHandler;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicHeader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class DefaultResponseHandlerTest {

    private DefaultResponseHandler handler = new DefaultResponseHandler();
    private CloseableHttpResponse responseMock;
    private Header[] headers;

    @BeforeClass
    public void setupMock(){
        responseMock = mock(CloseableHttpResponse.class);
    }

    @Test
    public void getHeaderValueFindsExactMatch(){
        String expectedValue = "valueA";
        headers = new Header[] { new BasicHeader("headerA", expectedValue) };

        when(responseMock.getAllHeaders()).thenReturn(headers);

        String actualValue = handler.getHeaderValue(responseMock,"headerA");
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getHeaderValueFindsCaseSensitiveMatch(){
        String expectedValue = "valueA";
        headers = new Header[] { new BasicHeader("HeaderA", expectedValue) };

        when(responseMock.getAllHeaders()).thenReturn(headers);

        String actualValue = handler.getHeaderValue(responseMock,"headerA");
        assertEquals(actualValue, expectedValue);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void getHeaderValueThrowsNoMatchingHeader(){
        headers = new Header[] { new BasicHeader("headerA", "valueA") };

        when(responseMock.getAllHeaders()).thenReturn(headers);

        handler.getHeaderValue(responseMock,"nonExistingHeader");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void getHeaderValueThrowsEmptyArray(){

        when(responseMock.getAllHeaders()).thenReturn(new Header[]{});

        handler.getHeaderValue(responseMock,"someHeader");
    }
}
