package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Ball extends GameObject {

	public static final int height = 50;
	public static final int width = 50;
	
	private static final float diffuse = 1;
	
	private int velX, velY, counter;
	private boolean inMotion = false, right = false, left = false;
	private ArrayList<Wall> walls;
	
	public Ball(int x, int y, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
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
		System.out.println(velY);
		for (Wall wall : walls) {
			if ((x + width >= wall.getX1()) && (x + width <= wall.getX2()) && ((y + height >= wall.getY1()) && (y + height <= wall.getY2()) || ((y >= wall.getY1()) && (y <= wall.getY2())))) { 
				velX = -velX;
			} // left side
			if ((x >= wall.getX1()) && (x <= wall.getX2()) && ((y + height >= wall.getY1()) && (y + height <= wall.getY2()) || ((y >= wall.getY1()) && (y <= wall.getY2())))) {
				velX = -velX;
			} //right side
			if ((y + height >= wall.getY1()) && (y + height <= wall.getY2()) && ((x + width >= wall.getX1()) && (x + width <= wall.getX2()) || ((x >= wall.getX1()) && (x <= wall.getX2())))) {
				velY = -velY;
			} //top side
			if ((y >= wall.getY1()) && (y <= wall.getY2()) && ((x + width >= wall.getX1()) && (x + width<= wall.getX2()) || ((x >= wall.getX1()) && (x <= wall.getX2())))) {
				velY = -velY;
			} //bottom side
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
