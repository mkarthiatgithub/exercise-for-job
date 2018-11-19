package runsupport;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features = "classpath:features",
		plugin = {"pretty", "html:target/cucumber-html-reports", "json:target/cucumber.json"},
		glue = { "classpath:steps", "classpath:runsupport" },
		tags = {"@dvla"}
		)
public class RunCukesTest{
	
}