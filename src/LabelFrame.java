import java.awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.* ;

public class LabelFrame  extends JFrame{
	
	private final JButton start; 
	private final JButton paint;
	private final JButton quit;


	private Font font ;
	
	public LabelFrame()
	{	
		super("Fansy ultra viola");
		setLayout(new BorderLayout());
		
		start = new JButton("START GAME");
		paint = new JButton("PAINT");
		quit = new JButton("QUIT");
		Font f = new Font("Arial", Font.BOLD,50);
	    start.setFont(f);
		paint.setFont(f);
		quit.setFont(f);
		
		start.setBackground(Color.BLUE);
		paint.setBackground(Color.WHITE);
		quit.setBackground(Color.RED);
		
		start.setPreferredSize(new Dimension(200, 150));
		add(start, BorderLayout.NORTH);
		paint.setPreferredSize(new Dimension(200, 150));
		add(paint, BorderLayout.CENTER);
		quit.setPreferredSize(new Dimension(200, 150));
		add(quit, BorderLayout.SOUTH);
		
		ButtonHandler handler = new ButtonHandler();

		start.addActionListener(handler);
		paint.addActionListener(handler);
		quit.addActionListener(handler);
		
	}
	
	private class ButtonHandler implements ActionListener  // inner class for button event handling
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==start){
				dispose();
				new Main();
//				System.out.println("in start");
			}
			else if(e.getSource()==paint){
				dispose();
				System.out.println("in paint");
				
				JFrame application = new JFrame("paint");
				PaintPanel paintpanel = new PaintPanel();
				application.add(paintpanel,BorderLayout.CENTER);
				application.add(new Label("Draw your creation"), BorderLayout.NORTH);
			
				application.setSize(400, 400);
				application.setVisible(true);
				application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			else if(e.getSource()==quit){
				System.exit(0);
			}
		}
	}
}
