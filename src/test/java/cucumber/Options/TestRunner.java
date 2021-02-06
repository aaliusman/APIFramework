package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
        glue = {"stepDefinitions", "myHooks"},
        plugin = {"pretty", "json:target/jsonReports/cucumber-report.json"},
        monochrome = true,
        strict = true,
        dryRun = false
//        tags = ("@DeletePlace")
//        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
//        "json:target/jsonReports/cucumber-report.json"
        )
public class TestRunner {

}
