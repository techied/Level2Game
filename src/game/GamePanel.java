package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener {
	
	private Timer timer;
	private ObjectManager manager;
	private Ball ball;
	private ArrayList<Wall> walls;
	private ArrayList<Level> levels;
	private boolean left, right;
	private int level = 0;
	private int numberOfLevels = 4;
	
	public GamePanel() {
		timer = new Timer(1000/60, this);
		manager = new ObjectManager();
		ball = new Ball(0, 0);
		walls = new ArrayList<Wall>();
		makeLevels();
		makeNextLevel();
	}
	
	private void makeLevels() {
		levels = new ArrayList<Level>();
		for (int i = 1; i <= numberOfLevels; i++) {
			Level level = new Level("levels/level" + Integer.toString(i) + ".txt");
			levels.add(level);
		}
		Collections.sort(levels);
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
	
	private void makeNextLevel() {
		try {
			manager.add(ball);
			ball.setStartX(levels.get(level).getStartX());
			ball.setStartY(levels.get(level).getStartY());
			walls = levels.get(level).getWalls();
			initWalls();
			level++;
		}
		catch (Exception e) {
			System.out.println("You beat the game!");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
		if (ball.hasWon()) {
			walls.clear();
			manager.clear();
			makeNextLevel();
		}
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