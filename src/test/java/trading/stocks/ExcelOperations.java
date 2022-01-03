package trading.stocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {

	public static void addTopTenStockData(String sheetName, Map<String, Object[]> mapData) throws IOException {
		// Get the File location
		FileInputStream file = new FileInputStream("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\TopGainLoss.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rownum = sheet.getPhysicalNumberOfRows();
		// Iterate over data and write to sheet
		Set<String> keyset = mapData.keySet();
//        int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = mapData.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		FileOutputStream out = new FileOutputStream("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\TopGainLoss.xlsx");
		workbook.write(out);
		out.close();
		workbook.close();
		file.close();
	}

	public static void addAllStockData(String sheetName, TreeMap<String, String> mapData, String stockDate)
			throws IOException, NumberFormatException {
		FileInputStream file = new FileInputStream("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\StockProgress.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rownum = sheet.getPhysicalNumberOfRows();
//		int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
		if (rownum < 5) {
			Set<String> keyset = mapData.keySet();
//	        int rownum = 0;
			for (String key : keyset) {
				Row row = sheet.createRow(rownum++);
				String value = mapData.get(key);
				int cellnum = 0;
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue(key);
				cell = row.createCell(cellnum++);
				cell.setCellValue(value);
			}
		} else {
			CellStyle style;
			DataFormat format = workbook.createDataFormat();
			style = workbook.createCellStyle();
			style.setDataFormat(format.getFormat("0.00"));
			CellStyle styleRed = workbook.createCellStyle();
			styleRed.setFillForegroundColor(IndexedColors.RED.getIndex());
			styleRed.setFillPattern(CellStyle.SOLID_FOREGROUND);
			CellStyle styleGreen = workbook.createCellStyle();
			styleGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			styleGreen.setFillPattern(CellStyle.SOLID_FOREGROUND);

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
					cell.setCellStyle(styleGreen);
				} else {
					cell.setCellStyle(styleRed);
				}
			}
		}
		FileOutputStream out = new FileOutputStream("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\StockProgress.xlsx");
		workbook.write(out);
		out.close();
		workbook.close();
		file.close();
	}

	public void createExcel() {
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Employee Data");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "ID", "NAME", "LASTNAME" });
		data.put("2", new Object[] { 1, "Amit", "Shukla" });
		data.put("3", new Object[] { 2, "Lokesh", "Gupta" });
		data.put("4", new Object[] { 3, "John", "Adwards" });
		data.put("5", new Object[] { 4, "Brian", "Schultz" });

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateCellColorInExcel(String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(new File("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\StockProgress.xlsx"));

		// Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		XSSFSheet sheet = workbook.getSheet(sheetName);
		CellStyle styleRed = workbook.createCellStyle();
		styleRed.setFillForegroundColor(IndexedColors.RED.getIndex());
		styleRed.setFillPattern(CellStyle.SOLID_FOREGROUND);
		CellStyle styleGreen = workbook.createCellStyle();
		styleGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		styleGreen.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		String value;
		Row row;
		Cell cell;
		DataFormatter formatter;
		for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			for (int colIndex = 1; colIndex <= 3; colIndex++) {
				cell = row.getCell(colIndex);
				formatter = new DataFormatter();
				value = formatter.formatCellValue(sheet.getRow(rowIndex).getCell(colIndex));
				if (Double.parseDouble(value) >= 0.0) {
					cell.setCellStyle(styleGreen);
				} else if (Double.parseDouble(value) < 0.0) {
					cell.setCellStyle(styleRed);
				} else {

				}
			}
		}
//		while (rowIterator.hasNext()) {
//			Row row = rowIterator.next();
//			// For each row, iterate through all the columns
//			Iterator<Cell> cellIterator = row.cellIterator();
//
//			while (cellIterator.hasNext()) {
//				Cell cell = cellIterator.next();
//
//				value = cell.getStringCellValue();
//
//				if (Double.parseDouble(value) >= 0.0) {
//					cell.setCellStyle(styleGreen);
//				} else if (Double.parseDouble(value) < 0.0) {
//					cell.setCellStyle(styleRed);
//				} else {
//
//				}
//				// Check the cell type and format accordingly
////					switch (cell.getCellType()) {
////					case Cell.CELL_TYPE_NUMERIC:
////						System.out.print(cell.getNumericCellValue() + "t");
////						break;
////					case Cell.CELL_TYPE_STRING:
////						System.out.print(cell.getStringCellValue() + "t");
////						break;
////					}
//			}
//		}
		FileOutputStream out = new FileOutputStream("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\StockProgress.xlsx");
		workbook.write(out);
		out.close();
		workbook.close();
		file.close();
	}

	public static void addAllStockData2(String sheetName, TreeMap<String, String> mapData, String stockDate)
			throws IOException, NumberFormatException {
		FileInputStream file = new FileInputStream("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\StockProgress.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rownum = sheet.getPhysicalNumberOfRows();
		if (rownum < 5) {
			Set<String> keyset = mapData.keySet();
//	        int rownum = 0;
			for (String key : keyset) {
				Row row = sheet.createRow(rownum++);
				String value = mapData.get(key);
				int cellnum = 0;
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue(key);
				cell = row.createCell(cellnum++);
				cell.setCellValue(value);
			}
		} else {
			CellStyle style;
			DataFormat format = workbook.createDataFormat();
			style = workbook.createCellStyle();
			style.setDataFormat(format.getFormat("0.00"));
			CellStyle styleRed = workbook.createCellStyle();
			styleRed.setFillForegroundColor(IndexedColors.RED.getIndex());
			styleRed.setFillPattern(CellStyle.SOLID_FOREGROUND);
			CellStyle styleGreen = workbook.createCellStyle();
			styleGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			styleGreen.setFillPattern(CellStyle.SOLID_FOREGROUND);
			Row row = sheet.getRow(0);
			String cellValueMaybeNull;
			List<String> companiesInExcel;
			String gainlossToAdd;
			Cell cell = row.createCell(row.getLastCellNum());
			cell.setCellValue(stockDate);
			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				row = sheet.getRow(rowIndex);
				if (row != null) {
					cell = row.getCell(0);
					if (cell != null) {
						// Found column and there is value in the cell.
						cellValueMaybeNull = cell.getStringCellValue();
						// Do something with the cellValueMaybeNull here ...
						// break; ???
						gainlossToAdd = mapData.get(cellValueMaybeNull);
						if(gainlossToAdd == null) {
							continue;
						}
						cell = row.createCell(row.getLastCellNum());
						cell.setCellValue(gainlossToAdd);
						if (Double.parseDouble(gainlossToAdd) >= 0.0) {
							cell.setCellStyle(styleGreen);
						} else {
							cell.setCellStyle(styleRed);
						}

					}
				}
			}
//
//			Set<String> keyset = mapData.keySet();
//			Iterator<Row> iterator = sheet.iterator();
//			Row currentRow = iterator.next();
//			Cell cell = currentRow.createCell(currentRow.getLastCellNum());
//			cell.setCellValue(stockDate);
//			for (String key : keyset) {
//				currentRow = iterator.next();
//				String value = mapData.get(key);
//				cell = currentRow.createCell(currentRow.getLastCellNum());
//				cell.setCellValue(value);
//				if (Double.parseDouble(value) >= 0.0) {
//					cell.setCellStyle(styleGreen);
//				} else {
//					cell.setCellStyle(styleRed);
//				}
//			}
		}
		FileOutputStream out = new FileOutputStream("C:\\Users\\Dell\\eclipse-workspace\\stocks\\resources\\StockProgress.xlsx");
		workbook.write(out);
		out.close();
		workbook.close();
		file.close();
	}

}
