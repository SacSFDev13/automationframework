package report.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
  private static ExtentReports extent;

  public static ExtentReports getInstance() {
    if (extent == null) {
      ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
      reporter.config().setReportName("Automation Report");
      reporter.config().setDocumentTitle("Automation Results");

      extent = new ExtentReports();
      extent.attachReporter(reporter);
      extent.setSystemInfo("Tester", "Sachin Fattepur");
    }
    return extent;
  }
}

