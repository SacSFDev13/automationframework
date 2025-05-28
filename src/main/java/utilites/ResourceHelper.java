package utilites;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ResourceHelper {

  public static Response get(String url, RequestSpecification requestSpecification) {
    return given()
        .header("x-api-key", "reqres-free-v1")
        .spec(requestSpecification)
        .when()
        .get(url);
  }

  public static Response create(String url, RequestSpecification requestSpecification, String json) {
    return given()
        .spec(requestSpecification)
        .header("x-api-key", "reqres-free-v1")
        .when()
        .body(json)
        .post(url);
  }

}