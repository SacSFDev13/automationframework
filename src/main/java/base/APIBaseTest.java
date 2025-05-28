package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import report.extent.ExtentReportManager;
import utilites.PropertyReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

public class APIBaseTest {
  protected RequestSpecification reqSpec;
  protected ResponseSpecification okResSpec;
  protected static final String BASE_URL = new PropertyReader().getProperty("base_url");
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  protected static ExtentReports reports;
  protected static ExtentTest test;
  PrintStream logStream;


  @BeforeTest
  public void setup() throws FileNotFoundException {
    logger.info("Before Test setup");
    reqSpec = new RequestSpecBuilder()
        .setBaseUri(BASE_URL)
        .setContentType(ContentType.JSON)
        .build();

    okResSpec = new ResponseSpecBuilder()
        .log(LogDetail.ALL)
        .expectContentType(ContentType.JSON)
        .expectStatusCode(200).build();

    RestAssuredConfig.config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true));
    logStream = new PrintStream(new FileOutputStream("logs/api.log"));
    RestAssured.filters(new RequestLoggingFilter(logStream), new ResponseLoggingFilter(logStream));

  }

  @BeforeSuite
  public void beforeSuiteSetup() {
    logger.info("Before Suite setup");
    reports = ExtentReportManager.getInstance();
  }

  @BeforeClass
  public void beforeClassSetup() {
    logger.info("Before Class setup");
  }

  @BeforeMethod
  public void beforeMethodSetup(Method method) throws FileNotFoundException {
    logger.info("Before Method setup");

    test = reports.createTest(String.format("Test Method - %s", method.getName()));
  }

  @AfterSuite
  public void afterSuite() {
    logger.info("After suite");
    reports.flush();
  }

  @AfterMethod
  public void afterMethod(ITestResult result) {
    if(result.getStatus()==ITestResult.FAILURE) {
      test.fail(result.getThrowable());
    }
    else if(result.getStatus() == ITestResult.SUCCESS) {
      test.pass("Test Passed");
    }
    else {
      test.skip("Test skipped");
    }
    logStream.flush();
  }
}
