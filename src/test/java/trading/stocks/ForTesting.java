package trading.stocks;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ForTesting extends ExcelOperations {
	static WebDriver driver;
	static Set<String> symbol;
	static Map<String, List<String>> mapData = new HashMap<String, List<String>>();

	public static void main(String[] args) throws IOException, AWTException, InterruptedException {

		// to add data from NSE to StocksForTrade
		symbol = getSymbolsFromSheet(0);
////		mapData = getRecordsFromSheet(symbol);
//		mapData = getRecordsFromSheetForDateRange(symbol, "10012022");
//		updateDataToExcel(mapData);

		// ************************************************************

		// To get stock companies logo
		initialize();
//		getImageFromOnline("3MINDIA");
		symbol.remove("Future Retail");
		symbol.remove("IRCTC");
		symbol.remove("Emami");
		symbol.remove("Infosys");
		symbol.remove("Manappuram Fin");
		symbol.remove("Symphony");
		symbol.remove("Sobha");//not saved
		symbol.remove("Aditya Birla F");
		symbol.remove("Firstsource Sol");//not saved
		symbol.remove("Bank of India");
		symbol.remove("HDFC AMC");
		
		
		for (String s : symbol) {
			getImageFromOnline(s);
		}
		

	}

	public static void initialize() {
		System.setProperty("webdriver.chrome.driver", "/Users/Dell/Downloads/chromedriver_win32_96/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	public static void getImageFromOnline(String companyName) throws MalformedURLException, IOException, InterruptedException {
		driver.get("https://www.google.com");
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(companyName+" stock");
		driver.findElement(By.xpath("(//input[@value='Google Search'])[1]")).click();
		//driver.findElement(By.xpath("//ul/li[1]/div")).click();// clicking from the dropdown suggestion
//		driver.findElement(By.xpath("//a[text()='Images']")).click();// to click images tag
//		if(driver.findElements(By.xpath("//a/g-img/img")).size()==1) {
//			driver.findElement(By.xpath("//a/g-img/img")).click(); //to click on the image
//		} else {
//			driver.findElement(By.xpath("//span[contains(text(),'"+companyName+"')]/parent::h2/parent::div/parent::div/parent::div/parent::div/following-sibling::div/div/div/a/g-img/img")).click();
//		}
//		
//		////span[contains(text(),'Manappuram Fin')]/parent::h2/parent::div/parent::div/parent::div/parent::div/following-sibling::div/div/div/a/g-img/img
////		WebElement companyImage = driver.findElement(By.xpath("(//img[contains(@alt,'"+companyName+"')])[1]"));
////		 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", companyImage);
////		driver.findElement(By.xpath("(//img[contains(@alt,'"+companyName+"')])[1]")).click();
//		String url = driver.findElement(By.xpath("(//span[contains(text(),' × ')])[1]/preceding-sibling::img"))
//				.getAttribute("src");
//		System.out.println("URL for "+companyName+" is "+url);
//		if(!url.contains("data:image")) {
//			if(url.contains(".svg")) {
////				BufferedImage bufferedImage = ImageIO.read(new URL(url));
////				File outputfile = new File("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\companylogos\\"+companyName+".svg");
////				ImageIO.write(bufferedImage, "svg", outputfile);
//			} else {
//				BufferedImage bufferedImage = ImageIO.read(new URL(url));
//				File outputfile = new File("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\companylogos\\"+companyName+".png");
//				ImageIO.write(bufferedImage, "png", outputfile);
//				System.out.println("Success");
//			}
//			
//		}
		Thread.sleep(20000);
		
	}

}
