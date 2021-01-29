package Practice;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CaptureScreenShotWithDate {

	public static String fileName;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		
	//	driver.getTitle().compareToIgnoreCase(str)
		
		String title = driver.getTitle();
	
		System.out.println("Website title is " + title);
		Date d = new Date();
		System.out.println("current date is " + d);
		fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File(".//reports//" + fileName));

	}

}
