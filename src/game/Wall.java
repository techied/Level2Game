package game;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends GameObject {

	public Wall(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void update() {

	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
	}

	public float getX1() {
		return x;
	}

	public float getX2() {
		return x + width;
	}

	public float getY1() {
		return y;
	}

	public float getY2() {
		return y + height;
	}

}
