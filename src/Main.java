import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageProducer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame{
	
	private static AudioPlayer bgMusic;
	
	public Main() {
		
        super("Run Run Run!!");
        setSize(1300, 720);
        setResizable(false);
        ScrollingBackground background = new ScrollingBackground();
        add(background.health);
		add(background.mana);
		add(background.score);
		background.increasescore();
		background.increasemana();
		background.increasetime();
		background.speedup();
        ((Component)background).setFocusable(true);
        getContentPane().add(background);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

	public static void main(String[] args) {

		int check;
		
		LabelFrame button = new LabelFrame();
		button.setDefaultCloseOperation(EXIT_ON_CLOSE);
		button.setSize(500,500);
		button.setResizable(false);
		button.getContentPane().setBackground(Color.white);
		button.setVisible(true);
	
		bgMusic = new AudioPlayer("/Music/bg.mp3");
		bgMusic.repeating();
	
	}

}
