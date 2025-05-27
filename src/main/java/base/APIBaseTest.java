package base;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class APIBaseTest {
  protected RequestSpecification reqSpec;
  protected ResponseSpecification okResSpec;
  protected Properties properties = new Properties();
  public static final Logger logger = LoggerFactory.getLogger(APIBaseTest.class);

  @BeforeTest
  public void setup() throws IOException {
    properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/url.properties"));
    logger.info(properties.getProperty("baseUrl"));
    reqSpec = new RequestSpecBuilder()
//        .setBaseUri("")
        .setContentType(ContentType.JSON)
        //.addHeader("x-api-key", "reqres-free-v1")
        .addHeader("special-key", "special-key")
        .log(LogDetail.ALL)
        .build();

    okResSpec = new ResponseSpecBuilder()
        .log(LogDetail.ALL)
        .expectContentType(ContentType.JSON).build();
//        .expectStatusCode(200).build();

 //   RestAssuredConfig.config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true));
  }
}
