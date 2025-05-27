package api;

import base.APIBaseTest;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PetAPITests extends APIBaseTest {
  private static final String BASE_URL = "https://petstore.swagger.io/v2/pet";

  @Test
  public void findByStatus() {
    Response response = given()
        .queryParam("status", "available")
        .when()
        .get(BASE_URL + "/findByStatus")
        .then()
          .statusCode(200)
          .contentType(ContentType.JSON)
          .body("[0].id", notNullValue() )
        .extract().response();
  }
}
