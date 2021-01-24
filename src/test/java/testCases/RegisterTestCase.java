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

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

//Class to test registration, login, download for Individual user
public class RegisterTestCase {

	WebDriver driver;
	static String email, pwd;
	SoftAssert softAssert = new SoftAssert();
	
	public static Properties OR = new Properties(); //Properties class obj. OR is required to read values from *.properties file
	public static FileInputStream finput;
	
	@BeforeSuite
	public void setBrowser() throws IOException
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		//driver.get("https://www.google.com/");
		Reporter.log("Driver is set");
		
		//load properties file here
		
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

	@BeforeTest
	public void OpenWebsite()
	{
		driver.get("http://localhost:8080/nltp/index.php");
		//driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/register");
		Reporter.log("Website opened");
	//	softAssert.assertEquals(driver.findElement(By.xpath("/html/body/header/div/div/div[1]/div/a/img")).getText(), "National Platform for Language Technology");
	//	softAssert.assertAll();
	}
	
	@AfterTest
	public void CloseWebsite()
	{
		Reporter.log("browser closed after test case execution");
		driver.close();
	}
	 
	
	//positive test case for successful registration of individual user
	//Register with Properties file and Data Parameterization
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp", priority=1)
	public void RegisterAsIndividual(String UserType, String firstname, String lastname, String emailID, String pwd, String confirmPwd )
	{
		driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/register");
		
		//add wait so that all elements in new screen load properly
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		//Fill individual user register form
		driver.findElement(By.xpath(OR.getProperty("radio_IndividualUser_XPATH"))).click();
		
		driver.findElement(By.xpath(OR.getProperty("firstname_XPATH"))).sendKeys(firstname);
		driver.findElement(By.xpath(OR.getProperty("lastname_XPATH"))).sendKeys(lastname);
	
		//generate Random no , for generating new email id everyday
		Random random = new Random();
		int randomInt = random.nextInt(999);
		
		email = "we" + randomInt + "@gmail.com";
		
	//  driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys(email);//	WebDriverWait wait = new WebDriverWait(driver, 20);
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#input-email")));
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("email_XPATH"))));
		driver.findElement(By.xpath(OR.getProperty("email_XPATH"))).sendKeys(email);
	
		//	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#input-email")));
	//	driver.findElement(By.cssSelector("#input-email")).sendKeys(email);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("pwd_XPATH"))));
		
		driver.findElement(By.xpath(OR.getProperty("pwd_XPATH"))).sendKeys(pwd);
	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("confirmPwd_XPATH"))));
		driver.findElement(By.xpath(OR.getProperty("confirmPwd_XPATH"))).sendKeys(confirmPwd);
		
		driver.findElement(By.xpath(OR.getProperty("chkbox_XPATH"))).click(); //click checkbox
		
		driver.findElement(By.xpath(OR.getProperty("continueBtn_XPATH"))).click(); 
		
		//add wait so that all elements in 2nd screen load properly
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
	
		driver.findElement(By.xpath(OR.getProperty("tel_XPATH"))).sendKeys("9899108080");
		
		driver.findElement(By.xpath(OR.getProperty("OTP_XPATH"))).sendKeys("12345");
			
		driver.findElement(By.xpath(OR.getProperty("inputBtn_XPATH"))).click();
		
		//3rd screen of success or failure msg
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		
		String expectedMsg = driver.findElement(By.xpath(OR.getProperty("successMsg_XPATH"))).getText();
         
		softAssert.assertTrue(expectedMsg.contains("Your new account has been successfully created"), "After registration msg donot contain success msg");
		 
		softAssert.assertAll();
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp", priority=2)
	public void RegisterAsIndianAcademic(Hashtable<String, String> xltabe)
	{
		System.out.println("Indian Academic");
	}
	
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp", priority=3)
	public void RegisterAsStartUp(Hashtable<String, String> xltabe)
	{
		System.out.println("Indian Academic");
	}
	
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp", priority=4)
	public void RegisterAsMSME(Hashtable<String, String> xltabe)
	{
		System.out.println("MSME");
	}
	
	
	//try to login as all Usertype 
	@Test(priority=5,dependsOnMethods = {"RegisterAsIndividual"})
	public void Login()
	{
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
 