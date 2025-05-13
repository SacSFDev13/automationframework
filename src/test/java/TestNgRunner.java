import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepdefinitions",
    monochrome = true,
    plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class TestNgRunner extends AbstractTestNGCucumberTests {
}