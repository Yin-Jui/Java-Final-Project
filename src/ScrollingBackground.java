import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class ScrollingBackground extends Canvas implements Runnable,
		KeyListener {

	private Background backOne;
	private Background backTwo;
	int y = 0;
	int yy = 0;
	int lasty = 0;
	private Obstacle obstacle1;
	private Obstacle obstacle2;
	private Obstacle obstacle3;
	private Obstacle obstacle4;
	private Obstacle obstacle5;
	private List<Obstacle> obstacles;
	private Rectangle hp = new Rectangle(100, 100, 200, 20);

	private Player player;
	
	private AudioPlayer jumpMusic,sprintMusic,bounceMusic,swordMusic;

	private BufferedImage back;

	public int u = 25;

	public JLabel health = new JLabel();
	public JLabel mana = new JLabel();
	public JLabel score = new JLabel();

	public ScrollingBackground() {
		backOne = new Background();
		backTwo = new Background(backOne.getImageWidth(), 0);

		obstacle1 = new Obstacle();
		obstacle2 = new Obstacle();
		obstacle3 = new Obstacle();
		obstacle4 = new Obstacle();
		obstacle5 = new Obstacle();
		initObstacles();

		player = new Player();

		new Thread(this).start();
		setVisible(true);
		addKeyListener(this);
	}

	public void initObstacles() {
		obstacles = new ArrayList<>();
		obstacles.add(obstacle1);
		obstacles.add(obstacle2);
		obstacles.add(obstacle3);
		obstacles.add(obstacle4);
		obstacles.add(obstacle5);
	}

	public void sethealth() {

		health.setText("HP: " + Integer.toString(player.hp));
		health.setSize(180, 25);

		Font font = new Font(Font.DIALOG_INPUT, Font.ITALIC, 25);
		health.setFont(font);
		health.setForeground(Color.RED);
		health.setLocation(50, 50);

	}

	public void setmana() {

		mana.setText("MANA: " + Integer.toString(player.mana));
		mana.setSize(180, 25);

		Font font = new Font(Font.DIALOG_INPUT, Font.ITALIC, 25);
		mana.setFont(font);
		mana.setForeground(Color.BLUE);
		mana.setLocation(50, 90);

	}

	public void setscore() {

		score.setText("SCORE " + Integer.toString(player.score));
		score.setSize(180, 25);

		Font font = new Font(Font.DIALOG_INPUT, Font.ITALIC, 25);
		score.setFont(font);
		score.setForeground(Color.GREEN);
		score.setLocation(50, 130);

	}

	public void increasemana() {

		Timer manamanage = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (player.mana < 100) {
					player.mana += 1;
				}
			}
		};
		manamanage.schedule(task, 1000, 1000);
	}

	public void increasescore() {

		Timer scoremanage = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				player.score += 10;
			}

		};

		scoremanage.schedule(task, 1000, 1000);
	}
	
	public void increasetime() {

		Timer timeManage = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				player.time += 1;
			}

		};

		timeManage.schedule(task, 1000, 1000);
	}

	public void speedup() {
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (u > 5)
					u -= 5;
			}

		};

		timer.schedule(task, 10000, 10000);
	}

	private void updateObstacles() {

		if (obstacles.isEmpty()) {
			obstacle1 = new Obstacle((int) (Math.random() * 500) + 1300, (int) (Math.random() * 247) + 473 -  obstacle1.getImageHeight() );
			obstacle2 = new Obstacle((int) (Math.random() * 500) + 1300, (int) (Math.random() * 247) + 473 -  obstacle2.getImageHeight() );
			obstacle3 = new Obstacle((int) (Math.random() * 500) + 1300, (int) (Math.random() * 247) + 473 -  obstacle2.getImageHeight() );
			obstacle4 = new Obstacle((int) (Math.random() * 500) + 1300, (int) (Math.random() * 247) + 473 -  obstacle2.getImageHeight() );
			obstacle5 = new Obstacle((int) (Math.random() * 500) + 1300, (int) (Math.random() * 247) + 473 -  obstacle2.getImageHeight() );
			obstacles.add(obstacle1);
			obstacles.add(obstacle2);
			obstacles.add(obstacle3);
			obstacles.add(obstacle4);
			obstacles.add(obstacle5);
			return;
		}

		for (int i = 0; i < obstacles.size(); i++) {

			Obstacle ob = obstacles.get(i);

			if (ob.isVisible()) {

			} else {
				obstacles.remove(i);
			}
		}
	}

	public void keyPressed(KeyEvent k) {

		if (k.getKeyCode() == KeyEvent.VK_J) {
			player.jumpb = false;
			player.jump = true;
			player.rotate = false;
			player.shoot = false;
			player.up = false;
			player.down = false;
			player.enlarge = false;
			y = player.getBounds().y;
			jumpMusic = new AudioPlayer("/Music/jump.mp3");
			jumpMusic.play();
		}
		if (k.getKeyCode() == KeyEvent.VK_SPACE) {
			player.jumpb = true;
			jumpMusic = new AudioPlayer("/Music/jump.mp3");
			jumpMusic.play();
		}
		if (k.getKeyCode() == KeyEvent.VK_W)
			player.up = true;
		if (k.getKeyCode() == KeyEvent.VK_S)
			player.down = true;
		if (k.getKeyCode() == KeyEvent.VK_A)
			player.left = true;
		if (k.getKeyCode() == KeyEvent.VK_D)
			player.right = true;
		if (k.getKeyCode() == KeyEvent.VK_R) {
			if (player.mana >= 50) {
				u = 75;
				player.freezing = true;
				player.mana -= 50;
			}
		}
		if (k.getKeyCode() == KeyEvent.VK_1) {
			if (player.mana >= 10) {
				player.rotate = true;
				player.mana -= 10;
				Timer timer = new Timer();
				TimerTask setfal = new TimerTask() {

					@Override
					public void run() {
						setfalse();
					}
				};

				timer.schedule(setfal, 500);
			}

		}
		if (k.getKeyCode() == KeyEvent.VK_2) {
			if (player.mana >= 20) {
				player.enlarge = true;
				player.mana -= 20;

				Timer timer = new Timer();
				TimerTask setfal = new TimerTask() {

					@Override
					public void run() {
						setfalse();
					}
				};

				timer.schedule(setfal, 500);
			}

		}
		if (k.getKeyCode() == KeyEvent.VK_3) {
			if (player.mana >= 30) {
				player.shoot = true;
				player.mana -= 30;

				Timer timer = new Timer();
				TimerTask setfal = new TimerTask() {

					@Override
					public void run() {
						setfalse();
					}
				};

				timer.schedule(setfal, 500);
			}

		}

	}

	public void keyReleased(KeyEvent k) {

		if (k.getKeyCode() == KeyEvent.VK_W)
			player.up = false;
		if (k.getKeyCode() == KeyEvent.VK_S)
			player.down = false;
		if (k.getKeyCode() == KeyEvent.VK_A)
			player.left = false;
		if (k.getKeyCode() == KeyEvent.VK_D)
			player.right = false;
		if (k.getKeyCode() == KeyEvent.VK_1)
			if (player.mana >= 10) {
				player.rotate = true;
			}
		if (k.getKeyCode() == KeyEvent.VK_2) {
			if (player.mana >= 20) {
				player.right = true;
				player.enlarge = true;
			}
		}
		if (k.getKeyCode() == KeyEvent.VK_3)
			if (player.mana >= 30) {
				player.shoot = true;
			}

	}

	public void keyTyped(KeyEvent k) {

	}

	@Override
	public void run() {

		while (true) {

			if (player.jump) {
				player.nowImage = player.jumping;
//				yy = player.y;
				player.y =  player.u * player.t - (player.g / 2) * player.t * player.t;
				player.t++;

				lasty = player.getBounds().y;
				if(player.x + player.jumping.getWidth(null) >= 1300)
				{
					player.x = 600 ;
				}
				player.x += 25;
				
			}
			if (player.up && player.y + player.sitting.getHeight(null) / 2 >= 430) {
				player.y -= 10;
			}
			if (player.rotate) {
				sprintMusic = new AudioPlayer("/Music/sprint.mp3");
				sprintMusic.play();
				player.x += 20;
				if(player.x + player.transform.getWidth(null) >= 1300) {
					player.x = 600 ;
				}

			}
			if (player.enlarge) {
				bounceMusic = new AudioPlayer("/Music/transform.mp3");
				bounceMusic.play();
				player.nowImage = player.transform;
//				player.y = player.u * player.t - (player.g) * player.t * player.t * player.t;
//				player.t++;
				if(player.x + player.sitting.getWidth(null) >= 1300) {
					player.x = 600 ;
				}
				player.hp += 1;
				player.x += 10;				

			}
			if (player.down && player.y + player.sitting.getHeight(null) <= 690) {
				player.y += 10;
			}
			if (player.left && player.x >= 0) {
				player.x -= 10;
			}
			if (player.right && player.x + player.sitting.getWidth(null) <= 1300) {
				player.x += 10;
			}
			if (player.shoot) {
				swordMusic = new AudioPlayer("/Music/sword.mp3");
				swordMusic.play();
				player.nowImage = player.sword;
				if(player.x + player.sword.getWidth(null) >= 1300) {
					player.x = 600 ;
				}
				// bullet = new Bullet(x, y);
				player.x += 25;
			}
			if (player.jumpb) {
				player.y = player.u * player.t - (player.g / 2) * player.t * player.t;
				player.t++;
				player.x -= 10;
				if(player.x + player.jumping.getWidth(null) >= 1000) {
					player.x = 600 ;
				}

			}
			if (player.t > 1 && player.y <= 0) {

				player.jump = false;
				player.jumpb = false;
				player.t = 1;
				player.y = y;

			}

			try {
				Thread.sleep(u);
			} catch (InterruptedException e) {

			}
			freeze();
			sethealth();
			setmana();
			setscore();
			checkCollisions();
			updateObstacles();
			repaint();
		}

	}

	@Override
	public void update(Graphics window) {
		paint(window);
	}

	public void paint(Graphics window) {
		Graphics2D twoD = (Graphics2D) window;

		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));
		
		// Create a buffer to draw to
		Graphics buffer = back.createGraphics();
		
		// Put the two copies of the background image onto the buffer
		backOne.draw(buffer);
		backTwo.draw(buffer);
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(buffer);
		}
		
		player.paint(buffer);
		
        // Draw the image onto the window
		twoD.drawImage(back, null, 0, 0);

		if (player.jump) {
			window.drawImage(player.jumping, player.x, player.frameheight - player.y - 130 + player.getHeight() / 2, null);
		} else if (player.jumpb) {
			window.drawImage(player.jumping, player.x, player.frameheight
					- player.y - 130, null);
		} else if (player.shoot) {
			window.drawImage(player.sword, player.x, player.y, null);

		} else if (player.rotate) {
			// g.drawRect(bullet.get_X(),bullet.get_Y() , 50, 50);

			AffineTransform at = AffineTransform.getTranslateInstance(player.x, player.y);
			at.rotate(Math.toRadians(player.degree = player.degree + -650),
					player.transform.getWidth(null) / 2,
					player.transform.getHeight(null) / 2);
			Graphics2D g2 = (Graphics2D) window;
			g2.drawImage(player.transform, at, null);

		} else if (player.enlarge) {
			AffineTransform at = AffineTransform.getTranslateInstance(player.x,player.y-130);
			at.scale(2, 2);
			Graphics2D g2 = (Graphics2D) window;
			g2.drawImage(player.sitting, at, null);
//			window.drawImage(player.transform, player.x, player.y, null);

		} else {

			window.drawImage(player.sitting, player.x, player.y, null);
		}

	}

	public TimerTask setfalse() {

		player.shoot = false;
		player.enlarge = false;
		player.rotate = false;
		player.right = false;
		player.left = false;
		player.jump = false;
		player.jumpb = false;

		return null;
	}

	public void freeze() {
		if (player.freezing) {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					player.freezing = false;
					u = 20;
				}
			};

			timer.schedule(task, 5000);
		}
	}

	public void checkCollisions() {

		Rectangle r3 = player.getBounds();

		for (int i = 0; i < obstacles.size(); i++) {

			Rectangle r1 = obstacles.get(i).getBounds();

			if (r3.intersects(r1)) {

				player.setVisible(false);
				obstacles.get(i).setVisible(false);
				if (!player.shoot&&!player.rotate) {
					player.hp -= 1;
				}
				System.out.println("p: " + player.hp);
				if (player.hp == 0) {
						new EndFrame();
				}

			}
		}
	}

}