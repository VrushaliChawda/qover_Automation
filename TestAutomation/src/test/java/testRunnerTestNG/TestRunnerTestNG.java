package testRunnerTestNG;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/BikeInsuranceQuoteGeneration.feature", glue = "step_Definitions",
        plugin = "html:target/cucumber.html")
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {

}
