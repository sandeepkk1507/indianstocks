package trading.stocks;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ForTesting extends ExcelOperations {
	static WebDriver driver;
	static Set<String> symbol;
	static Map<String, List<String>> mapData = new HashMap<String, List<String>>();
	static Logger logger = Logger.getLogger(ForTesting.class);

	public static void main(String[] args) throws IOException, AWTException, InterruptedException {
		BasicConfigurator.configure();

//		updateDataInNumberValueSheetOfForTrading();
//		addDataToForTrading();
//		getImagesFroGoogleSearch();

//		getTopFiveMovers();
		// same as above combined
//		getprofitOrLossForTopFiveLosers(1);
		// same as above but for all the dates
		logger.info("Started");
		getprofitOrLossForTopFiveLosersForAllDates();
		logger.info("ended");

	}

	public static void updateDataInNumberValueSheetOfForTrading() throws IOException {
		updateCellFormulaNumberValue();
	}

	public static void initialize() {
		System.setProperty("webdriver.chrome.driver", "/Users/Dell/Downloads/chromedriver_win32_98/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	public static void getImageFromOnline(String companyName)
			throws MalformedURLException, IOException, InterruptedException {
		driver.get("https://www.google.com");
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(companyName + " stock");
//		if(driver.findElements(By.xpath("(//input[@value='Google Search'])[1]")).size()==1) {
//			driver.findElement(By.xpath("(//input[@value='Google Search'])[1]")).click();
//			
//		}
		driver.findElement(By.xpath("(//input[@value='Google Search'])[1]")).click(); // not needed I guess
		// driver.findElement(By.xpath("//ul/li[1]/div")).click();// clicking from the
		// dropdown suggestion
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

	static void getImagesFroGoogleSearch() throws MalformedURLException, IOException, InterruptedException {
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

		symbol.remove("TeamLease Ser.");// no
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
		symbol.remove("TCNS Clothing C");
		symbol.remove("Vakrangee");
		symbol.remove("Spandana Sphoor");
		symbol.remove("Indiamart Inter");
		symbol.remove("Bharat Rasayan");
		symbol.remove("CreditAccess Gr");
		symbol.remove("COFORGE LTD.");
		symbol.remove("Linde India");
//		symbol.remove("NFL");
//		symbol.remove("NFL");
//		symbol.remove("NFL");
//		symbol.remove("NFL");

		symbol.remove("NFL");
		for (String s : symbol) {
			getImageFromOnline(s);
		}
	}

	static void addDataToForTrading() throws IOException {
		symbol = getSymbolsFromSheet(1);
		mapData = getRecordsFromSheetForDateRange(symbol, "24012022");
		updateDataToExcel(mapData);
	}

	static void getTopFiveMovers() throws IOException {
		getTopFiveLosers();
	}

	static void getLeastFiveDouble(HashMap<String, Double> map) throws IOException {
//		HashMap<String, Double> sortedMap = sortMapBasedOnValues(map);

		List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(map.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
		for (Map.Entry<String, Double> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}

		System.out.println("Sorted Map is " + temp.toString());
		getGrowthOfLeastStock(map);
	}

	static HashMap<String, Double> sortMapBasedOnValues(HashMap<String, Double> map) {
		List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(map.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
		for (Map.Entry<String, Double> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	static void getGrowthOfLeastStock(HashMap<String, Double> map) throws IOException {
		Map<String, Double[]> mapToReturn = getGrowth(map);
		getProfitOrLoss(mapToReturn);

	}

	static void getProfitOrLoss(Map<String, Double[]> mapOfGrowthValues) {
		// start with 100%
		// calculate the profitloss for each days
		// if profit is > 2%, exit with no of days for profit
		// else return no profit for no of days

		Set<String> keyset = mapOfGrowthValues.keySet();
		String result = "loss";
		for (String key : keyset) {
			double maxpercentage = 100;
			double initialInvestment = 100;
			double profitLoss = 0;
			int noOfDays = 0;
			double currentValue = 100;
			double daysProfitLoss = 0;
			System.out.println("Stock is " + key);
			Double[] objArr = mapOfGrowthValues.get(key);
			for (Double doub : objArr) {
				if (doub == null)
					break;
				noOfDays++;
				profitLoss = doub;
				daysProfitLoss = profitLoss * initialInvestment / maxpercentage;

				currentValue = initialInvestment + daysProfitLoss;
				initialInvestment = currentValue;
//				System.out.println("profitLoss percentage " + doub);
//				System.out.println("daysProfitLoss " + daysProfitLoss);
//				System.out.println("currentValue " + currentValue);
//				System.out.println("initialInvestment " + initialInvestment);
				if (currentValue > 102) {
					result = "profit";
					break;
				}
			}
			System.out.println("Result is " + result + " noOfDays " + noOfDays + " currentValue is " + currentValue);
		}
	}

	static void getprofitOrLossForTopFiveLosers(int indexColumn) throws IOException {
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\StocksForTrade.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Home");

		int sheetRow = 1;
		int rownum = sheet.getPhysicalNumberOfRows();
		Map<String, Double> mapOfPercentage = new HashMap<String, Double>();
		HashMap<String, Double> mapOfLeastFive = new HashMap<String, Double>();
		ArrayList<Double> list = new ArrayList<>();
		String selectedDate = sheet.getRow(0).getCell(indexColumn).toString();
//		System.out.println("Date is " + selectedDate);
		logger.info("Date is " + selectedDate);
		for (int i = 1; i < rownum; i++) {
			Row row = sheet.getRow(i);
			Cell cellplus = row.getCell(indexColumn);
			Cell cell = row.getCell(indexColumn - 1);
			Cell stockNameCell = row.getCell(0);
			Double cellPlusDouble = Double.parseDouble(cellplus.toString());
			Double cellDouble = Double.parseDouble(cell.toString());

			Double value = ((cellPlusDouble / cellDouble) - 1) * 100;
//			System.out.println("Value is "+cellPlusDouble+" "+cellDouble+" "+value );
			mapOfPercentage.put(stockNameCell.toString(), value);
			list.add(value);
		}
//		System.out.println("Map is " + mapOfPercentage.toString());
		Collections.sort(list);
		for (int i = 0; i < 5; i++) {
			for (Map.Entry<String, Double> entry : mapOfPercentage.entrySet()) {
				if (list.get(i).equals(entry.getValue())) {
					mapOfLeastFive.put(entry.getKey(), list.get(i));
				}
			}
		}
//		ForTesting.getLeastFiveDouble(mapOfLeastFive);

		// ******getLeastFiveDouble
		List<Map.Entry<String, Double>> list2 = new LinkedList<Map.Entry<String, Double>>(mapOfLeastFive.entrySet());

		// Sort the list
		Collections.sort(list2, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
		for (Map.Entry<String, Double> aa : list2) {
			temp.put(aa.getKey(), aa.getValue());
		}

		logger.info("Sorted Map is " + temp.toString());
//		getGrowthOfLeastStock(mapOfLeastFive);

		// ******getGrowthOfLeastStock
//		Map<String, Double[]> mapToReturn = getGrowth(temp);

		// ***********************************getGrowth
		Map<String, Double[]> mapOfGrowthValues = new HashMap<String, Double[]>();
		for (int i = 0; i < rownum; i++) {
			Row row = sheet.getRow(i);
			Cell stockNameCell = row.getCell(0);
			int counter = 0;
			Double arrDouble[] = new Double[500];
			Double value = null;
			Set<Entry<String, Double>> entrySet = temp.entrySet();
			for (Entry<String, Double> entry : entrySet) {
				if (stockNameCell.toString().equals(entry.getKey())) {
					for (int j = 4; j < row.getPhysicalNumberOfCells(); j++) {
						Cell cellplus = row.getCell(j);
						Cell cell = row.getCell(j - 1);
						Double cellPlusDouble = Double.parseDouble(cellplus.toString());
						Double cellDouble = Double.parseDouble(cell.toString());

						value = ((cellPlusDouble / cellDouble) - 1) * 100;
						arrDouble[counter++] = value;
					}
					mapOfGrowthValues.put(stockNameCell.toString(), arrDouble);
				}
			}
		}

//		getProfitOrLoss(mapToReturn);

		// ****************************getProfitOrLoss

		// start with 100%
		// calculate the profitloss for each days
		// if profit is > 2%, exit with no of days for profit
		// else return no profit for no of days

		Set<String> keyset = mapOfGrowthValues.keySet();
		String result = "loss";

//		FileInputStream file_write = new FileInputStream(
//				"C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\StocksForTrade.xlsx");
//		XSSFWorkbook workbook_write = new XSSFWorkbook();
//		XSSFSheet spreadsheet = workbook_write.createSheet();
		Map<String, Object[]> resultMap = new HashMap<String, Object[]>();

		for (String key : keyset) {
			double maxpercentage = 100;
			double initialInvestment = 100;
			double profitLoss = 0;
			int noOfDays = 0;
			double currentValue = 100;
			double daysProfitLoss = 0;
//			System.out.println("Stock is " + key);
			Double[] objArr = mapOfGrowthValues.get(key);
			for (Double doub : objArr) {
				if (doub == null)
					break;
				noOfDays++;
				profitLoss = doub;
				daysProfitLoss = profitLoss * initialInvestment / maxpercentage;

				currentValue = initialInvestment + daysProfitLoss;
				initialInvestment = currentValue;
//						System.out.println("profitLoss percentage " + doub);
//						System.out.println("daysProfitLoss " + daysProfitLoss);
//						System.out.println("currentValue " + currentValue);
//						System.out.println("initialInvestment " + initialInvestment);
				if (currentValue > 102) {
					result = "profit";
					break;
				}
			}

			logger.info("Result for stock " + key + " is " + result + " noOfDays " + noOfDays + " currentValue is "
					+ currentValue);

			resultMap.put(key, new Object[] {selectedDate, key, result, noOfDays, currentValue});
			
			
//			XSSFRow row;
//			row = spreadsheet.createRow(sheetRow++);
//
//			Cell cell = row.createCell(0);
//			cell.setCellValue(key);
//			row.createCell(1).setCellValue(result);
//			row.createCell(2).setCellValue(result);
//			row.createCell(3).setCellValue(result);
		}
		addTopTenStockData("RESULT", resultMap);
//		FileOutputStream out = new FileOutputStream(
//				new File("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\GFGsheet.xlsx"));
//
//		workbook.write(out);
//		out.close();
	}

	static void getprofitOrLossForTopFiveLosersForAllDates() throws IOException {
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\StocksForTrade.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Home");

		Row row = sheet.getRow(0);
		int colCount = row.getPhysicalNumberOfCells();

		for (int i = 3; i < colCount; i++) {
			getprofitOrLossForTopFiveLosers(i);
		}
	}
}
