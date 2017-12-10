package localhost;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class DeleteTests extends AbstractLocalHostTest {

    @Test
    public void deletingValidResource() throws IOException {

        postWithNameAndDesc("Bash",
                "A language good for one-liners but in which one writes cryptic and unmaintainable mishmash " +
                        "(completely unbiased description)");

        response = clive.sendDelete(ENDPOINT + "Bash");
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, HttpStatus.SC_OK);
    }

    @Test
    public void deletingNonExistingResource() throws IOException {
        String randomString = RandomStringUtils.random(10);
        response = clive.sendDelete(ENDPOINT + randomString);

        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, HttpStatus.SC_NOT_FOUND);
    }
}