package game;

import java.awt.Graphics;

public class GameObject {

	protected float x, y, width, height;
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
	}
	
	public void reverseDirection() {
		
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
}
