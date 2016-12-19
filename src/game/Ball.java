package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Ball extends GameObject {

	private int velX, velY, counter;
	private boolean inMotion = false, right = false, left = false;
	private ArrayList<Wall> walls;
	
	public Ball(int x, int y, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		width = 50;
		height = 50;
		walls = new ArrayList<Wall>();
	}
	
	public void update() {
		counter++;
		if(inMotion) {
			move();
			if (counter > 2) {
				velY -= Game.GRAVITY;
				if (right) {
					velX += 1;
				}
				if (left) {
					velX -= 1;
				}
				counter = 0;
			}
		}
		System.out.println(x + ", " + y + ", " + velY + ", " + left + ", " + right);
		if (x + width >= Game.WIDTH) {
			velX = -velX;
		}
		if (x <= 0) {
			velX = -velX;
		}
		if (y + height >= Game.HEIGHT) {
			velY = -velY;
			//velY = (int) Math.round((float) (velY * 3 / 4));
			if (velY == 0) {
				inMotion = false;
			}
		} 
		for (Wall wall : walls) {
			if ((x + width >= wall.getX1()) && (x + width <= wall.getX2()) && (y >= wall.getY1()) && (y <= wall.getY2())) { 
				velX = -velX;
			} // left side
			if ((x >= wall.getX1()) && (x <= wall.getX2()) && (y >= wall.getY1()) && (y <= wall.getY2())) {
				velX = -velX;
			} //right side
		}
	}
	
	public void addWall(Wall w) {
		walls.add(w);
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
	
	public void moveX(boolean right, boolean left) {
		this.right = right;
		this.left = left;
	}
	
}
