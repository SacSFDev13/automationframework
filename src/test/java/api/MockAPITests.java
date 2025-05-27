package api;

import base.APIBaseTest;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.*;

public class MockAPITests extends APIBaseTest {
  WireMockServer wireMockServer;
  @BeforeSuite
  public void setUp() {
    System.out.println(System.getenv("usr.dir"));
    wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080)
        .usingFilesUnderClasspath(""));
    wireMockServer.start();
    /*stubFor(get(urlEqualTo("/getUser"))
        .willReturn(aResponse()
            .withHeader("Content-Type", "application/xml")
            .withStatus(200)
            .withBody("<user><id>123</id><name>Sachin Fattepur</name></user>")));*/
  }

  @Test
  public void testA() {
    Response response = given()
        .baseUri("http://localhost:8080")
        .get("/getUser")
        .then()
        .statusCode(200)
        .contentType("application/xml")
        .extract().response();
    Assert.assertEquals(response.xmlPath().get("user.id"), "123");
    System.out.println(response.xmlPath().get("user.name").toString());
  }

  @Test
  public void testPost() {
    String requestXml = "<user><id>123</id><name>Sachin</name></user>";

    Response response =
        given()
        .baseUri("http://localhost:8080")
        .header("Content-Type", "application/xml")
        .body(requestXml)
        .when()
        .post("/api/createUser")
        .then()
        .statusCode(201)
      .extract().response();
    System.out.println(response.xmlPath().get("response.userId").toString());
  }

  @AfterSuite
  public void stopServer() {
    if (wireMockServer != null) {
      wireMockServer.stop();
    }
  }
}
