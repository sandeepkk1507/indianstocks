package trading.stocks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NSEDailyVolatility {
	static WebDriver driver;
	static String url = "https://archives.nseindia.com/archives/nsccl/volt/CMVOLT_17122021.CSV";
	static TreeMap<String, String> nseMapData = new TreeMap<String, String>();
	static TreeMap<String, String> nseMapDataFromExcel = new TreeMap<String, String>();

	public static void main(String[] args) throws IOException {
		//https://archives.nseindia.com/archives/nsccl/volt/CMVOLT_18112021.CSV
		
		initialize();
//		csvToXLSX();
//		nseMapDataFromExcel = getDataFromNSEDailySheet();
//		printMapData(nseMapDataFromExcel);

	}
	
	public static void initialize() {
		System.setProperty("webdriver.chrome.driver", "/Users/Dell/Downloads/chromedriver_win32_96/chromedriver.exe");
//		// for ghost testing
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless", "--window-size=1920,1200");
//		driver = new ChromeDriver(options);
		// for exixting window open
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
//		driver = new ChromeDriver(options);
		// normal brower
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	public static TreeMap<String, String> getDataFromNSEDailySheet() throws IOException {
		FileInputStream sourceFile = new FileInputStream("C:\\Users\\Dell\\Documents\\Stocks\\NSEDailyData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(sourceFile);
		XSSFSheet sheet = workbook.getSheet("sheet1");
		int rownum = sheet.getPhysicalNumberOfRows();
		Row row;
		Cell cell;
		String companyName;
		String todaysValue;
		
		
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (row != null) {
				cell = row.getCell(1);
				if (cell != null) {
					// Found column and there is value in the cell.
					companyName = cell.getStringCellValue();
					todaysValue = row.getCell(2).getStringCellValue();

					nseMapData.put(companyName, todaysValue);
				}
			}
		}
		return nseMapData;
	}
	
	public static void printMapData(TreeMap<String, String> data) {
		Set<String> keys = data.keySet();
		for(String key : keys) {
			System.out.println("Key is "+key+" valus is "+data.get(key));
		}
	}
	
	public static void csvToXLSX() {
	    try {
	        String csvFileAddress = "C:\\Users\\Dell\\Downloads\\CMVOLT_16112021.csv"; //csv file address
	        String xlsxFileAddress = "C:\\Users\\Dell\\Documents\\Stocks\\NSEDailyData.xlsx"; //xlsx file address
	        XSSFWorkbook workBook = new XSSFWorkbook();
	        XSSFSheet sheet = workBook.createSheet("sheet1");
	        String currentLine=null;
	        int RowNum=0;
	        BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
	        while ((currentLine = br.readLine()) != null) {
	            String str[] = currentLine.split(",");
	            RowNum++;
	            XSSFRow currentRow=sheet.createRow(RowNum);
	            for(int i=0;i<str.length;i++){
	                currentRow.createCell(i).setCellValue(str[i]);
	            }
	        }

	        FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFileAddress);
	        workBook.write(fileOutputStream);
	        fileOutputStream.close();
	        System.out.println("Done");
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage()+"Exception in try");
	    }
	}
	
	public static void addDataToExcel(TreeMap<String, String> mapData) throws IOException {
		Date stockDate = new Date();
		FileInputStream file = new FileInputStream("C:\\Users\\Dell\\Documents\\Stocks\\NSEDailyDataTracker.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("");
		int rownum = sheet.getPhysicalNumberOfRows();
		
		
		Set<String> keyset = mapData.keySet();
		Iterator<Row> iterator = sheet.iterator();
		Row currentRow = iterator.next();
		Cell cell = currentRow.createCell(currentRow.getLastCellNum());
		cell.setCellValue(stockDate);
		for (String key : keyset) {
			currentRow = iterator.next();
			String value = mapData.get(key);
			cell = currentRow.createCell(currentRow.getLastCellNum());
			cell.setCellValue(value);
			if (Double.parseDouble(value) >= 0.0) {
//				cell.setCellStyle(styleGreen);
			} else {
//				cell.setCellStyle(styleRed);
			}
		}
	FileOutputStream out = new FileOutputStream("C:\\Users\\Dell\\Documents\\Stocks\\StockProgress.xlsx");
	workbook.write(out);
	out.close();
	workbook.close();
	file.close();
		
	}

}
