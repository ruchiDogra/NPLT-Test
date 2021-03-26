package Practice;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinkList {

	public static WebDriver driver1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		setBrowser();
//		linkCount();
		linkInNewTab();
	}

	public static void setBrowser() {
		WebDriverManager.chromedriver().setup();
		driver1 = new ChromeDriver();
	}

	public static void linkCount() {
		driver1.navigate().to("https://rahulshettyacademy.com/AutomationPractice/");
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement footerElement = driver1.findElement(By.xpath("//*[@id='gf-BIG']"));
		List<WebElement> footerLinks = footerElement.findElements(By.tagName("a"));
		int linkCnt = footerLinks.size();
		System.out.println("total link count in footer is : " + linkCnt);
		for (WebElement footerLink : footerLinks) {
			System.out.println(footerLink.getText());
		}
	}

	// function to calculate broken links
	public static void brokenList() {

	}

	// open all links in new tab and grab
	public static void linkInNewTab() {
		 
		driver1.navigate().to("https://rahulshettyacademy.com/AutomationPractice/");
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//code to get window title of all footer links
		WebElement footerElement = driver1.findElement(By.xpath("//*[@id='gf-BIG']"));
		List<WebElement> footerLinks = footerElement.findElements(By.tagName("a"));
		int linkCnt = footerLinks.size();
		System.out.println("total link count in footer is : " + linkCnt);
		for (WebElement footerLink : footerLinks) 
		{
			//code to move focus to tab window and get window title
			//code to open the link in the opened browser in the new tab
			String openLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
			footerLink.sendKeys(openLink);
		}
		//now change the control to child window get its title
		Set <String> allWindows =  driver1.getWindowHandles();
		Iterator<String> it = allWindows.iterator();
		String parentHandle = it.next();
	//	driver1.switchTo().window(parentHandle);
		while(it.hasNext())
		{
			String childHandle= it.next();
			driver1.switchTo().window(childHandle);
			System.out.println( "New tab title is " +driver1.getTitle());
		}
	}

}
