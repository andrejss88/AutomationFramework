package localhost;

import com.github.tests.AbstractTest;
import localhost.entities.Technology;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class AbstractLocalHostTest extends AbstractTest {

    private static final String LOCALHOST_BASE = "http://localhost:8080/services/";
    protected static final String ENDPOINT = LOCALHOST_BASE + "technology/";

    protected void postWithNameAndDesc(String name, String desc) throws IOException {
        Technology order = new Technology();
        order.setName(name);
        order.setDescription(desc);

        response = clive.sendJsonPost(ENDPOINT, order);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, HttpStatus.SC_CREATED);
    }


    @BeforeClass
    public void beforeSuiteCheck() throws IOException {
        checkLocalHostIsRunning();
        sendSanityRequestCheck();
        releaseConnection();
    }

    /**
     * There is a limit of concurrent connections set in default client in ClientFactory
     * If that limit is still not enough - an alternative solution is to quietly consume the response
     * -> https://stackoverflow.com/questions/11875015/httpclient-exception-org-apache-http-conn-connectionpooltimeoutexception-timeo
     */
    private void releaseConnection() {
        EntityUtils.consumeQuietly(response.getEntity());
    }

    private void sendSanityRequestCheck() throws IOException {
        response = clive.sendGet(ENDPOINT);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, HttpStatus.SC_OK);
    }


    private void checkLocalHostIsRunning() throws IOException {
        response = clive.sendGet(LOCALHOST_BASE);

        int responseCode = rob.getStatusCode(response);
        if(responseCode != HttpStatus.SC_OK){
            throw new SkipException("The service is unreachable. " +
                    "Has the rest app (restapi module of this project) been deployed and is localhost running?");
        }
    }
}
