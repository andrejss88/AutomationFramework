package localhost;

import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get200 extends AbstractLocalHostTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_OK;

    @Test
    public void baseUrlRequestReturns200() throws IOException {
        
        response = clive.sendGet(LOCALHOST_BASE + "orders/");
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void printReturnedOrder() throws IOException {

        response = clive.sendGet(LOCALHOST_BASE + "1");
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

}
