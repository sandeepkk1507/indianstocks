package trading.stocks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;





public class CreateImage {
	public static final int BOLD = 1;

	public static void main(String[] args) throws IOException{
		int width = 400;
	    int height = 250;
	    int circleWidth = height;
	  
	    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2d = bufferedImage.createGraphics();
	    
	  
	    g2d.setColor(Color.blue);
	    g2d.fillRect(0, 0, width, height);
	  
//	    g2d.setColor(Color.green);
//	    g2d.fillOval(150, 0, circleWidth, height);
//	    g2d.setColor(Color.blue);
//	    g2d.fillOval(75, 0, circleWidth, height);
//	    g2d.setColor(Color.yellow);
//	    g2d.fillOval(0, 0, circleWidth, height);
	    
	    g2d.setColor(Color.black);
	    g2d.drawString("Making Images with Java!", 50, 125);
	    
	    g2d.dispose();
	    
	    //png file
	    File file = new File("C:\\Users\\Dell\\Pictures\\Automation\\myimage.png");
	    ImageIO.write(bufferedImage, "png", file);
	    
	    //jpg file
//	    file = new File("C:\\Users\\Dell\\Pictures\\Automation\\myimage.jpg");
//	    ImageIO.write(bufferedImage, "jpg", file);
	}
	
	
}
