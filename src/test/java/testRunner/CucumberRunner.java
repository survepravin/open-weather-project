package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html-report",
		"json:target/JsonReports/cucumber-report.json" }, features = "src/test/java/features", glue = {
				"stepDefinitions" }, tags = { "@end2end" })
public class CucumberRunner {

}
