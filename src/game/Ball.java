package game;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject {

	private int velX, velY, counter;
	private boolean inMotion = false;
	
	public Ball(int x, int y, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		width = 50;
		height = 50;
	}
	
	public void update() {
		counter++;
		if(inMotion) {
			move();
			if (counter > 2) {
				velY -= Game.GRAVITY;
				counter = 0;
			}
		}
		System.out.println(x + ", " + y + ", " + velY);
		if (x + width >= Game.WIDTH) {
			velX = -velX;
		}
		if (x <= 0) {
			velX = -velX;
		}
		if (y + height >= Game.HEIGHT) {
			velY = -velY;
			velY = (int) Math.round((float) (velY * 3 / 4));
			if (velY == 0) {
				inMotion = false;
			}
		} 
		//if (y <= 0) {
		//	velY = -velY;
		//	velY += resistance;
		//}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x, y, width, height);
	}
	
	public void setInMotion(boolean a) {
		inMotion = a;
	}
	
	private void move() {
		x += velX;
		y += velY;
	}
	
}
