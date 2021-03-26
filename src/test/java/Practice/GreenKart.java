package Practice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GreenKart {

	public static WebDriver driver1;
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		driver1 = new ChromeDriver();
		
		driver1.manage().window().maximize();
		driver1.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List <WebElement> productList = driver1.findElements(By.xpath("//div[@class='products']/div"));
	//	List <WebElement> productList = driver1.findElements(By.cssSelector("div.products > div")); //showing 30 in console but 8 here
		System.out.println(productList.size());
		
		String []productsToAdd = {"Cucumber", "Tomato", "Brinjal"};
		for(WebElement eachProduct: productList)
		{
			//eachProduct webElement is a block , and now within this block we have to 
			//System.out.println(eachProduct.findElement(By.tagName("h4")).getText());
			String eachProductName = eachProduct.findElement(By.tagName("h4")).getText();
			String []productName = eachProductName.split("-");
		//	System.out.println(productName[0].trim());
			productName[0] = productName[0].trim();
			for(int i=0; i< productsToAdd.length; i++)
			if(productName[0].equalsIgnoreCase(productsToAdd[i]))
			{
				//	eachProduct.findElement(By.xpath("//button[text()='ADD TO CART']")).click(); //not working as expected
				eachProduct.findElement(By.tagName("button")).click();
				
			}
			
			//now add code for proceed to payment
			
		}
	}
	

}
