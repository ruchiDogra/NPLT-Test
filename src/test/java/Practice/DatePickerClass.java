package Practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePickerClass {

	private static WebDriver driver1;
	public static void main(String[] args) throws InterruptedException {
		
		setBrowser();
		calender();
	}
	
	public static void setBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver1 = new ChromeDriver();
	}
	
	public static void calender() throws InterruptedException
	{
		driver1.get("https://www.path2usa.com/travel-companions");
		
		//To select date : 23 September 2021
		driver1.findElement(By.id("travel_date")).click();
		System.out.println(driver1.findElement(By.xpath("//*[@class='datepicker-days']//*[@class = 'datepicker-switch']")).getText());
	//	System.out.println(driver1.findElement(By.cssSelector("[class='datepicker-days'] [class='datepicker-switch']")).getText());
		if(!driver1.findElement(By.xpath("//*[@class='datepicker-days']//*[@class = 'datepicker-switch']")).getText().contains("May"))
		{
			driver1.findElement(By.xpath("//div[1]/table/thead/tr[1]/th[3]")).click();
		}
		System.out.println(driver1.findElement(By.xpath("//*[@class='datepicker-days']//*[@class = 'datepicker-switch']")).getText());
		
		List <WebElement> days = driver1.findElements(By.xpath("//*[@class='day']"));
		int dayCnt = driver1.findElements(By.xpath("//*[@class='day']")).size();
		
		for(WebElement day: days)
		{
			System.out.println(day.getText());
			Thread.sleep(10);
			if(day.getText().equalsIgnoreCase("26"))
			{
				day.click();
				break;
			}
		}
}

}
