package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

//Class to test registration, login, download for Individual user
public class RegisterTestCase {

	WebDriver driver;
	static String emailID, pwd;
	SoftAssert softAssert = new SoftAssert();
	@BeforeSuite
	public void setBrowser()
	{
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		//driver.get("https://www.google.com/");
		Reporter.log("Driver is set");
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
	//	driver.close();
	}
	 
	
	//positive test case for successful registration of individual user
	@Test(priority=1)
	public void Register()
	{
		driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/register");
		
		//add wait so that all elements in new screen load properly
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//Fill individual user register form
		driver.findElement(By.xpath("//input[@type = 'radio'and @value='10']")).click();
		
	
		//System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div/div/form/fieldset[1]/div[1]/div/div[1]/label")).getText());
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("abc");
		driver.findElement(By.xpath("//*[@id= 'input-lastname']")).sendKeys("hello");
	
		//generate Random no , for generating new email id everyday
		Random random = new Random();
		int randomInt = random.nextInt(999);
		
		emailID = "we" + randomInt + "@gmail.com";
		pwd = "Lotus@123";
		driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys(emailID);
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys("Lotus@123");
		driver.findElement(By.xpath("//*[@id='input-confirm']")).sendKeys("Lotus@123");
		driver.findElement(By.xpath("//form/div[2]/div/input[1]")).click(); //click checkbox
		
		driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click(); 
		
		
		//add wait so that all elements in 2nd screen load properly
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
	
		driver.findElement(By.xpath("//*[@id=\"input-telephone\"]")).sendKeys("9899108080");
		
		driver.findElement(By.xpath("//*[@id=\"input-otp\"]")).sendKeys("12345");
			
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/span/input")).click();
		
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		
		String expectedMsg = driver.findElement(By.xpath("/html/body/div/div/div/h4[1]")).getText();
         
		softAssert.assertTrue(expectedMsg.contains("Your new account has been successfully created"), "After registration msg donot contain success msg");
		 
		softAssert.assertAll();
        
	}
	@Test(priority=2,dependsOnMethods = {"Register"})
	public void Login()
	{
		driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/login");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//fill login form 
		try {
			driver.findElement(By.xpath("//*[@id=\"input-email\"]")).sendKeys(emailID);
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
 