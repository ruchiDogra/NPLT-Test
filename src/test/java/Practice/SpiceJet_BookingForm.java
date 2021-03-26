package Practice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

// To Automate SpiceJet flight booking form
public class SpiceJet_BookingForm {

	// variable declaration
	public static WebDriver driver;

	// Method: wait for complete page load
	public static void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 120); // unit is in seconds - 60 secs
		wait.until(pageLoadCondition);
	}

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		// try to open browser in already opened
		driver = new ChromeDriver();

		// this code is not working on Ubuntu 16.04
		/*
		 * ChromeOptions options = new ChromeOptions();
		 * options.setExperimentalOption("debuggerAddress", "localhost:9014");
		 * System.setProperty("webdriver.chrome.whitelistedIps", "");
		 * 
		 * driver = new ChromeDriver(options);
		 */

		driver.get("https://www.spicejet.com/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		waitForPageLoad(driver); // code to load the page completely

		System.out.println(driver.getTitle());

		WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_DropDownListCurrency\"]"));

		Select selectDropdown = new Select(dropdown);
		selectDropdown.selectByValue("USD");

		List<WebElement> list = selectDropdown.getOptions();
		for (WebElement value : list) {
			System.out.println(value.getText());
		}

		// test case of UI
		// condition: when 1 way radio button is selected , return calender should be
		// grey , then assert true
		if (driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_rbtnl_Trip_0\"]")).isSelected()) {
			if (driver.findElement(By.xpath("//*[@id=\"Div1\"]")).getAttribute("style").contains("0.5")) {
				System.out.println("One way trip and return calender is grey");
				Assert.assertTrue(true);
			} else {
				System.out.println("One way trip but return calender is not grey");
				Assert.assertTrue(false);
			}
		}

		// test case of UI
		if (driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_rbtnl_Trip_1\"]")).isSelected()) {
			if (driver.findElement(By.xpath("//*[@id=\"Div1\"]")).getAttribute("style").contains("1")) {
				System.out.println("Round trip and return calender is enabled");
				Assert.assertTrue(true); // hard assert
			} else {
				Assert.assertTrue(false);
			}
		}
		onewayTicketBooking();
		// roundTicketBooking();
	}

	// check ticket booking by filling complete form
	public static void onewayTicketBooking() {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		if (!driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_rbtnl_Trip_0\"]")).isSelected()) {
			driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_rbtnl_Trip_0\"]")).click();
		}

		// from dropdown
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).sendKeys("Gwalior (GWL)");

		// to dropdown
		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_destinationStation1_CTXT\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_destinationStation1_CTXT\"]"))
				.sendKeys("Bengaluru (BLR)");

		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_view_date1\"]")).click();
		// driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[5]/td[1]/a")).click();

		// code to select date from calendar

		for (int row = 2; row <= 5; row++) {
			for (int col = 1; col <= 4; col++) {

				try {

					WebElement dateElement = driver
							.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[" + row + "]/td["
									+ col + "] [not(contains(@class,'ui-datepicker-unselectable'))]/a"));

					System.out.println(dateElement.getText());
					if (dateElement.getText().equalsIgnoreCase("28")) {
						dateElement.click();
						break;
					}

				} catch (Throwable e) {
					System.out.println(e.getMessage());
				}
			}
		}

		// select value in passenger drop down

		driver.findElement(By.xpath("//*[@id=\"divpaxinfo\"]")).click();
		WebElement adultElement = driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_Adult\"]"));
		Select adultValSelect = new Select(adultElement);
		adultValSelect.selectByValue("4");

		WebElement childElement = driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_Child\"]"));
		Select childValSelect = new Select(childElement);
		childValSelect.selectByValue("2");

		WebElement currencyElement = driver
				.findElement(By.xpath("//*[@id=\"ctl00_mainContent_DropDownListCurrency\"]"));
		Select currencyValSelect = new Select(currencyElement);
		currencyValSelect.selectByValue("USD");

		// click search flight button
		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_btn_FindFlights\"]")).click();

		// next page is show flight results

		if (driver.findElement(By.xpath("//*[@id=\"availabilityTable0\"]/tbody/tr[1]/th/div[2]/span[2]")).getText()
				.equalsIgnoreCase("Departure Flight")) {
			System.out.println("flights found");
		} else {
			System.out.println("flights not found");
		}

	}

	// use css selector in this method, as css selector are widely used
	public static void roundTicketBooking() {

		if (!driver.findElement(By.cssSelector("#ctl00_mainContent_rbtnl_Trip_1")).isSelected()) {
			driver.findElement(By.cssSelector("#ctl00_mainContent_rbtnl_Trip_1")).click();
		}

		// from dropdown
		driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_originStation1_CTXT")).sendKeys("Gwalior (GWL)");

		// to dropdown
		driver.findElement(By.xpath("#ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
		driver.findElement(By.xpath("#ctl00_mainContent_ddl_destinationStation1_CTXT")).sendKeys("Bengaluru (BLR)");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("#ctl00_mainContent_view_date1")).click();
		// driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[5]/td[1]/a")).click();

		// code to select date from calendar
		for (int row = 2; row <= 5; row++) {
			for (int col = 1; col <= 4; col++) {

				try {
					WebElement dateElement = driver.findElement(By.xpath(
							"//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[" + row + "]/td[" + col + "]/a"));
					if (dateElement.isEnabled()) {
						String date = dateElement.getText();
						System.out.println(date);
						if (date.equalsIgnoreCase("")) {
							dateElement.click();
							break;
						}
					}
				} catch (Throwable e) {
					System.out.println(e.getMessage() + "element not present");
				}
			}
		}

		// select value in passenger drop down
		driver.findElement(By.xpath("//*[@id=\"divpaxinfo\"]")).click();
		WebElement adultElement = driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_Adult\"]"));
		Select adultValSelect = new Select(adultElement);
		adultValSelect.selectByValue("4");

		WebElement childElement = driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_Child\"]"));
		Select childValSelect = new Select(childElement);
		childValSelect.selectByValue("2");

		WebElement currencyElement = driver
				.findElement(By.xpath("//*[@id=\"ctl00_mainContent_DropDownListCurrency\"]"));
		Select currencyValSelect = new Select(currencyElement);
		currencyValSelect.selectByValue("USD");

		// click search flight button
		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_btn_FindFlights\"]")).click();

	}

	public void multiCityTicketBooking() {

	}
}
