package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {

	protected int x, y, width, height;
	protected boolean isAlive;
	
	
	public GameObject() {
		isAlive = true;
	}
	
	public void update() {
		if (x > Game.WIDTH || x < 0 || y < 0 || y > Game.HEIGHT) {
			isAlive = false;
		}
	}
	
	public boolean isDead() {
		return !isAlive;
	}
	
	public void draw(Graphics g) {
		g.fillRect(x, y, width, height);
	}
	
	public void reverseDirection() {
		
	}
	
}
