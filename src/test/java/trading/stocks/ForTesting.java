package trading.stocks;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ForTesting extends ExcelOperations {

	static Set<String> symbol;
	static Map<String, List<String>> mapData = new HashMap<String, List<String>>();
	public static void main(String[] args) throws IOException {
		
		symbol = getSymbolsFromSheet();
		mapData = getRecordsFromSheet(symbol);
		updateDataToExcel(mapData);
		

	}

}
