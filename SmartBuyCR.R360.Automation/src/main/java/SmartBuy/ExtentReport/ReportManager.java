package SmartBuy.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Get instance of ExtentReports
    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = getTimestamp();
            String reportFileName = "test-output/ExtentReports/SmartBuyCR.Automation.ExtentReport_" + timestamp + ".html";
            extent = createInstance(reportFileName); // Initialize the ExtentReports instance
        }
        return extent;
    }

    // Create an instance of ExtentReports
    private static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("R360 Automation Test Report");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName("Test Automation Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        return extent;
    }

    // Start a new test in the report
    public static void startTest(String testName) {
        ExtentTest extentTest = getInstance().createTest(testName); // Ensure you create a test after initializing
        test.set(extentTest);
    }

    // Get the current test instance
    public static ExtentTest getTest() {
        return test.get();
    }

    // Log fail message
    public static void logFail(String message) {
        getTest().fail(message);
    }

    // Log pass message
    public static void logPass(String message) {
        getTest().pass(message);
    }

    // Log info message
    public static void logInfo(String message) {
        getTest().info(message);
    }

    // Get current timestamp
    public static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        return sdf.format(new Date());
    }

    // Flush the report to the file
    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
