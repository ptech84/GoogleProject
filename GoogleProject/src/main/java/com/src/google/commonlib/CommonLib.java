package com.src.google.commonlib;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.src.google.base.TestBase;

public class CommonLib extends TestBase{
	
	static ExtentReports extentReport;
	static ExtentTest extentTest;
	Logger logger ;
	
	public void reporting() {
		logger = Logger.getLogger(CommonLib.class);
		extentReport = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		extentReport.addSystemInfo("Username", "Praveen");
		extentReport.addSystemInfo("Environment", "QA");
		
	}

	public void fnReport(String result) {
		
		
		switch (result) {
		case "Pass":
			extentTest.log(LogStatus.PASS, "test case is passed");
		    break;
			
		case "Fail":
			extentTest.log(LogStatus.FAIL, "test case is failed");
			break;
			
		case "Skip":
			extentTest.log(LogStatus.SKIP, "test case is skipped");
		}
	
		
	}

	public void SendKeys(WebElement xpath,String value) {
		WebElement element = xpath;
		if(element.isDisplayed()) {
			element.sendKeys(value);
			//fnReport("Pass");
		}else {
			//fnReport("Fail");
		}
	}
	
	public void Wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ClickButton(WebElement xpath) {
		WebElement element = xpath;
		if(element.isDisplayed()) {
			element.click();
			//fnReport("Pass");
		}else {
			//fnReport("Fail");
		}
	
		
	}
	
	
	public void Report(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test case is failed" + result.getName());//add name to extentreport
			extentTest.log(LogStatus.FAIL, "test case is failed"+result.getThrowable());//add exception to extentreport
			
			String SCREENSHOTPATH = GetScreenShot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(SCREENSHOTPATH));
			
		} else if(result.getStatus()==ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "TestCase is skipped" + result.getName());
			
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test case is passed" + result.getName());
		}
		
	}
	
	public String GetScreenShot(WebDriver driver, String screenshotname) throws IOException{
		
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		String Destination = System.getProperty("user.dir")+"/FailedTestCases" + screenshotname + date + ".png";
		File fi = new File(Destination);
		FileUtils.copyFile(Source,fi);
		return Destination;
			
	}
	

}
