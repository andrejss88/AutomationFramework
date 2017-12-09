package localhost;

import com.github.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class AbstractLocalHostTest extends AbstractTest {

    protected static final String LOCALHOST_BASE = "http://localhost:8080/services/";

    @BeforeClass
    public void beforeSuiteCheck(){
        checkLocalHostIsRunning();
    }

    private void checkLocalHostIsRunning(){
        try {
            response = clive.sendGet(LOCALHOST_BASE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int responseCode = rob.getStatusCode(response);
        if(responseCode != HttpStatus.SC_OK){
            throw new SkipException("The service is unreachable. " +
                    "Has the rest app (restapi module of this project) been deployed and is localhost running?");
        }
    }
}
