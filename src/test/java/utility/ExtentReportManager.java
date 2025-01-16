package utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseTest;

public class ExtentReportManager implements ITestListener {
	ExtentSparkReporter sparkReporter; //UI Defining..
	ExtentReports extent; //Providing information
	ExtentTest test; //Test reports
	
	public void onStart(ITestContext context) {
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + "report.html");
		sparkReporter.config().setDocumentTitle("Automation");
		sparkReporter.config().setDocumentTitle("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("QA", "Radheshyam");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", "Windows 11");
		
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test passed is " + result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test failed is " + result.getName());
		test.log(Status.FAIL, "Test failed because of " + result.getThrowable());
		
		try {
			String imgPath = new BaseTest().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test skipped is " +result.getName());
	}
	
}