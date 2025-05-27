package api;

import base.APIBaseTest;
import static io.restassured.RestAssured.*;

/*import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;*/
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.json.JSONString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
       // .header("x-api-key", "reqres-free-v1")
        .queryParam("page", 2)
        .queryParam("per_page", 6)
        .when()
        .get("/users")
        .then()
        .spec(okResSpec)
        .body("data.findAll { it.first_name == 'Rachel' && it.last_name == 'Howell' }.size()", greaterThan(0))
        .extract().response();
    try {
      JSONParser parser = new JSONParser();
      JSONObject root = (JSONObject) parser.parse(response.as(JSONObject.class).toJSONString());

      // Read simple fields
      System.out.println("Page: " + root.get("page"));
      System.out.println("Total Pages: " + root.get("total_pages"));

      // Read array of users
      JSONArray users = (JSONArray) root.get("data");
      for (Object obj : users) {
        JSONObject user = (JSONObject) obj;
        System.out.println("ID: " + user.get("id"));
        System.out.println("Name: " + user.get("first_name") + " " + user.get("last_name"));
        System.out.println("Email: " + user.get("email"));
        System.out.println("Avatar: " + user.get("avatar"));
        System.out.println("-----------");
      }

      // Support object
      JSONObject support = (JSONObject) root.get("support");
      System.out.println("Support Text: " + support.get("text"));

    } catch (Exception e) {
      e.printStackTrace();
    }
    // System.out.println("JSON Object email - " + jsonObject.toJSONString());
    Assert.assertEquals(response.getStatusCode(), 200);
    System.out.println(response.jsonPath().getList("data.email"));
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
