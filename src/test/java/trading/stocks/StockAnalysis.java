package trading.stocks;

import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import mobile.ChromeMobileEmulator;

public class StockAnalysis {
	public static String moneycontrol_url = "https://www.moneycontrol.com/stocks/marketstats/nsegainer/index.php";
	public static String nse_url = "https://www.nseindia.com/market-data/live-equity-market";
	public static String xpath_midcap100 = "//a[contains(text(),'NIFTY Midcap 100')]";
	public static String xpath_smallcap100 = "//a[normalize-space()='NIFTY Smallcap 100']";
	public static String xpath_nifty100 = "//a[normalize-space()='NIFTY 100']";
	public static String xpath_table = "//body/section[@id='mc_content']/section/section/div/div/div/div/div/table[1]/thead/tr/th";
	public static String xpath_table_row = "//body/section[@id='mc_content']/section/section/div/div/div/div/div/table[1]/tbody/tr";
	public static String xpath_table_CompanyName = "//body/section[@id='mc_content']/section/section/div/div/div/div/div/table[1]/tbody/tr/td/span/a";
	public static String xpath_stock_toggle = "//a[@class=\"toggleIcon\"][@id=\"equityStockTablecol0\"]";
	public static String xpath_stockDate = "//span[@class=' FL ML15 opn13no_gray MT20']";
	public static String xpath_nse_dropdown = "//select[@id=\"equitieStockSelect\"]";
	public static String xpath_nse_table = "//*[@id=\"indicesTable\"]/thead/tr/th";
//	public static String xpath_nse_table = "//*[@id=\"indices_stocks\"]/div[2]/div/div/table/tbody[1]/tr/th";
//	public static String xpath_nse_table_row = "//*[@id=\"indices_stocks\"]/div[2]/div/div/table/tbody[2]/tr";
	public static String xpath_nse_table_row = "//*[@id=\"indicesTable\"]/tbody/tr";
	public static String nifty500_url = "https://www.moneycontrol.com/markets/indian-indices/top-nse-500-companies-list/7?classic=true";
	public static String midcap150_url = "https://www.moneycontrol.com/markets/indian-indices/top-nsemidcap-150-companies-list/111?classic=true";
	public static String smallcap250_url = "https://www.moneycontrol.com/markets/indian-indices/top-nsesmallcap-250-companies-list/114?classic=true";
	public static String niftygain_url = "https://www.moneycontrol.com/stocks/marketstats/nse-gainer/nifty-100_28/";
	public static String niftyloss_url = "https://www.moneycontrol.com/stocks/marketstats/nse-loser/nifty-100_28/";
	public static String midcapgain_url = "https://www.moneycontrol.com/stocks/marketstats/nse-gainer/nifty-midcap-100_27/";
	public static String midcaploss_url = "https://www.moneycontrol.com/stocks/marketstats/nse-loser/nifty-midcap-100_27/";
	public static String smallcapgain_url = "https://www.moneycontrol.com/stocks/marketstats/nse-gainer/nifty-smallcap-100_53/";
	public static String smallcaploss_url = "https://www.moneycontrol.com/stocks/marketstats/nse-loser/nifty-smallcap-100_53/";
	public static WebDriver driver;
	public static HashMap<String, Integer> midCapMap;
	public static HashMap<String, Integer> smallCapMap;
	public static HashMap<String, Integer> NiftyMap;
	public static String uniqueUrl;
	public static String gainOrLoss;
	public static String stockDate;
	public static ExcelOperations excelOperations;
	public static String NIFTYGAIN = "NIFTY100-GAIN";
	public static String NIFTYLOSS = "NIFTY100-LOSS";
	public static String MIDCAPGAIN = "MIDCAP-GAIN";
	public static String MIDCAPLOSS = "MIDCAP-LOSS";
	public static String SMALLCAPGAIN = "SMALLCAP-GAIN";
	public static String SMALLCAPLOSS = "SMALLCAP-LOSS";
	public static String NIFTY500 = "NIFTY500";
	public static String NIFTYMIDCAP150 = "NIFTYMIDCAP150";
	public static String NIFTYSMALLCAP250 = "NIFTYSMALLCAP250";
	public static String companyName;
	public static String change;
	public static String xpath_liveMarket = "//a[normalize-space()='Live Markets']";
	public static String xpath_date = "//div[@id=\"nsebse_3\"]/div/div[@class=\"tableheading TAR\"]";
	public static Date date;
	public static int count;
	private static SimpleDateFormat formatter;
	private static String strDate;
	public static Map<String, String> topFiveNiftyGainMapData = new HashMap<String, String>();
	public static Map<String, String> topFiveNiftyLossMapData = new HashMap<String, String>();
	public static Map<String, String> topFiveMidcapGainMapData = new HashMap<String, String>();
	public static Map<String, String> topFiveMidcapLossMapData = new HashMap<String, String>();
	public static Map<String, String> topFiveSmallcapGainMapData = new HashMap<String, String>();
	public static Map<String, String> topFiveSmallcapLossMapData = new HashMap<String, String>();

