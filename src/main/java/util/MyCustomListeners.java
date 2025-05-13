package util;

import org.testng.*;
import org.testng.log4testng.Logger;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.logging.LogManager;

public class MyCustomListeners implements IExecutionListener, IInvokedMethodListener, IAlterSuiteListener {

  @Override
  public void onExecutionStart() {
    System.out.println("Execution Started!");
  }

  @Override
  public void onExecutionFinish() {
    System.out.println("Execution Completed!");
  }

  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    log("Starting", method, testResult);
  }

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    log("Completed", method, testResult);
  }

  @Override
  public void alter(List<XmlSuite> suites) {
    XmlSuite suite = suites.get(0);
    suite.getTests().get(0).setName("Listener Test");
    suite.setName(getClass().getSimpleName());
  }

  private static void log(String prefix, IInvokedMethod method, ITestResult result) {
    String type = "Configuration";
    if (method.isTestMethod()) {
      type = "Test";
    }
    String msg = prefix + " executing [" + type + "] method "
        + method.getTestMethod().getQualifiedName() + "()";
    System.out.println(msg);
  }
}
