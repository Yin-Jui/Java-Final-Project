import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.JPanel;

public class PaintPanel extends JPanel{
	
	private final ArrayList<Point> points = new ArrayList<>();
	
	public PaintPanel() {
		addMouseMotionListener(new MouseMotionAdapter()
				{
			public void mouseDragged(MouseEvent event)
			{
				points.add(event.getPoint());
				repaint();
			}
				}
		);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for(Point point : points)
			g.fillOval(point.x, point.y, 4, 4);
	}

}
