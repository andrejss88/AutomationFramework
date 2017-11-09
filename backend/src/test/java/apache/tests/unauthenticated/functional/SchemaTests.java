package apache.tests.unauthenticated.functional;

import apache.tests.AbstractTest;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.github.entities.generated.Repo;
import com.github.entities.manuallycreated.User;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.utils.MappingUtils.validateSchema;

public class SchemaTests extends AbstractTest {

    @Test(description = "User POJO is incomplete, so it is expected that unmarshalling will fail",
          expectedExceptions = UnrecognizedPropertyException.class)
    public void userSchemaIsCorrect() throws IOException {

        HttpGet httpget = new HttpGet(BASE_API_URL  + "users/andrejss88");
        response = client.execute(httpget);

        validateSchema(response, User.class);

    }

    @Test(description = "Repo POJO is complete. No exception thrown when unmarshalling is considered a successful test.")
    public void repoSchemaIsCorrect() throws IOException {

        HttpGet httpget = new HttpGet(BASE_API_URL  + "repos/andrejss88/AutomationFramework");
        response = client.execute(httpget);

        validateSchema(response, Repo.class);
    }
}
