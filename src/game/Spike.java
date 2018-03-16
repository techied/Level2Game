package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Spike extends Wall {

	private int[] xPoints, yPoints;
	private static final int nPoints = 3;
	//x, y, width , height define the spikes hitbox
	private Spike(int[] xPoints, int[] yPoints, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.xPoints = xPoints;
		this.yPoints = yPoints;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
	// direction:
	// 0 -> right 
	// 1 -> left
	// 2 -> up
	// 3 -> down
	public static ArrayList<Wall> makeSpikes(float x, float y, float width, float height, float nSpikes, float direction) {
		ArrayList<Wall> spikes = new ArrayList<>();
		if (direction >= 2) {
			width /= nSpikes;
		}
		else {
			height /= nSpikes;		
		}
		float xOffset;
		float yOffset;
		for (int i = 0; i < nSpikes; i++) {
			switch (Math.round(direction)) {
				case 0:
					xOffset = 0;
					yOffset = i * height;
					spikes.add(new Spike(new int[]{Math.round(x), Math.round(x), Math.round(x + width)}, new int[]{Math.round(y + yOffset), Math.round(y + yOffset + height), Math.round(y + yOffset + height / 2)}, Math.round(x + xOffset), Math.round(y + yOffset), Math.round(width), Math.round(height))); 
					break;
				case 1:
					xOffset = 0;
					yOffset = i * height;
					spikes.add(new Spike(new int[]{Math.round(x + width), Math.round(x + width), Math.round(x)}, new int[]{Math.round(y + yOffset), Math.round(y + yOffset + height), Math.round(y + yOffset + height / 2)}, Math.round(x + xOffset), Math.round(y + yOffset), Math.round(width), Math.round(height))); 
					break;
				case 2:
					xOffset = i * width;
					yOffset = 0;
					spikes.add(new Spike(new int[]{Math.round(x + xOffset), Math.round(x + xOffset + width), Math.round(x + xOffset + width / 2)}, new int[]{Math.round(y + height), Math.round(y + height), Math.round(y)}, Math.round(x + xOffset), Math.round(y + yOffset), Math.round(width), Math.round(height))); 
					break;
				case 3:
					xOffset = i * width;
					yOffset = 0;
					spikes.add(new Spike(new int[]{Math.round(x + xOffset), Math.round(x + xOffset + width), Math.round(x + xOffset + width / 2)}, new int[]{Math.round(y), Math.round(y), Math.round(y + height)}, Math.round(x + xOffset), Math.round(y + yOffset), Math.round(width), Math.round(height)));
					break;
			}
		
		
		
		}
		return spikes;
	}
	
}
