package myRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

		plugin = { "pretty", "html:test-output", "json:target/cucumber.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, 		            
		features = {"src/test/java/appFeatures"},
		glue = {"appStepDefinitions","appHooks"},
		tags="@login",
		publish=true
		)

public class MyTestRunnerJunit {

}
