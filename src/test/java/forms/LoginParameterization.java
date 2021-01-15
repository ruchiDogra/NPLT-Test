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
	
	@Test(dataProvider = "dp")
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
	

/*	@DataProvider(name = "dp")
	public Object[][] getData()
	{
		Object[][] data = new Object[3][2];
		
		data[0][0] = "ruchi.dogra.cdac@gmail.com";
		data[0][1] = "Lotus@123";

		data[1][0] = "ruchid@cdac.in";
		data[1][1] = "Lotus@123";
		
		data[2][0] = "sanjayt@cdac.in";
		data[2][1] = "Lotus@123";

		return data;
	}
*/	

	//read data from xls 
	@DataProvider(name = "dp")
	public Object[][] getData()
	{
		ExcelReader excel = new ExcelReader("/home/ruchi/eclipse-workspace/NPLT-Test/src/test/resources/excel/testdata.xls"); // ExcelReader class constructor needs string excel path as parameter which you wants to read
		String sheetName = "LoginTest";
		
		int rowCnt = excel.getRowCount(sheetName);
		int colCnt = excel.getColumnCount(sheetName);
			
		Object [][] data = new Object[rowCnt-1][colCnt];
		
		for(int i=0; i < rowCnt-1; i++ )
		{
			for(int j=0; j< colCnt; j++)
			{
				data[i][j] = excel.getCellData(sheetName, j, i+2);
				
				System.out.println("row number is " + i + "col number is" + j + data[i][j]);
			}
		}
		
	
		return data;
	}
	
}
