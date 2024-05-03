package com.qa.reports;

import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.net.PortProber;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.qa.testComponents.BaseClass;


public class ExtentListener extends BaseClass implements ITestListener, ISuiteListener {


	
	public void saveScreenShot(String msg)
	{
		String byteImage=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);
		ExtentManager.getExtentTest().pass(msg,MediaEntityBuilder.createScreenCaptureFromBase64String(byteImage).build());
	}
	public void saveFailureScreenShot(String msg)
	{
		String byteImage=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);
		ExtentManager.getExtentTest().fail(msg,MediaEntityBuilder.createScreenCaptureFromBase64String(byteImage).build());
	}
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	//	ExtentReport.createTest(result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		ExtentManager.getExtentTest().log(Status.PASS,MarkupHelper.createLabel(result.getMethod().getMethodName()+" :Testcase Passed",ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
		saveScreenShot("Passed test Screenshot captured");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		ExtentManager.getExtentTest().log(Status.FAIL,MarkupHelper.createLabel(result.getMethod().getMethodName()+" :Testcase Failed",ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
		saveFailureScreenShot("Failure Screenshot captured");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentManager.getExtentTest().log(Status.SKIP,MarkupHelper.createLabel(result.getMethod().getMethodName()+" :Testcase Skipped",ExtentColor.ORANGE));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub		

	}
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		ExtentReport.initReports();
	 
        try {
            // Start Selenium Grid Hub programmatically
			startSeleniumGridHub();
			 // Start Selenium Grid Node programmatically
			 startSeleniumGridNode();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
       

	}
	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		try {
			ExtentReport.flushReports();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    public static void startSeleniumGridHub() throws IOException, InterruptedException {
        String[] command = {"cmd", "/c", "start/min", "cmd", "/k", "java -jar selenium-server-4.20.0.jar hub"};
        Runtime.getRuntime().exec(command);
        Thread.sleep(5000); // Wait for the hub to start
    }

    public static void startSeleniumGridNode() throws IOException, InterruptedException {
        String[] command = {"cmd", "/c", "start/min", "cmd", "/k", "java -jar selenium-server-4.20.0.jar node --port " + PortProber.findFreePort()};
        Runtime.getRuntime().exec(command);
        Thread.sleep(5000); // Wait for the node to start
    }

}
