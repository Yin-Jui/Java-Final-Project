import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Character {
	private BufferedImage image;
	private String character = "player.gif";
	private String pic;
	 
    private int x;
    private int y;
    
    public Character() {
    	this(0,300);
    }
    
    public Character(int x, int y) {
        this.x = x;
        this.y = y;
 
        // Try to open the image file
        try {
        	pic = character;
            image = ImageIO.read(getClass().getResource(pic));
//            System.out.println("x = " + getX() + ", y = " + getY());
        }
        catch (Exception e) {
        	System.out.println(e);
        }
    }
    
    public void draw(Graphics window) {
   	 
        // Draw the image onto the Graphics reference
    	window.drawImage(image, getX(), getY(), image.getWidth(), image.getHeight(), null);
    }
    
    public void jump() {
    	int u = 80, t = 1, g = 10;
    	for (int i = 0; i < 100; i++) {
    		y = u * t - (g / 2) * t * t;
    		t++;
    	}
    }
    
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
    	this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getImageWidth() {
        return image.getWidth()/5;
    }
    public int getImageHeight() {
    	return image.getHeight()/5;
    }
}
