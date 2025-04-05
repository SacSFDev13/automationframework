package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;

public class APIBaseTest {
  protected RequestSpecification reqSpec;
  protected ResponseSpecification okResSpec;

  @BeforeMethod
  public void setup() {
    reqSpec = new RequestSpecBuilder()
        .setBaseUri("https://reqres.in/api")
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();

    okResSpec = new ResponseSpecBuilder()
        .log(LogDetail.ALL)
        .expectContentType(ContentType.JSON)
        .expectStatusCode(200).build();

    RestAssuredConfig.config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true));
  }
}
