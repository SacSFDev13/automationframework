package web.orangehrm;

import base.WebUIBaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.orangehrm.LoginPage;

public class OrangeHrmBaseTest extends WebUIBaseTest {

  @BeforeMethod
  public void setUpApplication() {
    logger.info("Launch Application");
    driver.get().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
  }

  @Test
  public void loginTest() {
    LoginPage loginPage = new LoginPage(driver.get());
    loginPage.login("RahulDas123", "Rahuldas@123");
  }

  @AfterMethod
  public void closeApplication() {
    //driver.get().close();
  }


}
