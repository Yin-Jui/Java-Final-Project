import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EndFrame implements ActionListener{

	private Player p;
	
	JButton endbtn = new JButton();
	JButton restartbtn = new JButton();
	JFrame endframe = new JFrame();
	JLabel label = new JLabel();
	JLabel sl = new JLabel();
	

	
	public EndFrame(){
		endframe.setTitle("Game Over");
		endframe.setSize(500, 500);
		
		endbtn.addActionListener(this);
		endbtn.setText("End");
		
		restartbtn.addActionListener(this);
		restartbtn.setText("Restart");
		
		Font f = new Font("Arial", Font.BOLD,50);
		endbtn.setFont(f);
		restartbtn.setFont(f);
		
		Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 30);
		sl.setText("     Score: " + Integer.toString(Player.score));
		sl.setFont(font);
		sl.setForeground(Color.BLUE);
		
		endframe.setLayout(new BorderLayout());
		endframe.add(sl, BorderLayout.NORTH);
		
		endbtn.setPreferredSize(new Dimension(100,100));
		endframe.add(endbtn, BorderLayout.SOUTH);
		
		restartbtn.setPreferredSize(new Dimension(100,100));
		endframe.add(restartbtn, BorderLayout.CENTER);
		
		endframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endframe.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==endbtn) {
			System.exit(0);
		}
		else if(e.getSource()==restartbtn){
			endframe.dispose();
			new Main();
		}
		
	}
}
