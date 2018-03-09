package game;

import java.awt.Color;
import java.awt.Graphics;

public class Goal extends Wall{
	
	Goal(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(51 * 4 / 3, 102 * 4 / 3, 0));
		g.fillRect(Math.round(vector.x), Math.round(vector.y), Math.round(size.x), Math.round(size.y));
	}
	
}
