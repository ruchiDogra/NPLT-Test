package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import testCases.RegisterTestCase;

public class BaseClass {

	
	public static WebDriver driver;
	public static String email, pwd;
	public static SoftAssert softAssert = new SoftAssert();
	
	public static Properties OR = new Properties(); //Properties class obj. OR is required to read values from *.properties file
	public static FileInputStream finput;
	public static Logger log = Logger.getLogger(RegisterTestCase.class.getName()); // import Apache API
	
	
	@BeforeSuite
	public static void setBrowser()
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		
		Reporter.log("Driver is set"); // testNG report
		log.info("Browser is set"); //this is log4j api log 
		
		//code to load property file
		try 
		{
			finput = new FileInputStream(".//src//test//resources//properties//OR.properties"); 
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try 
		{
			OR.load(finput);
			Reporter.log("Properties file is loaded");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}
	
	@AfterSuite
	public static void CloseBrowser()
	{
		Reporter.log("browser closed after test case execution");
		driver.close();
	
	}
	
	//method to check whether Element is present or not
	public static boolean isElementPresent(String ElementLocator)
	{
		try 
		{
			driver.findElement(By.xpath(OR.getProperty(ElementLocator)));
			log.info("Element is present : " + ElementLocator ); //this is log4j api log
			Reporter.log("Element is present : " + ElementLocator);
			return true;
		}catch(Throwable e)
		{
			Reporter.log("Element not found " + e.getMessage());
			return false;
		}
		
	}
	
	
	public static void click(String ElementLocator)
	{
		driver.findElement(By.xpath(OR.getProperty(ElementLocator))).click();
		log.info("Element clicked is: " + ElementLocator);//this is log4j api log
		Reporter.log("Element clicked is: "+ ElementLocator);
	}
	
	public static void sendKey(String ElementLocator, String value)
	{
		driver.findElement(By.xpath(OR.getProperty(ElementLocator))).sendKeys(value);
		log.info("Element clicked is: " + ElementLocator);//this is log4j api log
		Reporter.log("Element clicked is: "+ ElementLocator);
	}
	
	public static void select(String ElementLocator)
	{
		
	}
}
