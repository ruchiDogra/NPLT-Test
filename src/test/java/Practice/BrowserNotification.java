package Practice;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserNotification {

	public static void main(String[] args) {
		// this class is about browser notification handling. Its working fine
		
		/*
		 * Chrome-

			DesiredCapabilities caps = new DesiredCapabilities();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--lang=es"); -> Changing the language to spanish // 1- allow, 2-block
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			
			ChromeOptions options=new ChromeOptions();
			Map prefs=new HashMap();
			prefs.put(“profile.default_content_setting_values.notifications”, 1);
			options.setExperimentalOption(“prefs”,prefs);
			ChromeDriver driver=new ChromeDriver(options);
			
			Firefox-
			
			WebDriver driver ;
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference(“permissions.default.desktop-notification”, 1);
			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new FirefoxDriver(capabilities);
			driver.get(“your Web site”);
		 * 
		 */
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver;
		ChromeOptions options = new ChromeOptions();
		Map prefs = new HashMap();
		prefs.put("profile.default_content_setting_values.notifications", 1);  // 1- allow, 2-block
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
	}

}
