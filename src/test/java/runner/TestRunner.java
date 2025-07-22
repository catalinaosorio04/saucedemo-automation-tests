package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        plugin = {
                "pretty",
                "html:reports/cucumber-reports/cucumber-html-report.html",
                "json:reports/cucumber-reports/cucumber.json",
                "junit:reports/cucumber-reports/cucumber.xml"
        },
        monochrome = false,
        publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}