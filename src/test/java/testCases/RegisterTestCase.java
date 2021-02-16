package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.TestDataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import base.BaseClass;

//Class to test registration, login, download for Individual user
public class RegisterTestCase extends BaseClass 
{

	@BeforeTest
	public void OpenWebsite()
	{
		driver.get("http://localhost:8080/nltp/index.php");
		//driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/register");
		Reporter.log("Website opened");
	//	softAssert.assertEquals(driver.findElement(By.xpath("/html/body/header/div/div/div[1]/div/a/img")).getText(), "National Platform for Language Technology");
	//	softAssert.assertAll();
	}
	
/*	@AfterTest
	public void CloseWebsite()
	{
		Reporter.log("browser closed after test case execution");
		driver.close();
	}
*/
	
	//Smoke test : positive test case for successful registration of individual user
	//Register with Properties file and Data Parameterization  --- 1st approach 

	//1st approach - using keyword methods and passing xls columns as method parameters
/*	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp", priority=1)
	public void RegisterAsIndividual(String UserType, String firstname, String lastname, String emailID, String pwd, String confirmPwd )
	{
		driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/register");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS); 	//add wait so that all elements in new screen load properly
		
		//Fill individual user register form
		click("radio_IndividualUser_XPATH");
		sendKey("firstname_XPATH", firstname);
		sendKey("lastname_XPATH", lastname);
		
		Random random = new Random(); 		//generate Random no , for generating new email id everyday
		int randomInt = random.nextInt(999);
		email = "we" + randomInt + "@gmail.com";
		
		WebDriverWait wait = new WebDriverWait(driver, 20); //In such conditions a separate method isElementPresent should be called
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("email_XPATH"))));
		sendKey("email_XPATH", email);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("pwd_XPATH"))));
		sendKey("pwd_XPATH", "pwd");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("confirmPwd_XPATH"))));
		sendKey("confirmPwd_XPATH", confirmPwd);
		click("chkbox_XPATH");
		click("continueBtn_XPATH");
		
		//Now 2nd screen will openup, add wait so that all elements in 2nd screen load properly
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		sendKey("tel_XPATH", "9899108080"); //phone no value also from xls file
		sendKey("OTP_XPATH", "12345"); //I have hardcoded OTP in localhost , as OTP part cannot be handled in Selenium
		click("inputBtn_XPATH");
		
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS); //3rd screen of success or failure msg
		String expectedMsg = driver.findElement(By.xpath(OR.getProperty("successMsg_XPATH"))).getText();
   		softAssert.assertTrue(expectedMsg.contains("Your new account has been successfully created"), "After registration msg donot contain success msg");
		softAssert.assertAll();
	}
*/
 
