package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)

@CucumberOptions(
		features = "./src/test/resources/features/movies.feature",
		
		glue={"movieStepDefinition"}, 
		dryRun = false	
		)


public class TestRunner {

}
