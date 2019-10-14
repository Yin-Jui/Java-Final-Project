

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends JPanel{

	public int framewidth = 1300, frameheight = 720;

	public Image sitting = new ImageIcon("player.gif").getImage();
	public Image jumping = new ImageIcon("player.gif").getImage();
	public Image transform = new ImageIcon("images.png").getImage();
//	public Image shooting = new ImageIcon("bullet.png").getImage();
	public Image sword = new ImageIcon("sword.gif").getImage();
	public Image nowImage = sitting;
//	public BufferedImage sitting;
//	public BufferedImage nowImage = sitting;
	
	public int x = 0, y = frameheight - sitting.getHeight(null);

	public boolean jump = false;
	public boolean jumpb = false;
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	public boolean freezing = false;
	
	private JLabel health = new JLabel();

	public int u = 80, t = 1, g = 10;
	public int degree = 0;	
	public int hp = 15;
	public int mana = 100;
	public static int score = 0;
	public static int time;

	public boolean rotate = false;
	public boolean enlarge = false;
	public boolean shoot = false;

	public Bullet bullet;

	public Player() {
		health.setLocation(65,55);
        health.setSize(100,25);
        health.setText("HP: "+Integer.toString(100));
        Font font = new Font(Font.DIALOG_INPUT, Font.ITALIC, 25);
        health.setFont(font);
        health.setForeground(Color.RED);
        add(health);
	}
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, nowImage.getWidth(null) - 5, nowImage.getHeight(null) - 5);
    }

//	public void run() {
//
//		while (true) {
//
//			if (jump) {
//				y = u * t - (g / 2) * t * t;
//				t++;
//				// x += 10;
//				if (left) {
//					x -= 10;
//				}
//				if (right) {
//					x += 10;
//				}
//			}
//			if (up) {
//				y += 10;
//			}
//			if (rotate) {
//				x += 20;
//
//			}
//			if (enlarge) {
//				y = u * t - (g) * t * t * t;
//				t++;
//				x += 10;
//
//			}
//			if (down) {
//				y -= 10;
//			}
//			if (left) {
//				x -= 10;
//			}
//			if (right) {
//				x += 10;
//			}
//			if (shoot) {
//
//			//	bullet = new Bullet(x, y);
//				x += 25;
//			}
//			if (jumpb) {
//				y = u * t - (g / 2) * t * t;
//				t++;
//				x -= 10;
//
//			}
//			if (t > 1 && y <= 50) {
//				jump = false;
//				jumpb = false;
//				t = 1;
//				y = 50;
//			}
//
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//
//			}
//			repaint();
//		}
//	}

//	public void paint(Graphics g) {
//
////		g.fillRect(10, 10, framewidth, frameheight);
//		// g.drawImage(background, 0,0,null);
//
//		if (jump) {
//			g.drawImage(jumping, x, frameheight - y - 130, null);
//		} else if (jumpb) {
//			g.drawImage(jumping, x, frameheight - y - 130, null);
//		} else if (shoot) {
//			g.drawImage(sitting, x, frameheight - y - 130, null);
//			g.drawImage(sword, x + 160, frameheight - y - 130, null);
//			
//			// g.drawImage(sword,bullet.get_X()/2,bullet.get_Y()/2,null);
//		} else if (rotate) {
//			// g.drawRect(bullet.get_X(),bullet.get_Y() , 50, 50);
//
//			AffineTransform at = AffineTransform.getTranslateInstance(x, frameheight - y - 130);
//			at.rotate(Math.toRadians(degree= degree + -650),transform.getWidth(null)/2 , transform.getHeight(null)/2);
//			Graphics2D g2 = (Graphics2D) g;
//			g2.drawImage(transform, at, null);
//
//			// g.drawImage(jumping,bullet.get_X(),bullet.get_Y(),null);
//
//		} else if (enlarge) {
//
//			AffineTransform at = AffineTransform.getTranslateInstance(x,
//					frameheight - y - 130);
//			at.scale(0.5, 0.5);
//			Graphics2D g2 = (Graphics2D) g;
//			g2.drawImage(transform, at, null);
//
//		} else {
//
//			g.drawImage(sitting, x, frameheight - y - 130, null);
//		}
//	}

//	public void keyPressed(KeyEvent k) {
//
//		if (k.getKeyCode() == KeyEvent.VK_J)
//			{
//			jumpb = false ;
//			jump = true;
//			rotate =  false;
//			shoot =false ;
//			up = false ;
//			down = false;
//			enlarge = false ;
//			}
//		if (k.getKeyCode() == KeyEvent.VK_SPACE)
//			jumpb = true;
//		if (k.getKeyCode() == KeyEvent.VK_W)
//			up = true;
//		if (k.getKeyCode() == KeyEvent.VK_S)
//			down = true;
//		if (k.getKeyCode() == KeyEvent.VK_A)
//			left = true;
//		if (k.getKeyCode() == KeyEvent.VK_D)
//			right = true;
//
//		if (k.getKeyCode() == KeyEvent.VK_1) {
//
//			rotate = true;
//			Timer timer = new Timer();
//			TimerTask setfal = new TimerTask() {
//
//				@Override
//				public void run() {
//					setfalse();
//
//				}
//			};
//
//			timer.schedule(setfal, 2000);
//
//		}
//		if (k.getKeyCode() == KeyEvent.VK_2) {
//			enlarge = true;
//
//			Timer timer = new Timer();
//			TimerTask setfal = new TimerTask() {
//
//				@Override
//				public void run() {
//					setfalse();
//
//				}
//			};
//
//			timer.schedule(setfal, 1500);
//
//		}
//		if (k.getKeyCode() == KeyEvent.VK_3) {
//
//			shoot = true;
//
//			Timer timer = new Timer();
//			TimerTask setfal = new TimerTask() {
//
//				@Override
//				public void run() {
//					setfalse();
//
//				}
//			};
//
//			timer.schedule(setfal, 1000);
//
//		}
//
//	}
//
//	public void keyReleased(KeyEvent k) {
//
//		if (k.getKeyCode() == KeyEvent.VK_W)
//			up = false;
//		if (k.getKeyCode() == KeyEvent.VK_S)
//			down = false;
//		if (k.getKeyCode() == KeyEvent.VK_A)
//			left = false;
//		if (k.getKeyCode() == KeyEvent.VK_D)
//			right = false;
//		if (k.getKeyCode() == KeyEvent.VK_1)
//			rotate = true;
//		if (k.getKeyCode() == KeyEvent.VK_2) {
//			right = true;
//			enlarge = true;
//		}
//		if (k.getKeyCode() == KeyEvent.VK_3)
//			shoot = true;
//
//	}

//	public TimerTask setfalse() {
//
//		shoot = false;
//		enlarge = false;
//		rotate = false;
//		right = false;
//		left = false;
//		jump = false;
//		jumpb = false;
//
//		return null;
//	}

//	public void keyTyped(KeyEvent k) {
//
//	}

//	public static void main(String args[]) {
//		new Player();
//		new Main();
//		// new Bullet(0, 0);
//	}

}
