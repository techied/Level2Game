package game;

import javax.swing.JFrame;

public class Game {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final int GRAVITY = -1;
	
	private JFrame window;
	private GamePanel gamePanel;
	
	public Game() {
		window = new JFrame();
		gamePanel = new GamePanel();
		window.add(gamePanel);
		setup();
	}
	
	private void setup() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(WIDTH + 1, HEIGHT + 23);
		window.setVisible(true);
		window.addKeyListener(gamePanel);
		window.addMouseListener(gamePanel);
		gamePanel.start();
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
