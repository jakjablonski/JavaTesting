package Selenium.Jbehave;


import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

import java.util.List;
import java.util.Locale;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.PerStoryWebDriverSteps;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.jbehave.web.selenium.TypeWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.jbehave.web.selenium.WebDriverSteps;
import org.openqa.selenium.chrome.ChromeDriver;


public class JbehaveConfigurationTest extends JUnitStories {
	
	private WebDriverProvider driverProvider = new TypeWebDriverProvider(ChromeDriver.class); // Pusty dla FirefoxDriver
    private WebDriverSteps lifecycleSteps = new PerStoryWebDriverSteps(driverProvider); // or PerStoryWebDriverSteps(driverProvider)
    private SeleniumContext context = new SeleniumContext();
    private ContextView contextView = new LocalFrameContextView().sized(500, 100);
    Keywords keywords = new LocalizedKeywords(new Locale("pl"));
    
    public JbehaveConfigurationTest() {
    	System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
	}

	 
    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        return new SeleniumConfiguration()
                .useSeleniumContext(context)
                .useWebDriverProvider(driverProvider)
                .useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
               //.useKeywords(keywords)
              // .useStoryParser(new RegexStoryParser(keywords))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                    .withCodeLocation(codeLocationFromClass(embeddableClass))
                    .withDefaultFormats()
                    .withFormats(Format.CONSOLE, Format.TXT));
    }
    @Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(
				codeLocationFromClass(this.getClass()).getFile(),
				asList("**/*.story"), null);
	}

    @Override
    public InjectableStepsFactory stepsFactory() {
        Configuration configuration = configuration();
        return new InstanceStepsFactory(configuration, 
                new JbehaveTest(driverProvider),
                lifecycleSteps,
                new WebDriverScreenshotOnFailure(driverProvider, configuration.storyReporterBuilder()));
    }

//    private StoryReporterBuilder getReporter(){
//		return new StoryReporterBuilder().withPathResolver(new FilePrintStreamFactory.ResolveToSimpleName()
//				).withDefaultFormats().withFormats(Format.CONSOLE, Format.HTML).withKeywords(keywords);
//	}

}
