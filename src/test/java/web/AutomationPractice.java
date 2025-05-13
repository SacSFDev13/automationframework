package web;

import base.WebUIBaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.util.TimeUtils;
import pages.Homepage;
import util.RetryFailedTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class AutomationPractice extends WebUIBaseTest {

  @Test
  public void searchProduct() {
    logger.info("Search Product Test started");
    driver.get().get("https://automationexercise.com/");
    Homepage homepage = new Homepage(driver.get());
    homepage.isFocusOnHomePage();

  }

}

 /*@Ignore
@Test //(dataProvider = "NameData", dataProviderClass = FlipkartTest.class, retryAnalyzer = RetryFailedTest.class) //dataProvider = "NameData", dataProviderClass = FlipkartTest.class, retryAnalyzer = RetryFailedTest.class
  public void registerUser(Method method) throws InterruptedException {
  driver.get().get("http://google.com");
  System.out.println(method.getName());
  System.out.println(Thread.currentThread().getId() + "  --> " + driver.get().toString());
 // assert
      //driver.get().findElement(By.xpath("//div[contains(@class,\"shop-menu\")]/ul/li/a[contains(text(), \"Home\")]")).isDisplayed();
  *//*driver.findElement(By.xpath("//div[contains(@class,\"shop-menu\")]/ul/li/a[contains(text(), \"Products\")]")).click();
  File file = driver.findElement(By.xpath("//div[contains(@class,\"shop-menu\")]/ul/li/a[contains(text(), \"Products\")]")).getScreenshotAs(OutputType.FILE);
  File desFile = new File(System.getProperty("user.dir") + "\\Screenshots\\elementscreenshot.png");
  try {
    FileUtils.copyFile(file, desFile);
  } catch (IOException e) {
    e.printStackTrace();
  }*//*
  Thread.sleep(1000);
  Reporter.log("Sample report log on Test method");
  Assert.assertEquals(1, 1);
}

@Ignore
@Test(expectedExceptions =  {AssertionError.class})
public void test3() {
  System.out.println(System.getProperty("Test"));
  Assert.assertEquals(1, 2);
}

@Ignore
@Test
public void test2() throws InterruptedException {
  driver.get().get("http://automationexercise.com");
  LocalTime currentTime = LocalTime.now();
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss SSS");
  String formattedTime = currentTime.format(formatter);
  System.out.println("Current time: " + formattedTime);
  System.out.println(Thread.currentThread().getId() + "  --> " + driver.get().toString());
  List<WebElement> products = driver.get().findElements(By.cssSelector("div.features_items div.single-products"));
      //driver.get().findElement(By.xpath("//div[contains(@class,\"shop-menu\")]/ul/li/a[contains(text(), \"Products\")]")).click();
  LocalTime afterTime = LocalTime.now();
  String formattedTime1 = currentTime.format(formatter);
  System.out.println("After time: " + formattedTime1);
  Reporter.log("Sample report log on Test method");
  System.out.println(products.size());
  Assert.assertEquals(products.size(), 34);
}*/

