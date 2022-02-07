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
		
		File folder = new File("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\companylogos");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
//		    System.out.println("File " + listOfFiles[i].getName());
		    symbol.remove(listOfFiles[i].getName().replace(".png", ""));
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
//		System.out.println("All items "+symbol.toString()+" size is "+symbol.size());
		
		symbol.remove("TeamLease Ser.");//no
		symbol.remove("ICICI Bank");
		symbol.remove("Mahindra CIE");
		symbol.remove("Zensar Tech");
		symbol.remove("Tata Elxsi");
		symbol.remove("Godrej Consumer");
		symbol.remove("Grindwell Norto");
		symbol.remove("Sequent Scienti");
		symbol.remove("Mishra Dhatu Ni");
		symbol.remove("TATA Cons. Prod");
		symbol.remove("Dhani Services");
		symbol.remove("NFL");
		symbol.remove("Birla Corp");
		symbol.remove("NLC India");
		symbol.remove("Central Bank");
		symbol.remove("Cadila Health");
		symbol.remove("Phillips Carbon");
		symbol.remove("Sundram");
		symbol.remove("Radico Khaitan");
		symbol.remove("Gujarat Gas");
		symbol.remove("Heidelberg Cem");
		symbol.remove("Motilal Oswal");
		symbol.remove("NTPC");
		symbol.remove("MRPL");
//		symbol.remove("NFL");
//		symbol.remove("NFL");
//		symbol.remove("NFL");
//		symbol.remove("NFL");
//		symbol.remove("NFL");
//		symbol.remove("NFL");
//		symbol.remove("NFL");
		
		
		symbol.remove("NFL");
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
//		if(driver.findElements(By.xpath("(//input[@value='Google Search'])[1]")).size()==1) {
//			driver.findElement(By.xpath("(//input[@value='Google Search'])[1]")).click();
//			
//		}
		driver.findElement(By.xpath("(//input[@value='Google Search'])[1]")).click(); //not needed I guess
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
