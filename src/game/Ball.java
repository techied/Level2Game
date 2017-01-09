package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class Ball extends GameObject {

	public static final int height = 50;
	public static final int width = 50;
	
	private static final float diffuse = 1;
	
	private int velX, velY, counter;
	private boolean inMotion = false, right = false, left = false;
	private ArrayList<Wall> walls;
	private TreeSet<Integer> wallsLeft = new TreeSet<Integer>();
	private TreeSet<Integer> wallsRight = new TreeSet<Integer>();
	private TreeSet<Integer> wallsUp = new TreeSet<Integer>();
	private TreeSet<Integer> wallsDown = new TreeSet<Integer>();
	
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
		for (Wall wall : walls) {
			if ((x + width >= wall.getX1()) && (x + width <= wall.getX2()) && ((y + height >= wall.getY1()) && (y + height <= wall.getY2()) || ((y >= wall.getY1()) && (y <= wall.getY2())))) { 
				velX = -velX;
				checkGoal(wall);
				System.out.println("l");
			} // left side
			if ((x >= wall.getX1()) && (x <= wall.getX2()) && ((y + height >= wall.getY1()) && (y + height <= wall.getY2()) || ((y >= wall.getY1()) && (y <= wall.getY2())))) {
				velX = -velX;
				checkGoal(wall);
				System.out.println("r");
			} //right side
			if ((y + height >= wall.getY1()) && (y + height <= wall.getY2()) && ((x + width >= wall.getX1()) && (x + width <= wall.getX2()) || ((x >= wall.getX1()) && (x <= wall.getX2())))) {
				velY = -velY;
				checkGoal(wall);
				System.out.println("t");
			} //top side
			if ((y >= wall.getY1()) && (y <= wall.getY2()) && ((x + width >= wall.getX1()) && (x + width<= wall.getX2()) || ((x >= wall.getX1()) && (x <= wall.getX2())))) {
				velY = -velY;
				checkGoal(wall);
				System.out.println("b");
			} //bottom side
		}
	}
	
	private void checkGoal(Wall w) {
		if (w instanceof Goal) {
			System.out.println("You won!");
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
		for(Wall wall : walls) {
			if (wall.getX1() - x - width > 0 && wall.getY1() < y && wall.getY2() > y + height && wall.getX1() > 0 && wall.getX2() < Game.WIDTH) 
				wallsLeft.add(wall.getX1() - x - width);
			if (x - wall.getX2() > 0 && wall.getY1() < y && wall.getY2() > y + height && wall.getX2() < Game.WIDTH && wall.getX1() > 0)
				wallsRight.add(x - wall.getX2());
			if (wall.getY1() - y - height > 0 && wall.getX1() < x && wall.getX2() > x + width && wall.getY1() > 0 && wall.getY2() < Game.HEIGHT)
				wallsUp.add(wall.getY1() - y - height);
			if (y - wall.getY2() > 0 && wall.getX1() < x && wall.getX2() > x + width && wall.getY2() < Game.HEIGHT && wall.getY1() > 0)
				wallsDown.add(y - wall.getY2());
		}
		try {
			System.out.println(wallsRight.first() + "dr");
			System.out.println(wallsLeft.first() + "dl");
			System.out.println(wallsUp.first() + "du");
			System.out.println(wallsDown.first() + "dd");
			if (!(wallsRight.first() + velX < 0 || wallsLeft.first() + velX < 0))
				x += velX;
			if (!(wallsUp.first() + velY < 0 || wallsDown.first() + velY < 0))
				y += velY;
		} catch(Exception IDC) {
			x += velX;
			y += velY;
		}
		wallsLeft.clear();
		wallsRight.clear();
		wallsUp.clear();
		wallsDown.clear();
	}
	
	public void moveX(boolean right, boolean left) {
		this.right = right;
		this.left = left;
	}
	
}
