package api;

import base.APIBaseTest;
import static io.restassured.RestAssured.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/*@Epic("API Feature")
@Feature("ReqRes API Test")*/
public class ReqResAPITest extends APIBaseTest {

  @Test(description = "This method test GET operation API")
/*  @Description("This method test GET operation API")*/
  public void getMethodTest() {
    Response response = given()
        .spec(reqSpec)
        .when()
        .get("/users?page=2")
        .then()
        .spec(okResSpec)
        .extract().response();
    Assert.assertEquals(response.getStatusCode(), 200);
  }


  @Test
  public void delayedGetMethod() {
    given()
        .spec(reqSpec)
        .when()
        .get("/users?delay=3")
        .then()
        .spec(okResSpec)
        .time(lessThan(1000L), TimeUnit.MILLISECONDS);
  }

  @Test(dependsOnMethods = {"delayedGetMethod"})
  public void skipTest() {
    given()
        .spec(reqSpec)
        .when()
        .get("/users?delay=3")
        .then()
        .spec(okResSpec)
        .time(lessThan(1000L), TimeUnit.MILLISECONDS);
  }

}
