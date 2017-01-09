package game;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends GameObject {

	private int dx, dy;
	
	public Wall(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}
	
	public int getX1() {
		return x;
	}
	
	public int getX2() {
		return x + width;
	}
	
	public int getY1() {
		return y;
	}
	
	public int getY2() {
		return y + height;
	}

}
