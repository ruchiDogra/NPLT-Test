package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionClass {

	// In this class action class for mouse movement and page load is tried

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		setBrowser();
	//	npltActionClassExample();
		amazonActionClassExample();
	}

	public static void setBrowser() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	// this function will wait for page load
	public static void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(pageLoadCondition);
	}

	public static void npltActionClassExample() throws InterruptedException {
		driver.navigate().to("http://localhost:8080/nltp/index.php?route=account/login");// added this line as we have
		// to navigate to login page

		// @BeforeTest runs only once as this is a single test which runs for
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // after clicking new URL, page should be
		// refreshed
		driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys("ruchi.dogra.cdac@gmail.com");
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys("Lotus@123");

		driver.findElement(By.xpath("//input[@type = 'submit' and @value = 'Login']")).click(); // relative xpath

		// After clicking login button, it will redirect to new page
		// now add page load wait here and after complete page load click logout button

		// waitForLoad(driver);
		Thread.sleep(10000);

		// driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS); //after
		// clicking new URL, page should be refreshed
		try {
			driver.findElement(By.xpath("/html/body/nav[1]/div/div/ul/li[4]/a/span[1]")).click();

			// add if element <logout> present here, then logout the page , add try-catch
			WebElement accLogout = driver.findElement(By.xpath("/html/body/nav[1]/div/div/ul/li[4]/ul/li[5]/a"));

			Actions action = new Actions(driver);
			action.moveToElement(accLogout).perform();
			driver.findElement(By.xpath("/html/body/nav[1]/div/div/ul/li[4]/ul/li[5]/a")).click();
		} catch (Exception e) {
			System.out.println("exception is " + e.getMessage());
		}

	}
	// Include all functionality of Action class here

	public static void amazonActionClassExample()
	{
		driver.navigate().to("https://www.amazon.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions act = new Actions(driver);
		
		WebElement accMenuElement = driver.findElement(By.cssSelector("a[id='nav-link-accountList']")); 
		//move mouse to account menu
		act.moveToElement(accMenuElement).build().perform();
	
		//move mouse to search box, press shift key for typing in capital letters and then sendkey and perform action
		act.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("gaming").doubleClick().build().perform();
		
		//right click using context click
		act.moveToElement(accMenuElement).contextClick().build().perform();
		act.moveToElement(accMenuElement).clickAndHold().build().perform();
	}
}