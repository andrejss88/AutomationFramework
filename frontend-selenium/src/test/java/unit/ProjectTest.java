package unit;

import com.github.CommonConstants;
import com.github.utils.ProjectUtil;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Often it is necessary to define rules how the team should code.
 * Example rules can be: 'No duplicate @Test method names' or 'All @Tests must have descriptions'
 * To enforce this, reflection-powered tests can be implemented
 */
public class ProjectTest {

    private String baseDir = CommonConstants.USER_DIR + "\\src\\test\\java\\";

    @Test(description = "Enforces that all methods in the module have unique names")
    public void checkUniqueMethodNames() throws IOException, ClassNotFoundException {
        ProjectUtil.checkUniqueMethodNames(baseDir);
    }

    /**
     * Set 'enabled = true' and run - it will fails displaying methods with no description
     */

    @Test(description = "Enforces that all methods have descriptions", enabled = false)
    public void checkMethodHasDescription() throws IOException, ClassNotFoundException {
        ProjectUtil.checkMethodHasDescription(baseDir);
    }
}