	public static String niftyGainImagePath = "C:\\Users\\Dell\\Documents\\Stocks\\instagramnifty100gainpost.exe";
	public static String niftyLossImagePath = "C:\\Users\\Dell\\Documents\\Stocks\\instagramnifty100losspost.exe";
	public static String midcapGainImagePath = "C:\\Users\\Dell\\Documents\\Stocks\\instagrammidcapgainpost.exe";
	public static String midcapLossImagePath = "C:\\Users\\Dell\\Documents\\Stocks\\instagrammidcaplosspost.exe";
	public static String smallcapGainImagePath = "C:\\Users\\Dell\\Documents\\Stocks\\instagramsmallcapgainpost.exe";
	public static String smallcapLossImagePath = "C:\\Users\\Dell\\Documents\\Stocks\\instagramsmallcaplosspost.exe";

	public static String niftyGainCaption = "Do you own any of these stocks? #nifty #largecap #nifty100 #trading #gain #top5 #money #stocks #stockmarket #nse #profit #financial #financialtrends";
	public static String niftyLossCaption = "#largecap #nifty #nifty100 #trading #gainloss #top5 #money #stocks #stockmarket #nse #loss #financial #financialtrends";
	public static String midcapGainCaption = "Do you own any of these stocks? #midcap #nifty #trading #gain #top5 #money #stocks #stockmarket #nse #profit #financial #financialtrends";
	public static String midcapLossCaption = "#midcap #nifty #trading #gainloss #top5 #money #stocks #stockmarket #nse #profit #financial #financialtrends";
	public static String smallcapGainCaption = "Do you own any of these stocks? #smallcap #nifty #trading #gain #top5 #money #stocks #stockmarket #nse #profit #financial #financialtrends";
	public static String smallcapLossCaption = "#smallcap #nifty #trading #gainloss #top5 #money #stocks #stockmarket #nationalstockexchange #nse #loss #financial #financialtrends";

//	public StockAnalysis() {
//		// TODO Auto-generated constructor stub
//		excelOperations = new ExcelOperations();
//	}

	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		initialize();
//
		date = new Date();
		formatter = new SimpleDateFormat("dd/MMM/yyyy");
		strDate = formatter.format(date);

		getTopTenStockData(niftygain_url, NIFTYGAIN);
		getTopTenStockData(niftyloss_url, NIFTYLOSS);
		getTopTenStockData(midcapgain_url, MIDCAPGAIN);
		getTopTenStockData(midcaploss_url, MIDCAPLOSS);
		getTopTenStockData(smallcapgain_url, SMALLCAPGAIN);
		getTopTenStockData(smallcaploss_url, SMALLCAPLOSS);

		getAllStockProgress(nifty500_url, NIFTY500);
		getAllStockProgress(midcap150_url, NIFTYMIDCAP150);
		getAllStockProgress(smallcap250_url, NIFTYSMALLCAP250);

		topFiveNiftyGainMapData = getTopFiveStockDataToAddInImage(niftygain_url, NIFTYGAIN);
		topFiveNiftyLossMapData = getTopFiveStockDataToAddInImage(niftyloss_url, NIFTYLOSS);
		topFiveMidcapGainMapData = getTopFiveStockDataToAddInImage(midcapgain_url, MIDCAPGAIN);
		topFiveMidcapLossMapData = getTopFiveStockDataToAddInImage(midcaploss_url, MIDCAPLOSS);
		topFiveSmallcapGainMapData = getTopFiveStockDataToAddInImage(smallcapgain_url, SMALLCAPGAIN);
		topFiveSmallcapLossMapData = getTopFiveStockDataToAddInImage(smallcaploss_url, SMALLCAPLOSS);

		niftyGainCaption = updateCaption(topFiveNiftyGainMapData, niftyGainCaption);
		niftyLossCaption = updateCaption(topFiveNiftyLossMapData, niftyLossCaption);
		midcapGainCaption = updateCaption(topFiveMidcapGainMapData, midcapGainCaption);
		midcapLossCaption = updateCaption(topFiveMidcapLossMapData, midcapLossCaption);
		smallcapGainCaption = updateCaption(topFiveSmallcapGainMapData, smallcapGainCaption);
		smallcapLossCaption = updateCaption(topFiveSmallcapLossMapData, smallcapLossCaption);

		ChromeMobileEmulator.initializeTheMobileBrowser();
		ChromeMobileEmulator.loginToInstagram();

//		ChromeMobileEmulator.uploadImageToInsta(niftyGainImagePath, niftyGainCaption);
//		ChromeMobileEmulator.uploadImageToInsta(niftyLossImagePath, niftyLossCaption);
//		ChromeMobileEmulator.uploadImageToInsta(midcapGainImagePath, midcapGainCaption);
		ChromeMobileEmulator.uploadImageToInsta(midcapLossImagePath, midcapLossCaption);
		ChromeMobileEmulator.uploadImageToInsta(smallcapGainImagePath, smallcapGainCaption);
		ChromeMobileEmulator.uploadImageToInsta(smallcapLossImagePath, smallcapLossCaption);

