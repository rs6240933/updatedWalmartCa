package WalmartCa.webutility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import WalmartCa.webutility.Baseclass;

public class Listeners extends Baseclass implements ITestListener {
	ExtentReports extent = Baseclass.configReport();
	public static ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
	}
}
