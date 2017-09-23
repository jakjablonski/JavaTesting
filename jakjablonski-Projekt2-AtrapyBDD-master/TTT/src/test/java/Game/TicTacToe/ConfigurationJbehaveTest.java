package Game.TicTacToe;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterControls;

public class ConfigurationJbehaveTest extends JUnitStories {
	
	Keywords keywords = new LocalizedKeywords(new Locale("pl"));
	 
    @Override
    public Configuration configuration()
    {
            Configuration configuration = new MostUsefulConfiguration();

            configuration.useKeywords(keywords)
                    .useStoryParser(new RegexStoryParser(keywords))
                    .useStoryReporterBuilder(getReporter())
                    .useStoryLoader(new LoadFromURL());
            		

            return  configuration;
    }
	@Override
	protected List<String> storyPaths() {
		String path = "stories/**/*.story";
		return new StoryFinder().findPaths(CodeLocations.codeLocationFromPath("").getFile(),
				Collections.singletonList(path),
				new ArrayList<String>(), "file:");
	}

	@Override 
	public InjectableStepsFactory stepsFactory(){
		return new InstanceStepsFactory(configuration(), new TTTScenarioTest(),new TTTParametrScenarioTest());
	}
	
	private StoryReporterBuilder getReporter(){
		return new StoryReporterBuilder().withPathResolver(new FilePrintStreamFactory.ResolveToSimpleName()
				).withDefaultFormats().withFormats(Format.CONSOLE, Format.HTML).withKeywords(keywords);
	}
}
