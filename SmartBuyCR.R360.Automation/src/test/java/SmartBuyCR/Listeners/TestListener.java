package SmartBuyCR.Listeners;

import SmartBuy.ExtentReport.ReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
	    String testName = result.getName();
	    ReportManager.logFail("Test " + testName + " failed with exception: " + result.getThrowable().getMessage());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	    String testName = result.getName();
	    ReportManager.logPass("Test " + testName + " passed successfully.");
	}

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        ReportManager.logInfo("Starting test: " + testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ReportManager.logInfo("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.flush();
    }
}
