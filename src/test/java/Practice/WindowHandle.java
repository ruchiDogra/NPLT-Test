package Practice;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandle {

	public static WebDriver driver1;

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		setBrowser();
		//windowHandleMethod1();
	//	windowHandleMethod2();
//		frameHandleMethod();
		nestedFrameHandle();
	}

	

	public static void setBrowser() {
		WebDriverManager.chromedriver().setup();
		driver1 = new ChromeDriver();
	}

	public static void windowHandleMethod1()
	{
		driver1.get("https://rahulshettyacademy.com/loginpagePractise/#");
		driver1.findElement(By.cssSelector(".blinkingText")).click();
		// on the click of blinking text a new tab gets open, so now we have to shift
		// window focus on new tab to work on it.

		Set<String> allWindow = driver1.getWindowHandles(); // it will return[parentid, childId, subchildId]

		Iterator<String> it = allWindow.iterator();
		String parentId = it.next();
		String childId = null;
		// childId = it.next();
		while (it.hasNext()) {
			childId = it.next();
		}
		if (childId != null) {
			driver1.switchTo().window(childId);
			driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println(driver1.findElement(By.cssSelector("p.im-para.red")).getText());
			String emailId = driver1.findElement(By.cssSelector(".im-para.red")).getText().split("at")[1].trim()
					.split(" ")[0];
			System.out.println(emailId); // mentor@rahulshettyacademy.com

			String[] username = emailId.split("@", 2);
			System.out.println(username[0] + "---" + username[1]);
		/*	String uname = username[1];
			String[] userId = uname.split(".");
			System.out.println("finally user  id is " + userId[0] );
		 */ //not working			
			
			driver1.switchTo().window(parentId); // switch back to parent window
			driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver1.findElement(By.id("username")).sendKeys("rahulshettyacademy");
			driver1.findElement(By.id("password")).sendKeys("learning");
		}
	}

	public static void windowHandleMethod2() {

		driver1.get("http://the-internet.herokuapp.com/");
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver1.findElement(By.linkText("Multiple Windows")).click();
		driver1.findElement(By.cssSelector("a[href*='windows']")).click();

		Set<String> allWindows	=	driver1.getWindowHandles();
		Iterator<String> iterate = allWindows.iterator();
		String parentWindowID = iterate.next();
		String childWindowID = iterate.next();
		driver1.switchTo().window(childWindowID);
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String childURL = driver1.getCurrentUrl(); System.out.println("child window url is " + childURL);
		
		String childWindowTitle = driver1.getTitle(); System.out.println("child window title is " + childWindowTitle);
		
		//switch back to parent Window
		driver1.switchTo().window(parentWindowID);
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String parentURL = driver1.getCurrentUrl(); System.out.println("child window url is " + parentURL);
		String parentWindowTitle = driver1.getTitle(); System.out.println("child window title is " + parentWindowTitle);
		System.out.println("text on parent window is : " + driver1.findElement(By.cssSelector("div.example > h3")).getText());
			
	}

	public static void frameHandleMethod()
	{
		driver1.navigate().to("https://jqueryui.com/droppable/");
		//after this step control comes in frame
		driver1.switchTo().frame(driver1.findElement(By.cssSelector("iframe.demo-frame")));
		
		//find draggable element
		WebElement sourceElement = driver1.findElement(By.id("draggable"));
		WebElement targetElement = driver1.findElement(By.id("droppable"));
		
		Actions act = new Actions(driver1);
		act.dragAndDrop(sourceElement, targetElement).build().perform();
	}
	
	//code to read text from nested frame
	public static void nestedFrameHandle()
	{
		driver1.get("http://the-internet.herokuapp.com/");
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver1.findElement(By.linkText("Nested Frames")).click();
		
		driver1.switchTo().frame(driver1.findElement(By.name("frame-top"))).switchTo().frame(driver1.findElement(By.name("frame-middle")));
		
		System.out.println(driver1.findElement(By.id("content")).getText());
	}
}
