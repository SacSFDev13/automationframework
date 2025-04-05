package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileBaseTest {
  protected AppiumDriver driver;

  @BeforeMethod
  public void setup() throws MalformedURLException {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("platformName", "Android");
    caps.setCapability("deviceName", "emulator-5554");
    caps.setCapability("appPackage", "com.example");
    caps.setCapability("appActivity", "com.example.MainActivity");
    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
  }

  @AfterMethod
  public void teardown() {
    if (driver != null) driver.quit();
  }
}
