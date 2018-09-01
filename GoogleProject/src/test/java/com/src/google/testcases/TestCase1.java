package com.src.google.testcases;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.src.google.base.TestBase;
import com.src.google.commonlib.CommonLib;
import com.src.google.pages.GoogleLandingPage;
import com.src.google.testutil.TestUtil;


@Listeners(com.src.google.commonlib.ListenersDemo.class)

public class TestCase1 extends TestBase{
	
	ExtentReports extent;
	ExtentTest extentTest;
	CommonLib commlib;
	GoogleLandingPage googleLandingPage;
	
	Logger log = Logger.getLogger(TestCase1.class);
	
	public TestCase1() {
		super();
		
	}
	
	@org.testng.annotations.BeforeTest
	public void Setup() {
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReports.html",true);
		extent.addSystemInfo("environment","Praveen MACBOOK");
		extent.addSystemInfo("Role", "QA");	
		
	}
	
	
	@BeforeMethod
	public void BeforeMethod() {
		Initialization();	
		googleLandingPage = new GoogleLandingPage();
		commlib = new CommonLib();
	}
	
	@Test
	public void TestCase1() {
	extentTest = extent.startTest("TestCase1");
	commlib.SendKeys(googleLandingPage.googleSearchText, "SeleniumTest");
	commlib.ClickButton(googleLandingPage.googlesearchBtn);
	commlib.Wait(5000);
	driver.navigate().back();
	commlib.Wait(5000);
	}
	
	@DataProvider
	public Object[][] DataDriven(){
		extentTest = extent.startTest("TestCase2");
		
		Object[][] data = TestUtil.getTestData("contacts");
		return data;
		
	}
	
	@Test(dataProvider="DataDriven")
	public void TestCase2(String title, String firstName, String lastName, String company) {
		System.out.println(title + " " + firstName + " "+ lastName + " "+ company);
		
	}
	
	
	
	
	@AfterMethod
	public void teardown(ITestResult result) throws IOException{
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
		
		extent.endTest(extentTest);
		driver.quit();
	}
	
	@AfterTest
	public void AfterTest() {
		extent.flush();
		extent.close();
		
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
