package mobile;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MobileBase {
	
	static WebDriver driver;
	static String addToHomeScreenCancelButtonXpath = "//button[text()='Cancel']";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initialize();
//		if(driver.findElement(By.xpath(addToHomeScreenCancelButtonXpath)).isDisplayed()) {
//			driver.findElement(By.xpath(addToHomeScreenCancelButtonXpath)).click();
//		}
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys("hi");
//		driver.findElement(By.xpath("//div[@data-testid='new-post-button']")).click();
		
	}

	public static void initialize() {
		System.setProperty("webdriver.chrome.driver", "/Users/Dell/Downloads/chromedriver_win32_94/chromedriver.exe");
		// for ghost testing
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless", "--window-size=1920,1200");
//		driver = new ChromeDriver(options);
		// for exixting window open
		
		Map<String, String> mobileEmulation = new HashMap<>();

		mobileEmulation.put("deviceName", "Pixel 2 XL");

		ChromeOptions chromeOptions = new ChromeOptions();

//		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
//		WebDriver driver = new ChromeDriver(chromeOptions);
		
//		ChromeOptions options = new ChromeOptions();
		
		driver = new ChromeDriver(chromeOptions);
		// normal brower
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//		driver.get("https://www.instagram.com/");
	}
}
