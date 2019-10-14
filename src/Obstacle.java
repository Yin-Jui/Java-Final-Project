import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Obstacle {
	private BufferedImage image;
	private String[] obstacles = {"stone1.png", "stone2.png", "stone3.png", "car1.png", "car2.png"};
	private String pic;
	 
    private int x;
    private int y;
    
    private boolean visible = true;
    
    public Obstacle() {
    	this((int)(Math.random() * 500) + 1300,(int)(Math.random() * 247) + 473 - 50);
    }
    
    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
 
        // Try to open the image file
        try {
        	pic = obstacles[(int)(Math.random() * obstacles.length)];
            image = ImageIO.read(getClass().getResource(pic));
            System.out.println("x = " + getX() + ", y = " + getY());
        }
        catch (Exception e) {
        	System.out.println(e);
        }
    }
    
    public void draw(Graphics window) {
    	 
        // Draw the image onto the Graphics reference
    	window.drawImage(image, getX(), getY(), image.getWidth(), image.getHeight(), null);
 
    	move();
//        // Move the x position left for next time
//        this.x -= 5;
 
        // Check to see if the image has gone off stage left
        if (this.x <= -1 * image.getWidth()) {
 
            // If it has, line it back up so that its left edge is
            // lined up to the right side of the other background image
        	
        	try {
        		pic = obstacles[(int)(Math.random() * obstacles.length)];
                image = ImageIO.read(getClass().getResource(pic));
                System.out.println("x = " + getX() + ", y = " + getY());
            }
            catch (Exception e) {
            	System.out.println(e);
            }
        	setX((int)(Math.random() * 500) + 1300);
        	setY((int)(Math.random() * 247) + 473 -  image.getHeight());
        }
 
    }
    
    private void move() {
    	// Move the x position left for next time
        this.x -= 5;
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
        return image.getWidth();
    }
    public int getImageHeight() {
    	return image.getHeight();
    }
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, getImageWidth(), getImageHeight());
    }
}
