package forms;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;
import utilities.TestDataProvider;

public class LoginParameterization {

	WebDriver driver;
	
	@BeforeTest
	public void openURL()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/nltp/index.php?route=account/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dp")
	public void doLogin(String username, String pwd)
	{
		driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/login");//added this line as we have to navigate to login page
		//@BeforeTest runs only once as this is a single test which runs for
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //after clicking new URL, page should be refreshed
		driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys(pwd);
		
		driver.findElement(By.xpath("//input[@type = 'submit' and @value = 'Login']")).click(); //relative xpath

	//	System.out.println("username is "+ username + " password is " + pwd);
	}
	
	
}
