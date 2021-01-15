package forms;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.record.chart.UnitsRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

// practice program on form controls
public class PracticeProg {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		//WebDriverManager class manages driver of all the browsers
		WebDriverManager.firefoxdriver().setup();
		FirefoxDriver driver = new FirefoxDriver();

	//	WebDriverManager.chromedriver().setup();
	//	ChromeDriver driver = new ChromeDriver();
		
		PracticeProg obj = new PracticeProg();
	//	obj.testCheckBox(driver);
		
		obj.testMouseOver(driver);
	}
	
	public void testNPLTLogin(FirefoxDriver driver)
	{
		//Login in localhost/nplt
		
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/nltp/index.php?route=account/login");
		//driver.findElement(By.xpath("//*[@id=\"input-email\"]"));
		driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys("ruchi.dogra.cdac@gmail.com");
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys("Lotus@123");
		
	//	driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/form/input")).click(); // absolute xpath
	//	driver.findElement(By.xpath("//div/div[2]/div/form/input")).click(); //partial absolute xpath
		driver.findElement(By.xpath("//input[@type = 'submit' and @value = 'Login']")).click(); //relative xpath


	}
	public void testDropBox(FirefoxDriver driver)
	{
		//Practice working on Dropdown HTML forms
		
			driver.get("http://www.echoecho.com/tooldropdown.htm");
			WebElement dropdown = driver.findElement(By.xpath("/html/body/div[2]/table[9]/tbody/tr/td[4]/table/tbody/tr/td/div/span/form[1]/div/p/select"));
			Select select = new Select(dropdown);
			select.selectByVisibleText("MICROSOFT");
			
			//navigate to another page and get values of drop down
			driver.navigate().to("http://www.echoecho.com/sampledropdown.htm");
			
			WebElement dropdown1 = driver.findElement(By.xpath("/html/body/form[2]/select"));
			//List <WebElement> values = driver.findElements(By.tagName("option")); // this way I am getting all the list of both the dropdowns
			Select select2 = new Select(dropdown1);
			List <WebElement> values = select2.getOptions();
			
			System.out.println(values.size());
			for(WebElement value : values) 
			{
				System.out.println("dropdown values are: "+ value.getText());
			}
	}
	
	public void testCheckBox(FirefoxDriver driver)
	{
		driver.navigate().to("http://tizag.com/htmlT/htmlcheckboxes.php");
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
	//	WebElement checkBox = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[1]/td[2]/table/tbody/tr/td/div[4]/input[1]"));
	//	checkBox.click();
	
		//this code will select all the checkboxes ie. 8
	/*	List <WebElement> checkboxes = driver.findElements(By.name("sports"));
		System.out.println(checkboxes.size());
		
		for(WebElement checkbox : checkboxes)
		{
			checkbox.click();
		}
	*/	
		//code to select checkbox of first block only i.e. 4
		//First locate the block containing 4 checkboxes
		WebElement block = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[1]/td[2]/table/tbody/tr/td/div[6]"));
		List <WebElement> checkboxes = block.findElements(By.name("sports"));
		
		System.out.println(checkboxes.size());
		
		for(WebElement checkbox : checkboxes)
		{
			//System.out.println(checkbox.getText()); not working giving error
			System.out.println(checkbox.getAttribute("value"));
				if(!checkbox.isSelected())
					checkbox.click();
		}
		
		//other way of writing for loop
	/*	for(int i =0; i<checkboxes.size(); i++)
		{
			System.out.println("inside for loop");
			System.out.println(checkboxes.get(i).getText());
		}
	 */
}
	
	//method to test mouse movement using action class
	public void testMouseOver(FirefoxDriver driver)
	{
		driver.manage().window().maximize();
		driver.get("https://nplt.in/demo/");
		WebElement resMenu  = driver.findElement(By.xpath("/html/body/nav[2]/div/div[2]/ul/li[2]/a"));
		System.out.println(resMenu.getCssValue("font-family")); //getCssValue(CSS property name to be passed here)
		
		Actions act = new Actions(driver);
		
		act.moveToElement(resMenu).perform();
		
		driver.findElement(By.xpath("/html/body/nav[2]/div/div[2]/ul/li[2]/div/div/ul[1]/li[1]/a")).click();
		
		//navigate to test URL to test slider
		driver.navigate().to("https://jqueryui.com/resources/demos/slider/default.html");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		//move the slider in the mid of the slider bar 
		WebElement mainSlider = driver.findElement(By.id("slider"));
		
		WebElement slider = driver.findElement(By.xpath("//*[@id=\"slider\"]"));
		
		act.dragAndDropBy(slider, mainSlider.getSize().width/2,0).perform();  //x-axis = 400px and y-axis = 0px as its horizontal movement
			
	}

	
}
