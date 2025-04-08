package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class WebUIBaseTest {
  protected static WebDriver driver;

  @BeforeMethod
  public void setup() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }

  @AfterMethod
  public void teardown(ITestResult result) {
    if (ITestResult.FAILURE == result.getStatus()) {
      captureScreenshot(result);
    }
    if (driver != null) driver.quit();
  }

  public static void captureScreenshot(ITestResult result) {
    String outputDirPath = result.getTestContext().getOutputDirectory().substring(0,
        result.getTestContext().getOutputDirectory().lastIndexOf('\\'));
    String testName = result.getName();
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    File desFile = new File(outputDirPath + "\\Screenshots\\" + testName + "_test.png");
    try {
      FileUtils.copyFile(file, desFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
