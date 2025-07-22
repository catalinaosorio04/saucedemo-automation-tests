package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import base.BaseTest;
import utils.ConfigReader;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());

        String browser = System.getProperty("browser",
                ConfigReader.getProperty("browser", "chrome"));

        BaseTest.initializeDriver(browser);

        System.out.println("Driver initialized for browser: " + browser);
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = BaseTest.getDriver();

        if (scenario.isFailed() && driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed_Screenshot");
                System.out.println("Screenshot captured for failed scenario: " + scenario.getName());
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }

        BaseTest.quitDriver();
        System.out.println("Finished scenario: " + scenario.getName());
    }
}