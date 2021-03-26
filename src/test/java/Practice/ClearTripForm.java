package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClearTripForm {

	public static WebDriver driver;

	public static void main(String[] args) {

//		id = OneWay
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://www.cleartrip.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		checkCompulsoryField();
		
	//	oneWayTrip();
	}
	
	public static void checkCompulsoryField()
	{
		driver.findElement(By.id("SearchBtn")).click();
		//Boolean displayStatus = driver.findElement(By.xpath("//*[@id=\"ORtrip\"]/section[1]/div[1][@class='hint ugly errorLabel']")).isDisplayed();
		
	//	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"ORtrip\"]/section[1]/div[1]/dl/dd/span/small[@class='hint ugly errorLabel']")).isDisplayed(), "Error: From field not selected");
		Assert.assertTrue(driver.findElement(By.xpath("//section[1]/div[1]/dl/dd/span/small[@class='hint ugly errorLabel']")).isDisplayed(), "Error: From field not selected");
		Assert.assertTrue(driver.findElement(By.xpath("//section[1]/div[3]/dl/dd/span/small[@class='hint ugly errorLabel']")).isDisplayed(), "Error: To field not selected");
		Assert.assertTrue(driver.findElement(By.xpath("//section[2]/div[1]/dl/dd/div/small[@class='hint ugly errorLabel']")).isDisplayed(), "Error: Journey date not selected");
		
		System.out.println("error status for all mandatory fields is getting displayed");
		
	}

	public static void oneWayTrip() {
		// book one way trip
		if (!driver.findElement(By.id("OneWay")).isSelected()) {
			driver.findElement(By.id("OneWay")).isSelected();
		}

		driver.findElement(By.id("DepartDate")).click();

		driver.findElement(By.id("FromTag")).sendKeys("New York, US - All airports (NYC)");
		driver.findElement(By.id("ToTag")).sendKeys("Frankfurt, DE - Frankfurt (FRA)");

		driver.findElement(By.id("DepartDate")).click();
		// driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[3]/td[2]/a")).click();
		System.out.println(
				driver.findElement(By.xpath("//*[@class = 'ui-state-default ui-state-highlight ui-state-active ']"))
						.getText());
		driver.findElement(By.xpath("//*[@class = 'ui-state-default ui-state-highlight ui-state-active ']")).click();

		WebElement AdultElement = driver.findElement(By.id("Adults"));
		Select selectAdult = new Select(AdultElement);
		selectAdult.deselectByValue("2");

		driver.findElement(By.xpath("//a[@id='MoreOptionsLink'])")).click();

		Assert.assertTrue(driver.findElement(By.id("Class")).isDisplayed(), "More-Options, select class is visible");
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='AirlineAutocomplete']")).isDisplayed(),
				"More Options - Airline selection option is visible");
		System.out.println("More selection options are available");

	}

}
