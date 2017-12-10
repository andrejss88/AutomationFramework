package localhost;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.MEDIA_TYPE_JSON;
import static org.testng.Assert.assertEquals;

public class PostTests extends AbstractLocalHostTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_CREATED;

    @Test
    public void postRawJsonStringWorks() throws IOException {

        response = clive.sendRawPost(ENDPOINT, "{\"name\":\"New Mega tech\",\"description\": \"Will take over the world \"}", MEDIA_TYPE_JSON);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void postAsObjectWorks() throws IOException {

        postWithNameAndDesc("Hamster",
                            "A general purpose programming language for all your daily needs.");

    }
}
