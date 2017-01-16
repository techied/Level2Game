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
		g.setColor(Color.RED);
		g.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
	}
	
}
