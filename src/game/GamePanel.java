package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
	
	Timer timer;
	ObjectManager manager;
	Ball ball;
	ArrayList<Wall> walls;
	boolean left, right;
	
	public GamePanel() {
		timer = new Timer(1000/60, this);
		manager = new ObjectManager();
		ball = new Ball(25, 100, 0, 0);
		manager.add(ball);
		walls = new ArrayList<Wall>();

		walls.add(new Wall(0, Game.HEIGHT, Game.WIDTH, Ball.height));
		walls.add(new Wall(-Ball.width, 0, Ball.width, Game.WIDTH));
		walls.add(new Wall(Game.WIDTH, 0, Ball.width, Game.HEIGHT));
		walls.add(new Wall(0, -Ball.height, Game.WIDTH, Ball.height));
		walls.add(new Wall(100, 0, 50, 600));
		walls.add(new Wall(300, 200, 50, 600));
		walls.add(new Goal(700, 0, 100, 800));
		initWalls();
	}
	
	private void initWalls() {
		for (Wall wall : walls) {
			manager.add(wall);
			ball.addWall(wall);
		}
	}
	
	private void update() {
		ball.moveX(right, left);
		manager.update();
	}
	
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		manager.draw(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			timer.start();
			ball.setInMotion(true);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			repaint();
			update();
		}
		else if (e.getKeyCode() == KeyEvent.VK_P){
			timer.stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
	}

	public void start() {
		timer.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}