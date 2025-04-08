package web;

import org.testng.annotations.DataProvider;

public class FlipkartTest {

  @DataProvider(name = "NameData")
  public Object[][] DataSource() {
    return new Object[][] {
        {"John"},
        {"Kerry"}
    };
  }

}
