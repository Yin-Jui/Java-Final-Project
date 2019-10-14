

import java.awt.Graphics;
import java.awt.Image;
import java.awt.* ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class Bullet {

	private int x=0 ;
	private int y=0 ; // x and y coordinate of the player

	private Image jumping = new ImageIcon("jumping.png").getImage();
	
	public Bullet(int x, int y) // constructor
	{
		this.x =x ;
		this.y =y;
		
	//	Image sit = new Image(game.get_Sitting());
	}
	
	public void paintb(Graphics bu)
	{

//			bu.drawRect(x, y, 50, 50);
		
	}
	
	public void movement()
	{
		x += 1 ;
	}
	
	public int get_X()
	{
		return x ;
	}

	public int get_Y()
	{
		return y ;
	}
	
	public void set_X(int x)
	{
		this.x = x  ;
	}
	
	public void set_Y(int y)
	{
		this.y = y ;
	}
}
