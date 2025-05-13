package web;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class FlipkartTest {

  @DataProvider(name = "NameData")
  public Object[][] DataSource() throws NoSuchMethodException {
    return new Object[][] {
        { AutomationPractice.class.getMethod("registerUser", Method.class) }
    };
  }

}
