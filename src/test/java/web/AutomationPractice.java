package web;

import base.WebUIBaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RetryFailedTest;

import java.io.File;
import java.io.IOException;

public class AutomationPractice extends WebUIBaseTest {

@Test(retryAnalyzer = RetryFailedTest.class) //dataProvider = "NameData", dataProviderClass = FlipkartTest.class
  public void registerUser() {
  driver.get("http://automationexercise.com");
  assert
      driver.findElement(By.xpath("//div[contains(@class,\"shop-menu\")]/ul/li/a[contains(text(), \"Home\")]")).isDisplayed();
  /*driver.findElement(By.xpath("//div[contains(@class,\"shop-menu\")]/ul/li/a[contains(text(), \"Products\")]")).click();
  File file = driver.findElement(By.xpath("//div[contains(@class,\"shop-menu\")]/ul/li/a[contains(text(), \"Products\")]")).getScreenshotAs(OutputType.FILE);
  File desFile = new File(System.getProperty("user.dir") + "\\Screenshots\\elementscreenshot.png");
  try {
    FileUtils.copyFile(file, desFile);
  } catch (IOException e) {
    e.printStackTrace();
  }*/
  Assert.assertEquals(1, 0);
}
}

