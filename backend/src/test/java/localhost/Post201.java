package localhost;

import localhost.entities.Order;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.github.Constants.MEDIA_TYPE_JSON;
import static org.testng.Assert.assertEquals;

public class Post201 extends AbstractLocalHostTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_CREATED;
    private static final String ENDPOINT = LOCALHOST_BASE + "orders/";

    @Test
    public void postRawJsonStringWorks() throws IOException {

        response = clive.sendRawPost(ENDPOINT, "{\"desc\":\"Clive Mega order\",\"items\": null}", MEDIA_TYPE_JSON);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void postAsObjectWorks() throws IOException {

        Order order = new Order();
        order.setDesc("A new order for chocolate");
        order.setItems(new ArrayList<>(Arrays.asList("Chocolate", "More chocolate")));

        response = clive.sendJsonPost(ENDPOINT, order);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
