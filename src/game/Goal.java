package game;

import java.awt.Color;
import java.awt.Graphics;

public class Goal extends Wall{
	
	public Goal(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(51 * 4 / 3, 102 * 4 / 3, 0));
		g.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
	}
	
}
