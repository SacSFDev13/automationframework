package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WebUIBaseTest {
  protected WebDriver driver;

  @BeforeMethod
  public void setup() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }

  @AfterMethod
  public void teardown() {
    if (driver != null) driver.quit();
  }
}
