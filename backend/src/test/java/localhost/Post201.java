package localhost;

import localhost.entities.Technology;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.MEDIA_TYPE_JSON;
import static org.testng.Assert.assertEquals;

public class Post201 extends AbstractLocalHostTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_CREATED;
    private static final String ENDPOINT = LOCALHOST_BASE + "technology/";

    @Test
    public void postRawJsonStringWorks() throws IOException {

        response = clive.sendRawPost(ENDPOINT, "{\"name\":\"New Mega tech\",\"description\": \"Will take over the world \"}", MEDIA_TYPE_JSON);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void postAsObjectWorks() throws IOException {

        Technology order = new Technology();
        order.setName("Hamster");
        order.setDescription("A general purpose programming language for all your daily needs.");

        response = clive.sendJsonPost(ENDPOINT, order);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
