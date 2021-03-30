package testNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListener implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Làm gì đó trước khi testcase này được chạy");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Làm gì đó sau khi testcase này success");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Làm gì đó sau khi testcase này fail");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Làm gì đó sau khi testcase này skip");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Làm gì đó sau khi testcase này fail with % success");
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Làm gì đó trước khi Class này được chạy");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Làm gì đó sau khi Class này kết thúc");
		
	}

}
