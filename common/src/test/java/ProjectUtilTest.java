import com.github.CommonConstants;
import com.github.utils.ProjectUtil;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProjectUtilTest {

    private String baseDir = CommonConstants.USER_DIR + "\\src\\test\\java\\";


    @Test(expectedExceptions = AssertionError.class)
    public void checkUniqueMethodNamesFails() throws IOException, ClassNotFoundException {

        ProjectUtil.checkUniqueMethodNames(baseDir);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void checkMethodHasDescriptionFails() throws IOException, ClassNotFoundException {
        ProjectUtil.checkMethodHasDescription(baseDir);
    }

}
