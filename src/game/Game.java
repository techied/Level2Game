package game;

import javax.swing.*;

public class Game {

    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    static final float BALL_SPEED_INCREMENT = -0.1f;

    private JFrame window;
    private GamePanel gamePanel;

    public Game() {
        window = new JFrame();
        gamePanel = new GamePanel();
        window.add(gamePanel);
        setup();
    }

    private void setup() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT + 23);
        window.setVisible(true);
        window.addKeyListener(gamePanel);
        window.addMouseListener(gamePanel);
    }

    public static void main(String[] args) {
        new Game();
    }
}
