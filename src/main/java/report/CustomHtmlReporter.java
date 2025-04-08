package report;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.util.*;

public class CustomHtmlReporter implements IReporter {

  @Override
  public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
    try {
      String fileName = outputDirectory + File.separator + "custom-report.html";
      FileWriter writer = new FileWriter(fileName);

      writer.write("<html><head><title>Automation Report</title>");
      writer.write("<style>");
      writer.write("table {width: 100%; border-collapse: collapse;}");
      writer.write("th, td {border: 1px solid #ccc; padding: 8px; text-align: left;}");
      writer.write("th {background-color: #f2f2f2;}");
      writer.write(".pass {color: green;} .fail {color: red;} .skip {color: orange;}");
      writer.write("</style></head><body>");

      writer.write("<h1>Automation Test Report</h1>");
      writer.write("<table><tr><th>Test Name</th><th>Status</th><th>Duration</th><th>Error</th></tr>");

      for (ISuite suite : suites) {
        Map<String, ISuiteResult> results = suite.getResults();
        for (ISuiteResult result : results.values()) {
          ITestContext context = result.getTestContext();

          writeTestResults(writer, context.getPassedTests(), "pass");
          writeTestResults(writer, context.getFailedTests(), "fail");
          writeTestResults(writer, context.getSkippedTests(), "skip");
        }
      }

      writer.write("</table></body></html>");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeTestResults(FileWriter writer, IResultMap tests, String status) throws IOException {
    for (ITestResult result : tests.getAllResults()) {
      String testName = result.getMethod().getMethodName();
      long duration = result.getEndMillis() - result.getStartMillis();
      String error = (result.getThrowable() != null) ? result.getThrowable().getLocalizedMessage() : "-";

      writer.write("<tr>");
      writer.write("<td>" + testName + "</td>");
      if(status.toLowerCase().equalsIgnoreCase("fail")) {
        String line = "<a href='..\\test-output\\Screenshots\\" +
            result.getName() + "_test.png'> " + status.toUpperCase() +"<br/><img src='..\\test-output\\Screenshots\\" +
            result.getName() +"_test.png' height='100' width='100'/> </a>";
        writer.write("<td class='" + status + "'>" + line +"</td>");
      }
      else {
        writer.write("<td class='" + status + "'>" + status.toUpperCase() + "</td>");
      }
      writer.write("<td>" + duration + " ms</td>");
      writer.write("<td>" + error + "</td>");
      writer.write("</tr>");
    }
  }
}

