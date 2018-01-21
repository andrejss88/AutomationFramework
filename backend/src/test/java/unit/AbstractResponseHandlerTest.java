package unit;

import com.github.handlers.impl.AbstractResponseHandler;
import com.github.handlers.impl.DefaultResponseHandler;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicHeader;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class AbstractResponseHandlerTest {

    private AbstractResponseHandler handler = new DefaultResponseHandler();
    private CloseableHttpResponse responseMock;


    @BeforeMethod
    public void setupMock(){
        responseMock = mock(CloseableHttpResponse.class, Mockito.RETURNS_DEEP_STUBS);
    }

    @Test
    public void getStatusCodeTest(){

        when(responseMock.getStatusLine().getStatusCode()).thenReturn(200);

        assertEquals(handler.getStatusCode(responseMock), 200);
    }

    @Test
    public void headerIsPresentReturnsFalse(){
        Header[] headers = new Header[] { new BasicHeader("headerA", "valueA") };

        when(responseMock.getAllHeaders()).thenReturn(headers);

        assertFalse(handler.headerIsPresent(responseMock, "headerB"));
    }

    @Test
    public void headerIsPresentReturnsFalseWhenEmptyArray(){
        Header[] headers = new Header[]{};

        when(responseMock.getAllHeaders()).thenReturn(headers);

        assertFalse(handler.headerIsPresent(responseMock, "headerA"));
    }

    @Test
    public void headerIsPresentReturnsTrueSingleValue(){
        Header[] headers = new Header[] { new BasicHeader("headerA", "valueA") };

        when(responseMock.getAllHeaders()).thenReturn(headers);

        assertTrue(handler.headerIsPresent(responseMock, "headerA"));
    }

    @Test
    public void headerIsPresentReturnsTrueMultipleValues(){
        Header[] headers = new Header[] {
                new BasicHeader("headerA", "valueA"),
                new BasicHeader("headerB", "valueB") };

        when(responseMock.getAllHeaders()).thenReturn(headers);

        assertTrue(handler.headerIsPresent(responseMock, "headerB"));
    }

}
