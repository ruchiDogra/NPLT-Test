package Practice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoSuggestEditBox {
	
		//Method: wait for complete page load
		public static void waitForLoad(WebDriver driver) 
		{
	        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() 
	        {
	           public Boolean apply(WebDriver driver)
	           {
	               return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	           }
	        };

	        WebDriverWait wait = new WebDriverWait(driver, 120); //unit is in seconds - 60 secs
	        wait.until(pageLoadCondition);
	    }


	public static void main(String[] args) {
		
		//Code to test Auto-suggest functionality
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // maximize the browser
        driver.get("https://www.google.com/");

        waitForLoad(driver); // wait for page load 
        // Enter "Time in" text in Search edit box and wait for 30 seconds to load the
        // results
        driver.findElement(By.name("q")).sendKeys("Time in");
        
        /*Get the text after ajax call*/
	/*	For ajax call , to load the webelement properly it important to add wait
	 * 
	 * WebElement TextElement = driver.findElement(By.className("radiobutton"));
	 
		wait.until(ExpectedConditions.visibilityOf(TextElement));
		String textAfter = TextElement.getText().trim();
		
		//Verify both texts before ajax call and after ajax call text.
		Assert.assertNotEquals(textBefore, textAfter);
		System.out.println("Ajax Call Performed");
		
      */  
        
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
        // get all the auto populated results - using custom xpath
        List<WebElement> elements = driver.findElements(By.xpath("//ul[@class='erkvQe']//li//span"));

        System.out.println(elements.size());

        // Traverse each auto-suggest result and click on the result "time in india", if
        // this suggestion is displayed.
        
        if(elements.size() > 0) 
        {
        	for (WebElement element : elements) 
        	{
        		//selectedElement = element;
        		
        	//if (element.getText().equalsIgnoreCase("time in india")) 
        		if(element.getText().contains("time in germany"))
        		{
          			element.click();
        			break;
        		}
            
        	}
        }
        
        //Display the time after clicking the result
        //String timeResult = driver.findElement(By.xpath("//div[@class='gsrt vk_bk dDoNo']")).getText();
        waitForLoad(driver);
        String timeResult = driver.findElement(By.xpath("//div[contains(@class, 'gsrt vk_bk dDoNo')]")).getText();
        
        //By.xpath("//div[@class='gsrt vk_bk dDoNo']") unable to locate this element
        System.out.println(" Current Time is: " + timeResult);
        driver.close();


		
	}

}
