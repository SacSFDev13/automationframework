package api;

import base.APIBaseTest;
import static io.restassured.RestAssured.*;

import builders.CreateUserRequestBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import model.api.request.User;
import model.api.response.CreateUserResponse;
import model.api.response.GetUsersResponse;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import utilites.RequestHelper;
import utilites.ResourceHelper;
import utilites.ResponseHelper;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

/*@Epic("API Feature")
@Feature("ReqRes API Test")*/
public class ReqResAPITest extends APIBaseTest {

  @Test(description = "This method test GET operation API")
/*  @Description("This method test GET operation API")*/
  public void getMethodTest() throws IOException {
//    test = test.createNode("Node");
    Response response = ResourceHelper.get("/users?page=2", reqSpec);
    Assert.assertEquals(response.getStatusCode(), 200);
    GetUsersResponse response1 = (GetUsersResponse) ResponseHelper
        .getResponseAsObject(response.asString(), GetUsersResponse.class);
    Assert.assertEquals(response1.getPage(), 2);
    Assert.assertEquals(response1.getData().length, 6);
    Markup markup = MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON);
//    test.fail(markup);
    markup = MarkupHelper.createLabel("Get Test completed", ExtentColor.BLUE);
    test.log(Status.INFO, markup);
    test.info("API logs saved at: <a href='../logs/api.log'>logs/api.log</a>");
  }

  @Test
  public void createUser() throws IOException {
    User user = new CreateUserRequestBuilder()
        .withName("Rahul Dravid")
        .withJob("Coach")
        .build();
    Response response = ResourceHelper.create("/users", reqSpec, RequestHelper.getJsonString(user));
    Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);
    CreateUserResponse jResponse = (CreateUserResponse)ResponseHelper
        .getResponseAsObject(response.asString(), CreateUserResponse.class);
    Assert.assertEquals(response.jsonPath().getString("name"), user.getName());
    Assert.assertEquals(jResponse.getJob(), user.getJob());
    Assert.assertNotNull(jResponse.getId());
    Assert.assertNotNull(jResponse.getCreatedAt());
    test.log(Status.INFO, "Create Test completed");
    test.info("API logs saved at: <a href='../logs/api.log'>logs/api.log</a>");
  }


  @Ignore
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

  @Ignore
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
