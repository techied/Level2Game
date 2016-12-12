package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
	
	Timer timer;
	ObjectManager manager;
	Ball ball;
	Goal goal;
	Line line;
	
	public GamePanel() {
		timer = new Timer(1000/60, this);
		manager = new ObjectManager();
		ball = new Ball(100, 100, 0, 0);
		goal = new Goal(500, 500, 50, 50);
		line = new Line(45, 90, 40, 95);
		manager.add(goal);
		manager.add(ball);
		manager.add(line);
	}
	
	private void update() {
		manager.update();
	}
	
	public void paintComponent(Graphics g) {
		g.drawRect(0, 0, Game.WIDTH, Game.HEIGHT);
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
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			ball.setInMotion(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

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