		ChromeMobileEmulator.teardown();

//		ChromeMobileEmulator.instagramUpload();

//		updateCellColor(NIFTY500);
//		updateCellColor(NIFTYMIDCAP150);
//		updateCellColor(NIFTYSMALLCAP250);

	}

	public static void initialize() {
		System.setProperty("webdriver.chrome.driver", "/Users/Dell/Downloads/chromedriver_win32_96/chromedriver.exe");
		// for ghost testing
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--window-size=1920,1200");
		driver = new ChromeDriver(options);
		// for exixting window open
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
//		driver = new ChromeDriver(options);
		// normal brower
//		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//		driver.get(url);
	}

	public static void getTopTenStockData(String uniqueUrl, String type) throws IOException {
		driver.get(uniqueUrl);
//		List<WebElement> col = driver.findElements(By.xpath(xpath_table));

		List<WebElement> row = driver.findElements(By.xpath(xpath_table_row));

		stockDate = driver.findElement(By.xpath(xpath_stockDate)).getText();

		Map<String, Object[]> mapData = new HashMap<String, Object[]>();
//		mapData.put("0", new Object[] {"Company Name", "High", "Low", "Last Price", "Prev Close", "Change", "Gain/Loss", "Date"});
		if (row.size() < 10) {
			count = row.size();
		} else
			count = 10;

		for (int i = 0; i < count; i++) {

			String[] companyName = row.get(i).getText().split("\n");
			String[] stockData = companyName[1].split(" ");
			mapData.put("" + i, new Object[] { companyName[0], stockData[0], stockData[1], stockData[2], stockData[3],
					stockData[4], stockData[5], strDate });
		}
		ExcelOperations.addTopTenStockData(type, mapData);
	}

	public static void getAllStockProgress(String url, String sheetName) throws InterruptedException, IOException {
		driver.get(url);
		// //*[@id="equityStockTable"]/tbody/tr[5]/td[1]/a - NSE Name
		// //*[@id="equityStockTable"]/tbody/tr[10]/td[8] - change
//		WebElement table = driver.findElement(By.xpath(xpath_nse_table));

//		List<WebElement> col = driver.findElements(By.xpath(xpath_nse_table));
//		System.out.println("Col size " + col.size());

		List<WebElement> row = driver.findElements(By.xpath(xpath_nse_table_row));
//		System.out.println("Row size " + row.size());
		Map<String, String> mapData = new HashMap<String, String>();
		for (int i = 1; i <= row.size(); i++) {
//			companyName = driver
//					.findElement(By
//							.xpath("//*[@id=\"indices_stocks\"]/div[2]/div/div/table/tbody[2]/tr[" + i + "]/td[1]/p/a"))
//					.getText();
			companyName = driver.findElement(By.xpath("//*[@id=\"indicesTable\"]/tbody/tr[" + i + "]/td/a")).getText();
//			change = driver
//					.findElement(
//							By.xpath("//*[@id=\"indices_stocks\"]/div[2]/div/div/table/tbody[2]/tr[" + i + "]/td[3]"))
//					.getText();
			change = driver.findElement(By.xpath("//*[@id=\"indicesTable\"]/tbody/tr[" + i + "]/td[3]")).getText();
			mapData.put(companyName, change);
//			System.out.print("Company name is "+companyName+"----");
//			System.out.println("Change is "+change);
		}
		TreeMap<String, String> tm = new TreeMap<String, String>(mapData);
		ExcelOperations.addAllStockData2(sheetName, tm, strDate);
	}

	public static void updateCellColor(String sheetName) throws IOException {
		ExcelOperations.updateCellColorInExcel(sheetName);
	}

	public static Map<String, String> getTopFiveStockDataToAddInImage(String uniqueUrl, String type)
			throws IOException {
		driver.get(uniqueUrl);
//		List<WebElement> col = driver.findElements(By.xpath(xpath_table));

		List<WebElement> row = driver.findElements(By.xpath(xpath_table_row));

		stockDate = driver.findElement(By.xpath(xpath_stockDate)).getText();

		Map<String, String> mapData = new HashMap<String, String>();
		if (row.size() < 5) {
			count = row.size();
		} else
			count = 5;

		for (int i = 0; i < count; i++) {

			String[] companyName = row.get(i).getText().split("\n");
			String[] stockData = companyName[1].split(" ");
			mapData.put(companyName[0], stockData[5]);
		}
		AddTextToImage.putDataToImage(mapData, type);

		return mapData;
	}

	public static String updateCaption(Map<String, String> mapData, String caption) {
		final StringBuilder builder = new StringBuilder();
		builder.append(caption);
		Set<String> keySet = mapData.keySet();
		keySet.forEach((i) -> {
			if (i.contains(" ")) {
				i = i.replace(" ", " #");
			}
			builder.append(" #" + i);
		});

		String concatenatedString = builder.toString();
		return concatenatedString;
	}

}
