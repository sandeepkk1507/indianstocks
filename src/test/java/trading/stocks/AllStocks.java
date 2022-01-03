package trading.stocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AllStocks {
	static String strDate;
	static WebDriver driver;
	static String url;
	public static Map<String, String> mapData = new HashMap<String, String>();
	public static String masterSheet = "C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\NSEDailyDataTracker.xlsx";

	public static void main(String[] args) throws IOException, InterruptedException {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		strDate = formatter.format(date);
//		strDate = "30122021";
		url = "https://archives.nseindia.com/archives/nsccl/volt/CMVOLT_" + strDate + ".CSV";
//		csvToXlsConvertor();
//		HashMap<String, Object[]> comapnyData = getDataFromExcel();
//		addDataToExcel(comapnyData);

		// start from here
		initialize();
		// Step 1: Download CSV
		downloadCsvFromNse();
		Thread.sleep(2000);

		// Step 1a: Rename the file to CMVOLT.csv
		renameFileName();
		// Step 2a: Login to CloudConvert
		loginToCloudConvert();

		// Step 2b: Convert CSV to XLSX
		convertCsvToXlsx();
		// Step 3: Get data from XLSX
		String pathOfFile = "C:\\Users\\Dell\\Downloads\\CMVOLT.xlsx";
		mapData = getDataFromNse(pathOfFile);
		// Step 4: Put data in Master file
		addDataToMasterSheet(mapData);
		// Step 5: Delete the file
		deleteFile();

		// Step 6: Teardown
		teardown();
	}

	public static void initialize() {
		System.setProperty("webdriver.chrome.driver", "/Users/Dell/Downloads/chromedriver_win32_96/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	public static void downloadCsvFromNse() {
		driver.get(url);
	}

	public static void renameFileName() {
		File file = new File("C:\\Users\\Dell\\Downloads\\CMVOLT_" + strDate + ".csv");

		File rename = new File("C:\\Users\\Dell\\Downloads\\CMVOLT.csv");

		file.renameTo(rename);
	}

	public static void loginToCloudConvert() {
		driver.get("https://cloudconvert.com");
		driver.findElement(By.xpath("//a[contains(., 'Login')]")).click();
		driver.findElement(By.xpath("//input[@type='text' and @name='email']")).sendKeys("tradinggraphs");
		driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys("Trading1@go");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	public static void convertCsvToXlsx() throws InterruptedException, IOException {
		driver.get("https://cloudconvert.com/csv-to-xlsx");
		driver.findElement(By.xpath("//button[@type='button' and contains(., 'Select File')]")).click();
		Thread.sleep(1000);

		Runtime.getRuntime().exec("C:\\Users\\Dell\\Documents\\Stocks\\csvToXlsxConvert.exe");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='button' and contains(., 'Convert')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(., 'Download')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
	}

	public static Map<String, String> getDataFromNse(String filePath) throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rownum = sheet.getPhysicalNumberOfRows();

		for (int i = 1; i < rownum; i++) {
			Row row = sheet.getRow(i);
			mapData.put(row.getCell(1).toString(), row.getCell(2).toString());
		}
		workbook.close();
		file.close();
		return mapData;
	}

	public static void addDataToMasterSheet(Map<String, String> mapData) throws IOException {
		FileInputStream file = new FileInputStream(masterSheet);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Home");
		int rownum = sheet.getPhysicalNumberOfRows();
		Row row = sheet.getRow(0);
		Cell cell;
		Cell toCell = row.createCell(row.getLastCellNum());
		toCell.setCellValue(strDate);
		for (int i = 1; i < rownum; i++) {
			row = sheet.getRow(i);
			cell = row.getCell(0);
			toCell = row.createCell(row.getLastCellNum());
			if (mapData.get(cell.toString()) != null) {
				toCell.setCellValue(mapData.get(cell.toString()));
			}
		}
		FileOutputStream out = new FileOutputStream(masterSheet);
		workbook.write(out);
		out.close();
		workbook.close();
		file.close();
	}

	public static void deleteFile() {
		File file = new File("C:\\Users\\Dell\\Downloads\\CMVOLT.csv");
		file.delete();

		file = new File("C:\\Users\\Dell\\Downloads\\CMVOLT.xlsx");
		file.delete();
	}

	public static void teardown() {
		driver.close();
	}
}
