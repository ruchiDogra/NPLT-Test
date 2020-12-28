package forms;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Register {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		FirefoxDriver driver = new FirefoxDriver();
		
		driver.get("http://localhost:8080/nltp/index.php?route=account/register");
		
		
		
		//Fill individual user register form
		driver.findElement(By.xpath("//input[@type = 'radio'and @value='10']")).click();
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		//System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div/div/form/fieldset[1]/div[1]/div/div[1]/label")).getText());
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("abc");
		driver.findElement(By.xpath("//*[@id= 'input-lastname']")).sendKeys("hello");
	
		//generate Random no , for generating new email id everyday
		Random random = new Random();
		int randomInt = random.nextInt(999);
		
		driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys("we" + randomInt + "@gmail.com");
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys("Lotus@123");
		driver.findElement(By.xpath("//*[@id='input-confirm']")).sendKeys("Lotus@123");
		driver.findElement(By.xpath("//form/div[2]/div/input[1]")).click(); //click checkbox
		
		driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click(); 
		
		//add wait so that all elements in new screen load properly
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		
	}

}
