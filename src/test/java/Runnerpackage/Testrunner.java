package Runnerpackage;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;





@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\Public\\eclipse\\workspace\\Autotradercalculator\\src\\test\\java\\featurefile",glue={"StepDefinition"}
		)

public class Testrunner {

}
