package pages.orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

  private By usernameText = By.xpath("//div/input[@name='username']");
  private By passwordText = By.xpath("//div/input[@name='password']");
  private By loginButton = By.xpath("//div/button[@type='submit']");
  private WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  public void login(String username, String password) {
    driver.findElement(usernameText).sendKeys(username);
    driver.findElement(passwordText).sendKeys(password);
    driver.findElement(loginButton).click();
  }
}
