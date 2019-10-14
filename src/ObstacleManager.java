import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ObstacleManager extends Canvas implements Runnable {
	
	private Obstacle ob = new Obstacle();

    private BufferedImage back;
	
	@Override
	public void run() {
		try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	@Override
	public void update(Graphics window) {
		paint(window);
	}
	
	public void paint(Graphics window) {
        Graphics2D twoD = (Graphics2D)window;
 
        if (back == null)
            back = (BufferedImage)(createImage(getWidth(), getHeight()));
 
        // Create a buffer to draw to
        Graphics buffer = back.createGraphics();
 
        // Put the two copies of the background image onto the buffer
        ob.draw(buffer);
 
        // Draw the image onto the window
        twoD.drawImage(back, null, 0, 0);
	}
}
