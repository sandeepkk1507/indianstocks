package mobile;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeMobileEmulator {
	public static WebDriver driver;

//	public static void main(String args[]) throws AWTException, IOException, InterruptedException {
	public static void instagramUpload() throws AWTException, IOException, InterruptedException {
		initialize();
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("tradingtipswithsk@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Trading1@go");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//button[text()='Save Info']")).click();
		
		if(!driver.findElements(By.xpath("//button[text()='Cancel']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Cancel']")).click();
		}
		

		driver.findElement(By.xpath("//div[@data-testid='new-post-button']")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Users\\Dell\\Documents\\Stocks\\instagrammidcapgainpost.exe");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		driver.findElement(By.xpath("//textarea[@placeholder='Write a caption…']")).sendKeys("#midcap #nifty #trading #gain #top5 #money #stocks #stockmarket #nse #profit #financial #financialtrends");
		driver.findElement(By.xpath("//img[@alt='Preview of photo to be uploaded']")).click();
		driver.findElement(By.xpath("//button/span[text()='Add Location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).sendKeys("nse");
		driver.findElement(By.xpath("//span[text()='National Stock Exchange']//parent::div//parent::button")).click();
		driver.findElement(By.xpath("//button[text()='Share']")).click();
		Thread.sleep(3000);
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		
		driver.findElement(By.xpath("//div[@data-testid='new-post-button']")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Users\\Dell\\Documents\\Stocks\\instagrammidcaplosspost.exe");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		driver.findElement(By.xpath("//textarea[@placeholder='Write a caption…']")).sendKeys("#midcap #nifty #trading #gainloss #top5 #money #stocks #stockmarket #nse #profit #financial #financialtrends");
		driver.findElement(By.xpath("//img[@alt='Preview of photo to be uploaded']")).click();
		driver.findElement(By.xpath("//button/span[text()='Add Location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).sendKeys("nse");
		driver.findElement(By.xpath("//span[text()='National Stock Exchange']//parent::div//parent::button")).click();
		driver.findElement(By.xpath("//button[text()='Share']")).click();
		Thread.sleep(3000);
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		
		driver.findElement(By.xpath("//div[@data-testid='new-post-button']")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Users\\Dell\\Documents\\Stocks\\instagramnifty100gainpost.exe");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		driver.findElement(By.xpath("//textarea[@placeholder='Write a caption…']")).sendKeys("#nifty #largecap #nifty500 #trading #gain #top5 #money #stocks #stockmarket #nse #profit #financial #financialtrends");
		driver.findElement(By.xpath("//img[@alt='Preview of photo to be uploaded']")).click();
		driver.findElement(By.xpath("//button/span[text()='Add Location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).sendKeys("nse");
		driver.findElement(By.xpath("//span[text()='National Stock Exchange']//parent::div//parent::button")).click();
		driver.findElement(By.xpath("//button[text()='Share']")).click();
		Thread.sleep(3000);
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		
		driver.findElement(By.xpath("//div[@data-testid='new-post-button']")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Users\\Dell\\Documents\\Stocks\\instagramnifty100losspost.exe");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		driver.findElement(By.xpath("//textarea[@placeholder='Write a caption…']")).sendKeys("#largecap #nifty #nifty500 #trading #gainloss #top5 #money #stocks #stockmarket #nse #loss #financial #financialtrends");
		driver.findElement(By.xpath("//img[@alt='Preview of photo to be uploaded']")).click();
		driver.findElement(By.xpath("//button/span[text()='Add Location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).sendKeys("nse");
		driver.findElement(By.xpath("//span[text()='National Stock Exchange']//parent::div//parent::button")).click();
		driver.findElement(By.xpath("//button[text()='Share']")).click();
		Thread.sleep(3000);
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		
		driver.findElement(By.xpath("//div[@data-testid='new-post-button']")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Users\\Dell\\Documents\\Stocks\\instagramsmallcapgainpost.exe");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		driver.findElement(By.xpath("//textarea[@placeholder='Write a caption…']")).sendKeys("#smallcap #nifty #trading #gain #top5 #money #stocks #stockmarket #nse #profit #financial #financialtrends");
		driver.findElement(By.xpath("//img[@alt='Preview of photo to be uploaded']")).click();
		driver.findElement(By.xpath("//button/span[text()='Add Location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).sendKeys("nse");
		driver.findElement(By.xpath("//span[text()='National Stock Exchange']//parent::div//parent::button")).click();
		driver.findElement(By.xpath("//button[text()='Share']")).click();
		Thread.sleep(3000);
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		
		driver.findElement(By.xpath("//div[@data-testid='new-post-button']")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Users\\Dell\\Documents\\Stocks\\instagramsmallcaplosspost.exe");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		driver.findElement(By.xpath("//textarea[@placeholder='Write a caption…']")).sendKeys("#smallcap #nifty #trading #gainloss #top5 #money #stocks #stockmarket #nationalstockexchange #nse #loss #financial #financialtrends");
		driver.findElement(By.xpath("//img[@alt='Preview of photo to be uploaded']")).click();
		driver.findElement(By.xpath("//button/span[text()='Add Location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Find a location']")).sendKeys("nse");
		driver.findElement(By.xpath("//span[text()='National Stock Exchange']//parent::div//parent::button")).click();
		driver.findElement(By.xpath("//button[text()='Share']")).click();
		Thread.sleep(3000);
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		if(!driver.findElements(By.xpath("//button[text()='Not Now']")).isEmpty()) {
			driver.findElement(By.xpath("//button[text()='Not Now']")).click();
		}
		teardown();
	}
	
	public static void initialize() {
		System.setProperty("webdriver.chrome.driver", "/Users/Dell/Downloads/chromedriver_win32_96/chromedriver.exe");

		// here creating our first map for deviceName
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Pixel 2 XL");
		// here creating the second map with key mobileEmulation
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		// setting DesiredCapabilities for chrome
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		driver = new ChromeDriver(capabilities);
		driver.get("http://www.instagram.com");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	public static void teardown() {
		if(driver!= null) {
			driver.close();
		}
	}
}
