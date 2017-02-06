package game;

import java.awt.Color;
import java.awt.Graphics;

public class DeathWall extends Wall {

	public DeathWall(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
	}
	
}
