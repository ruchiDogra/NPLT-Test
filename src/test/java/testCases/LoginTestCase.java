package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import base.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.TestDataProvider;

public class LoginTestCase extends BaseClass{

	//open URL in different browser
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dpBrowsername", priority = 1)
	public void BrowserName (String BrowserName)
	{
		try 
		{
			if (BrowserName.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if(BrowserName.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		}catch(Throwable t)
		{
			Reporter.log("Exception is: " + t.getMessage());
		}
		driver.get("http://localhost:8080/nltp/index.php?route=account/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp", priority = 2)
	public void doLogin(String username, String pwd)
	{
		try {
			driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/login");//added this line as we have to navigate to login page
		//@BeforeTest runs only once as this is a single test which runs for
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //after clicking new URL, page should be refreshed
			driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys(username);
			driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys(pwd);
		
			driver.findElement(By.xpath("//input[@type = 'submit' and @value = 'Login']")).click(); //relative xpath
		}catch(Throwable t)
		{
			System.out.println("Error is:" + t.getMessage());
			Reporter.log("Error is:" + t.getMessage());
			Assert.fail();
		}
	//	System.out.println("username is "+ username + " password is " + pwd);
	}
	
	@AfterSuite
	public void closeBrowser()
	{
		driver.quit();
	}
}
