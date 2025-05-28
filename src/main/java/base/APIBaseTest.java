package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import utilites.PropertyReader;

public class APIBaseTest {
  protected RequestSpecification reqSpec;
  protected ResponseSpecification okResSpec;
  protected static final String BASE_URL = new PropertyReader().getProperty("base_url");


  @BeforeMethod
  public void setup() {
    reqSpec = new RequestSpecBuilder()
        .setBaseUri(BASE_URL)
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
