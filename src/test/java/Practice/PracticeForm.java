package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeForm {
	// practice class for all form elements

	public static WebDriver driver1;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		setBrowser();
		assignment6();	
	}

	public static void setBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver1 = new ChromeDriver();
	}
	
	public static void assignment1()
	{
		driver1.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(driver1.findElement(By.id("checkBoxOption1")).isSelected())
		{
			System.out.println("Checkbox 1 is selected");
			driver1.findElement(By.id("checkBoxOption1")).click();
		}
		else if(!driver1.findElement(By.id("checkBoxOption1")).isSelected())
		{
			System.out.println("Checkbox 1 is not selected");
			driver1.findElement(By.id("checkBoxOption1")).click();
		}
		System.out.println(driver1.findElements(By.xpath("//input[@type='checkbox']")).size());
	}
	
	//Method defination: this method select a checkbox, reads its label, select the label and send the label to text box and check alert text and accept alert.
	public static void assignment6()
	{
		driver1.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(!driver1.findElement(By.id("checkBoxOption2")).isSelected())
		{
			System.out.println("Checkbox 2 is selected");
			driver1.findElement(By.id("checkBoxOption2")).click();
		}
		String checkboxLabel = driver1.findElement(By.xpath("//*[@id='checkbox-example']/fieldset/label[2]")).getText();
		
		System.out.println(checkboxLabel + "label of checkbox");
		WebElement selectElement = driver1.findElement(By.id("dropdown-class-example"));
		Select dropdown = new Select(selectElement);
//		dropdown.selectByValue(checkboxLabel); // value is different then visible text
		dropdown.selectByVisibleText(checkboxLabel); //visible text is what we see in drop down
		
		driver1.findElement(By.id("name")).sendKeys(checkboxLabel);
		
		driver1.findElement(By.id("alertbtn")).click();

		String alert = driver1.switchTo().alert().getText();
		System.out.println("alert text " + alert);
		
		String alertText = driver1.switchTo().alert().getText();
		System.out.println(alertText.contains(checkboxLabel));
		if(alertText.contains(checkboxLabel))
		{
			System.out.println("Success");
		}
		driver1.switchTo().alert().accept();
	
	}
	//check complete form and automate all fields
}
