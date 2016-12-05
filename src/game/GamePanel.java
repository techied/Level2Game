package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, ActionListener {

	Timer timer;
	ObjectManager manager;
	Paddle paddle;
	Ball ball;
	
	public GamePanel() {
		timer = new Timer(1000/60, this);
		manager = new ObjectManager();
		paddle = new Paddle();
		ball = new Ball(1, 1, 3, 2);
		manager.add(paddle);
		manager.add(ball);
	}
	
	private void update() {
		manager.update();
	}
	
	public void paintComponent(Graphics g) {
		manager.draw(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		update();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == KeyEvent.VK_A) {
			paddle.inputLeft(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == KeyEvent.VK_D) {
			paddle.inputRight(true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == KeyEvent.VK_A) {
			paddle.inputLeft(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == KeyEvent.VK_D) {
			paddle.inputRight(false);
		}
	}

	public void start() {
		timer.start();
	}
	
}
