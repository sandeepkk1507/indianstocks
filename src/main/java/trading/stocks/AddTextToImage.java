package trading.stocks;

import java.awt.AlphaComposite;
import java.util.Comparator;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

public class AddTextToImage {
	public static Color white = Color.WHITE;
	public static Color green = Color.GREEN;
	public static Color colorToShow;
	public static Color red = Color.RED;
	static String strDate;
	public static Date date;
	static SimpleDateFormat formatter;
	static String fileName = "C:\\Users\\Dell\\Pictures\\Automation\\";
	static File input;
//	= new File("C:\\Users\\Dell\\Pictures\\Automation\\black.jpg");
	static File output;
static //	= new File("C:\\Users\\Dell\\Pictures\\Automation\\addNewblack.jpg");
	HashMap<String, String> mapData = new HashMap<String, String>();

//	public static void main(String[] args) throws IOException {
//		date = new Date();
//		formatter = new SimpleDateFormat("dd/MMM/yyyy");
//		strDate = formatter.format(date);
//		input = new File(fileName+"black.jpg");
//		output = new File(fileName+"\\gain\\addNewblack.jpg");
//		mapData = new HashMap<String, String>();
//		mapData.put("TCS", "4.0");
//		mapData.put("HCL", "5.0");
//		mapData.put("WIPRO", "1.0");
//		mapData.put("INFY", "3.0");
//		mapData.put("PERSITENT", "2.0");
//		
//		addTextInImage(sortMapValueDesc(convertStringToDoubleMapValue(mapData)), "jpg", input, output);
//	}
	public static void putDataToImage(Map<String, String> mapData, String GainOrLossType) throws IOException {
		date = new Date();
		formatter = new SimpleDateFormat("dd/MMM/yyyy");
		strDate = formatter.format(date);
		input = new File(fileName+"newblack.jpg");
		if(GainOrLossType.contains("GAIN")) {
			output = new File(fileName+"\\Gain\\"+GainOrLossType+".jpg");
			colorToShow = green;
			addTextInImage(sortMapValueDesc(convertStringToDoubleMapValue(mapData)), "jpg", input,output, GainOrLossType);
			
		} else {
			output = new File(fileName+"\\Loss\\"+GainOrLossType+".jpg");
			colorToShow = red;
			addTextInImage(sortMapValueAsce(convertStringToDoubleMapValue(mapData)), "jpg", input,output, GainOrLossType);
		}
		
		
	}

	public static void addTextInImage(HashMap<String, Double> mapData, String type, File source, File destination, String GainOrLossType) throws IOException {
		
		BufferedImage image = ImageIO.read(input);
		int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
		
		BufferedImage bold = new BufferedImage(image.getWidth(), image.getHeight(), imageType);
		
		Graphics2D w = (Graphics2D) bold.getGraphics();
		
		w.drawImage(image, 1, 2, null);
		AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
		
		w.setComposite(alpha);
		w.setColor(Color.WHITE);
		w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 44));
		
		FontMetrics fMetrics = w.getFontMetrics();
		Rectangle2D rect; 
//		rect = fMetrics.getStringBounds(text, w);
		int centerX = 50;
		int centerY = 200;
		if(colorToShow.equals(green)) {
			w.drawString("Top 5 "+ GainOrLossType.split("-")[0]+" Gainers on - "+strDate, centerX, centerY);
		} else if(colorToShow.equals(red)) {
			w.drawString("Top 5 "+ GainOrLossType.split("-")[0]+" Lossers on - "+strDate, centerX, centerY);
		}
		Set<String> keyset = mapData.keySet();
		w.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 44));
		w.drawString("-------------------------------------------------------------------", centerX, centerY+50);
		for (String key : keyset) {
			centerY+=100;
			centerX = 100;
			rect = fMetrics.getStringBounds(key, w);
			w.setColor(white);
			centerY+=50;
			w.drawString(key, centerX, centerY);
			centerX = 500;
			w.drawString("----->", centerX, centerY);
			w.setColor(colorToShow);
			centerX = 650;
			w.drawString(mapData.get(key)+"%".toString(), centerX, centerY);
		}
		ImageIO.write(bold, type, output);
		
		w.dispose();
		
	}
	public static Map<String, Double> convertStringToDoubleMapValue(Map<String, String> mapData) {
		Map<String, Double> newMap = new HashMap<String, Double>();
		Set<String> s = mapData.keySet();
		for (String key : s) {
			newMap.put(key, Double.parseDouble(mapData.get(key)));
		}
		
		return newMap;
	}
	
	public static LinkedHashMap<String, Double> sortMapValueDesc(Map<String, Double> unSortedMap) {
        
		//LinkedHashMap preserve the ordering of elements in which they are inserted
		LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
		 
		//Use Comparator.reverseOrder() for reverse ordering
		unSortedMap.entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
		    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		 
		return reverseSortedMap;
	}
	
public static LinkedHashMap<String, Double> sortMapValueAsce(Map<String, Double> unSortedMap) {
        
		//LinkedHashMap preserve the ordering of elements in which they are inserted
		LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
		 
		//Use Comparator.reverseOrder() for reverse ordering
		unSortedMap.entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder())) 
		    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		return reverseSortedMap;
	}

}