	//2nd and best approach - using keyword methods and reading xls columns through Hashtable
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp1", priority=1)
	public void RegisterAsIndividual(Hashtable<String, String> data) throws InterruptedException  
	{
		System.out.println("Register As Indivual Test Case commented");
	
	//	Assert.fail();
		
		driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/register");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS); 	//add wait so that all elements in new screen load properly
		
		//Fill individual user register form
		click("radio_IndividualUser_XPATH");
		sendKey("firstname_XPATH", data.get("firstname"));
		sendKey("lastname_XPATH", data.get("lastname"));
		
		Random random = new Random(); 		//generate Random no , for generating new email id everyday
		int randomInt = random.nextInt(999);
		email = "we" + randomInt + "@gmail.com";
		
		WebDriverWait wait = new WebDriverWait(driver, 20); //In such conditions a separate method isElementPresent should be called
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("email_XPATH"))));
		sendKey("email_XPATH", email);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("pwd_XPATH"))));
		sendKey("pwd_XPATH", data.get("pwd"));
		
	//	pwd = this.pwd; //send value of pwd read from xls to static variable pwd
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("confirmPwd_XPATH"))));
		sendKey("confirmPwd_XPATH", data.get("confirmPwd"));
		click("chkbox_XPATH");
		click("continueBtn_XPATH");
		
		//Now 2nd screen will openup, add wait so that all elements in 2nd screen load properly
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
	
		//phone no value also from xls file. But while reading from xls it is converting 9899108080 to 9.899108080
		sendKey("tel_XPATH", data.get("phone")); 
	
		sendKey("OTP_XPATH", data.get("otp")); //I have hardcoded OTP in localhost , as OTP part cannot be handled in Selenium
		click("inputBtn_XPATH");
		
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS); //3rd screen of success or failure msg
		String actualMsg = driver.findElement(By.xpath(OR.getProperty("successMsg_XPATH"))).getText();
   	
		//for spelling and msg text testing soft Assert should be used
		softAssert.assertTrue(actualMsg.contains("Your new account has been successfully created"), "After registration msg donot contain success msg");
		softAssert.assertAll();
		
	// now to register with 2nd dataset from xls, it is important to logout first.
	//After clicking login button, it will redirect to new page
	//now add page load wait here and after complete page load and redirecting to new page, click logout button
				
	//	waitForLoad(driver);  //function with explicit wait written in ActionClass.java
		Thread.sleep(10000);
	//	driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS); //after clicking new URL, page should be refreshed
		try 
		{
			driver.findElement(By.xpath("/html/body/nav[1]/div/div/ul/li[4]/a/span[1]")).click();
				
			//add if element <logout> present here, then logout the page , add try-catch
			WebElement accLogout = driver.findElement(By.xpath(OR.getProperty("logoutMenu_XPATH"))); 
		
			//Assert true if logout element is found and then click on logout button
			Assert.assertTrue(isElementPresent("logoutMenu_XPATH"), "Logout menu found and Individual User registered successfully");
			Reporter.log("Logout menu found");
			
			Actions action = new Actions(driver); 
			action.moveToElement(accLogout).perform();
			driver.findElement(By.xpath("/html/body/nav[1]/div/div/ul/li[4]/ul/li[5]/a")).click();
			}
			catch(Exception e)
			{
				System.out.println("exception is "+ e.getMessage());
				Reporter.log("Exception is " + e.getMessage());
			}
		
		}
 	
 	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp1", priority=2)
	public void RegisterAsIndianAcademic(Hashtable<String, String> data) throws InterruptedException
	{
 		driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/register");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS); 	//add wait so that all elements in new screen load properly
		
		//Fill Indian Academic register form
		click("radio_IndianAcademic_XPATH");
		sendKey("firstname_XPATH", data.get("firstname"));
		sendKey("lastname_XPATH", data.get("lastname"));
		
		Random random = new Random(); 		//generate Random no , for generating new email id everyday
		int randomInt = random.nextInt(999);
		email = "we" + randomInt + "@gmail.com";
		
		WebDriverWait wait = new WebDriverWait(driver, 20); //In such conditions a separate method isElementPresent should be called
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("email_XPATH"))));
		sendKey("email_XPATH", email);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("pwd_XPATH"))));
		sendKey("pwd_XPATH", data.get("pwd"));
		
		//pwd = this.pwd; //send value of pwd read from xls to static variable pwd
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("confirmPwd_XPATH"))));
		sendKey("confirmPwd_XPATH", data.get("confirmPwd"));
		
		// additional fields of Indian Academic User
		if(data.get("ProfEmail") != "")
		{
			sendKey("profEmail_XPATH",data.get("ProfEmail"));
		}
		
		sendKey("OrgName_XPATH", data.get("OrgName"));
			
		if(data.get("webURL") != "")
		{
			sendKey("webURL_XPATH",data.get("webURL"));
		}
		if(data.get("PhotoID") != "")
		{
		// for upload functionality, there is no need to click browse button, directly send path of file.
			sendKey("PhotoID_XPATH", data.get("PhotoID"));
		}
		
		click("chkbox_XPATH");
		click("continueBtn_XPATH");
		
		//Now 2nd screen will openup, add wait so that all elements in 2nd screen load properly
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		//phone no value also from xls file. But while reading from xls it is converting 9899108080 to 9.899108080
	//	sendKey("tel_XPATH", data.get("phone")); 
		sendKey("tel_XPATH","9899108080");
	//	sendKey("OTP_XPATH", data.get("otp")); //I have hardcoded OTP in localhost , as OTP part cannot be handled in Selenium
		sendKey("OTP_XPATH", "12345");
		click("inputBtn_XPATH");
		
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS); //3rd screen of success or failure msg
		String actualMsg = driver.findElement(By.xpath(OR.getProperty("successMsg_XPATH"))).getText();
   	
		//for spelling and msg text testing soft Assert should be used
		softAssert.assertTrue(actualMsg.contains("Your new account has been successfully created"), "After registration msg donot contain success msg");
		softAssert.assertAll();
				
		Thread.sleep(10000);
				
				try {
				driver.findElement(By.xpath("/html/body/nav[1]/div/div/ul/li[4]/a/span[1]")).click();
				
				//add if element <logout> present here, then logout the page , add try-catch
				WebElement accLogout = driver.findElement(By.xpath("/html/body/nav[1]/div/div/ul/li[4]/ul/li[5]/a")); 
				
				Actions action = new Actions(driver); 
				action.moveToElement(accLogout).perform();
				driver.findElement(By.xpath("/html/body/nav[1]/div/div/ul/li[4]/ul/li[5]/a")).click();
				}catch(Exception e)
				{
					System.out.println("exception is "+ e.getMessage());
				}

	}
	
/*	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp", priority=3)
	public void RegisterAsStartUp(Hashtable<String, String> xltabe)
	{
		System.out.println("Indian Academic");
	}
	
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp", priority=4)
	public void RegisterAsMSME(Hashtable<String, String> xltabe)
	{
		System.out.println("MSME");
	}
*/	
	
 	//try to login as all Usertype 
//	@Test(priority=5,dependsOnMethods = {"RegisterAsIndividual"})
	@Test(priority=3,dependsOnMethods = {"RegisterAsIndividual"})
	public void Login()
	{
	
		Assert.fail();
		driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/login");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//fill login form 
		try {
			driver.findElement(By.xpath("//*[@id=\"input-email\"]")).sendKeys(email);
			driver.findElement(By.xpath("//*[@id=\"input-password\"]")).sendKeys(pwd);
			driver.findElement(By.xpath("//input[@type = 'submit' and @value = 'Login']")).click();
			Reporter.log("login details filled properly");
		}
		catch(NoSuchElementException e)
		{
			Reporter.log("username, password, login element not found");
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
		
			driver.findElement(By.xpath("//a[@href='http://localhost:8080/nltp/index.php?route=account/logout']")).click();
			} catch (NoSuchElementException e) {
				
				Reporter.log("Unable to Login");
			}
		
		//add assert here
	}
	
}
 