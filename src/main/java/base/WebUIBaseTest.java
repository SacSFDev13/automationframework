package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class WebUIBaseTest {
  protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
  public static final Logger logger = LoggerFactory.getLogger(WebUIBaseTest.class);

  @BeforeMethod
  public void setup() {
    WebDriverManager.chromedriver().setup();
    driver.set(new ChromeDriver());
    driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get().manage().window().maximize();
    logger.info("Driver initialised");
  }

  @AfterMethod
  public void teardownMethod(ITestResult result) {
    if (ITestResult.FAILURE == result.getStatus()) {
      captureScreenshot(result);
    }
    if (driver.get() != null) {
      driver.get().close();
      driver.remove();
    }
  }

  @AfterSuite
  public void teardownTest() {
    //if (driver != null) driver.get().quit();
  }

  public static void captureScreenshot(ITestResult result) {
    String outputDirPath = result.getTestContext().getOutputDirectory().substring(0,
        result.getTestContext().getOutputDirectory().lastIndexOf('\\'));
    String testName = result.getName();
    File file = ((TakesScreenshot)driver.get()).getScreenshotAs(OutputType.FILE);
    File desFile = new File(outputDirPath + "\\Screenshots\\" + testName + "_test.png");
    try {
      FileUtils.copyFile(file, desFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
