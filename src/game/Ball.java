package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.swing.plaf.synth.SynthDesktopIconUI;

public class Ball extends GameObject {

	public static final int height = 50;
	public static final int width = 50;
	
	private static final float diffuse = 1;
	
	private float velX, velY;
	private boolean inMotion = false, right = false, left = false;
	private ArrayList<Wall> walls;
	private TreeSet<Float> wallsLeft = new TreeSet<Float>();
	private TreeSet<Float> wallsRight = new TreeSet<Float>();
	private TreeSet<Float> wallsUp = new TreeSet<Float>();
	private TreeSet<Float> wallsDown = new TreeSet<Float>();
	private String dr, dl, du, dd;
	private int counter = 0;
	
	public Ball(float x, float y, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		walls = new ArrayList<Wall>();
	}
	
	public void update() {
		for(Wall wall : walls) {
			if (wall.getX1() - x - width > 0 && wall.getY1() < y && wall.getY2() > y + height) 
				wallsLeft.add(wall.getX1() - x - width);
			if (x - wall.getX2() > 0 && wall.getY1() < y && wall.getY2() > y + height)
				wallsRight.add(x - wall.getX2());
			if (wall.getY1() - y - height > 0 && wall.getX1() < x && wall.getX2() > x + width)
				wallsUp.add(wall.getY1() - y - height);
			if (y - wall.getY2() > 0 && wall.getX1() < x && wall.getX2() > x + width)
				wallsDown.add(y - wall.getY2());
		}
		if (wallsLeft.isEmpty())
			wallsLeft.add(-1f);
		if (wallsRight.isEmpty())
			wallsRight.add(-1f);
		if (wallsUp.isEmpty())
			wallsUp.add(-1f);
		if (wallsDown.isEmpty())
			wallsDown.add(-1f);
		
		dr = wallsRight.first() + "dr";
		dl = wallsLeft.first() + "dl";
		du = wallsUp.first() + "du" + " " + (walls.get(0).getY1() - y - height) + " " + walls.get(0).getY1() + " " + y + " " + (-y - height);
		dd = wallsDown.first() + "dd";
		counter++;
		if(inMotion) {
			move();
			wallsLeft.clear();
			wallsRight.clear();
			wallsUp.clear();
			wallsDown.clear();
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
		}/*
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
		*/
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
		g.fillOval(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
		System.out.println(dr);
		System.out.println(dl);
		System.out.println(du);
		System.out.println(dd);
	}
	
	public void setInMotion(boolean a) {
		inMotion = a;
	}
	
	private void move() {
		if (!(wallsRight.first() < 0 || wallsLeft.first() < 0))
			x += velX;
		else {
			velX = -velX;
			x += velX;
		}
		if (!(wallsUp.first() < 0 || wallsDown.first() < 0))
			y += velY;
		else {
			velY = -velY;
			y += velY;
		}
		
	}
	
	public void moveX(boolean right, boolean left) {
		this.right = right;
		this.left = left;
	}
	
}
