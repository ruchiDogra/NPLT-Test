package testCases;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import base.BaseClass;
import utilities.MonitoringMail;
import utilities.TestConfig;

public class CustomListener extends BaseClass implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("Test case execution started of test case: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("Name of the passed test case " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		Reporter.log("Name of the failed test case"  + result.getName());
		
		//code to take screenshot of error page  
	//	ITestContext context = result.getTestContext(); // 
	//    WebDriver driver = (WebDriver)context.getAttribute("WebDriver"); // get "WebDriver" attribute which is set in TestClass @BeforeSuite method 
	//	driver = (WebDriver)context.getAttribute("WebDriver");
	    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
	    Date d = new Date();
	    String	strDate = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
	    
	    try 
        {
        	FileUtils.copyFile(screenshotFile, new File(".//reports//" + result.getName()+ strDate + ".png"));
			String sImgPath = System.getProperty("user.dir")+"//reports//" + result.getName()+ strDate + ".png";
		
		 // to show direct error screenshot in  the report
			Reporter.log("<a href=\"file:///"+ sImgPath + "\"> Error Screenshot </a>"); //to show screenshot link, click to see the screenshot
		//	Reporter.log("<a href = \"file:///home/ruchi/eclipse-workspace/NPLT-Test/reports/ \"> Click here to see screenshot </a>");
		} 
	    catch (IOException e) {
	    	e.printStackTrace();
		}
	                
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
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
		
	//	On finish, send java mail of report and screenshots via gmail
		//monitoringMail.java class is in utilities package
		MonitoringMail mail = new MonitoringMail();
		try {
			
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
			System.out.println("Mail send");
			
		} catch (AddressException e) {
			System.out.println("Unable to mail" + e.getMessage());
			e.printStackTrace();
		
		} catch (MessagingException e) 
		{
			System.out.println("Unable to mail" + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
