package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
  private WebDriver driver;
  public By homeMenu = By.xpath("//div[contains(@class, \"shop-menu\")]/ul/li[contains(text(), Home)]/a");
  public By loginMenu = By.xpath("//div[contains(@class, \"shop-menu\")]/ul/li[text() = \" Signup / Login\"]/a");


      public Homepage(WebDriver driver) {
        this.driver = driver;
      }

      public void clickOnLoginMenu() {
        this.toWebElement(loginMenu).click();
      }

      public void enterUserEmail(String email) {

      }

      public boolean isFocusOnHomePage() {
        return this.toWebElement(homeMenu).getAttribute("style").contains("color: orange");
      }

      public WebElement toWebElement(By locator) {
        return this.driver.findElement(locator);
      }
}
