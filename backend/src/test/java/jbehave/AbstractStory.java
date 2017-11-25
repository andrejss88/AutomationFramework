package jbehave;

import com.github.handlers.RequestHandler;
import com.github.handlers.ResponseHandler;
import com.github.handlers.impl.DefaultRequestHandler;
import com.github.handlers.impl.DefaultResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.After;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;

/**
 * The tests that extend the class are the same as written in the main package
 * But using jBehave BDD framework.
 */
public abstract class AbstractStory extends JUnitStories {

    protected CloseableHttpResponse response;

    protected RequestHandler clive = new DefaultRequestHandler();

    protected ResponseHandler rob = new DefaultResponseHandler();

    @After
    public void after() throws IllegalStateException, IOException {
        rob.closeResponse(response);
        clive.close();
    }

    protected abstract String storyName();

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
          .useStoryLoader(new LoadFromClasspath(this.getClass()))
          .useStoryReporterBuilder(new StoryReporterBuilder()
            .withCodeLocation(codeLocationFromClass(this.getClass()))
            .withFormats(CONSOLE));
    }

    protected abstract Object stepInstance();

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), stepInstance());
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList(storyName());
    }

}
