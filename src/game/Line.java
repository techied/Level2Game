package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Line extends GameObject{

	private int x1, x2, y1, y2;
	private int[] xPoints, yPoints;

	public Line(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		xPoints = new int[4];
		yPoints = new int[4];
		
		xPoints[0] = x1;
		xPoints[1] = x2;
		xPoints[3] = x1 - 25;
		xPoints[2] = x2 - 25;
		
		yPoints[0] = y1;
		yPoints[1] = y2;
		yPoints[3] = y1 - 25;
		yPoints[2] = y2 - 25;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillPolygon(new Polygon(xPoints, yPoints, 4));
	}
	
}
