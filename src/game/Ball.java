package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TreeSet;

public class Ball extends GameObject {

	public static final int height = 50;
	public static final int width = 50;

	private float velX, velY, startX, startY;
	private boolean inMotion = false, won = false, right = false, left = false, rightGoal = false, leftGoal = false, upGoal = false, downGoal = false, rightDeath = false, leftDeath = false, upDeath = false, downDeath = false;
	private ArrayList<Wall> walls;
	private TreeSet<Float> wallsLeft = new TreeSet<Float>();
	private TreeSet<Float> wallsRight = new TreeSet<Float>();
	private TreeSet<Float> wallsUp = new TreeSet<Float>();
	private TreeSet<Float> wallsDown = new TreeSet<Float>();
	private float xDefraction = 1.0f;
	private float yDefraction = 1.0f;
	
	public Ball(float x, float y) {
		this.x = x;
		this.y = y;
		this.velX = 0;
		this.velY = 0;
		walls = new ArrayList<Wall>();
	}

	public void update() {
		if (inMotion) {
			velY -= Game.GRAVITY;
			if (right) {
				velX += 0.1f;
			}
			if (left) {
				velX -= 0.1f;
			}
			for (Wall wall : walls) {
				boolean isGoal = wall instanceof Goal;
				boolean isDeathWall = wall instanceof DeathWall;
				if (((wall.getY1() > y && wall.getY1() < y + height)
						|| (wall.getY2() > y && wall.getY2() < y + height)
						|| (y > wall.getY1() && y + height < wall.getY2()))) {
					float leftD = Math.abs(wall.getX1() - x - width);
					if (isGoal && leftD < wallsLeft.first()) {
						leftGoal = true;
						leftDeath = false;
					}
					if (isDeathWall && leftD < wallsLeft.first()) {
						leftDeath = true;
						leftGoal = false;
					}
					wallsLeft.add(leftD);
				}
				if (((wall.getY1() > y && wall.getY1() < y + height)
						|| (wall.getY2() > y && wall.getY2() < y + height)
						|| (y > wall.getY1() && y + height < wall.getY2()))) {
					float rightD = Math.abs(x - wall.getX2());
					if (isGoal && rightD < wallsRight.first()) {
						rightGoal = true;
						rightDeath = false;
					}
					if (isDeathWall && rightD < wallsRight.first()) {
						rightDeath = true;
						rightGoal = false;
					}
					wallsRight.add(rightD);
				}
				if (((wall.getX1() > x && wall.getX1() < x + width)
						|| (wall.getX2() > x && wall.getX2() < x + width)
						|| (x > wall.getX1() && x + width < wall.getX2()))) {
					float upD = Math.abs(wall.getY1() - y - height);
					if (isGoal && upD < wallsUp.first()) {
						upGoal = true;
						upDeath = false;
					}
					if (isDeathWall && upD < wallsUp.first()) {
						upDeath = true;
						upGoal = false;
					}
					wallsUp.add(upD);
				}
				if (((wall.getX1() > x && wall.getX1() < x + width)
						|| (wall.getX2() > x && wall.getX2() < x + width)
						|| (x > wall.getX1() && x + width < wall.getX2()))) {
					float downD = Math.abs(y - wall.getY2());
					if (isGoal && downD < wallsDown.first()) {
						downGoal = true;
						downDeath = false;
					}
					if (isDeathWall && downD < wallsDown.first()) {
						downDeath = true;
						downGoal = false;
					}
					wallsDown.add(downD);
				}
			}
			if (wallsLeft.isEmpty())
				wallsLeft.add(Float.MAX_VALUE);
			if (wallsRight.isEmpty())
				wallsRight.add(Float.MAX_VALUE);
			if (wallsUp.isEmpty())
				wallsUp.add(Float.MAX_VALUE);
			if (wallsDown.isEmpty())
				wallsDown.add(Float.MAX_VALUE);
			move();
		} 
	}

	public void addWall(Wall w) {
		walls.add(w);
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
		if (inMotion) {
			try {
				System.out.println("x: " + x);
				System.out.println("y: " + y);
				System.out.println("velX: " + velX);
				System.out.println("velY: " + velY);
				System.out.println(wallsRight.first() + " Walls Right");
				System.out.println(wallsLeft.first() + " Walls Left");
				System.out.println(wallsUp.first() + " Walls Up");
				System.out.println(wallsDown.first() + " Walls Down");
			} catch (Exception e) {
				
			}
			wallsLeft.clear();
			wallsRight.clear();
			wallsUp.clear();
			wallsDown.clear();
			leftGoal = false;
			rightGoal = false;
			upGoal = false;
			downGoal = false;
		}
	}

	public void setInMotion(boolean a) {
		inMotion = a;
	}

	private void move() {
		boolean xCollision = false;
		boolean yCollision = false;
		if (wallsRight.first() + velX < 0f) {
			x -= wallsRight.first();
			velX = -velX;
			velX *= xDefraction;
			xCollision = true;
			if (rightGoal) {
				won = true;
				reset();
				return;
			}
			if (rightDeath) {
				x = startX;
				y = startY;
				velX = 0;
				velY = 0;
				inMotion = false;
			}
		}
		if (wallsLeft.first() - velX < 0f) {
			x += wallsLeft.first();
			velX = -velX;
			velX *= xDefraction;
			xCollision = true;
			if (leftGoal) {
				won = true;
				reset();
				return;
			}
			if (leftDeath) {
				x = startX;
				y = startY;
				velX = 0;
				velY = 0;
				inMotion = false;
			}
		}
		if (wallsUp.first() - velY < 0f) {
			y += wallsUp.first();
			velY = -velY;
			velY *= yDefraction;
			yCollision = true;
			if (upGoal) {
				won = true;
				reset();
				return;
			}
			if (upDeath) {
				x = startX;
				y = startY;
				velX = 0;
				velY = 0;
				inMotion = false;
			}
		}
		if (wallsDown.first() + velY < 0f) {
			y -= wallsDown.first();
			velY = -velY;
			velY *= yDefraction;
			yCollision = true;
			if (downGoal) {
				won = true;
				reset();
				return;
			}
			if (downDeath) {
				x = startX;
				y = startY;
				velX = 0;
				velY = 0;
				inMotion = false;
			}
		}
		if (!xCollision)
			x += velX;
		if (!yCollision)
			y += velY;
	}

	private void reset() { 
		inMotion = false;
		walls.clear();
		velX = 0;
		velY = 0;
	}
	
	public boolean hasWon() {
		if (won) {
			won = false;
			return true;
		}
		return false;
	}
	
	public void moveX(boolean right, boolean left) {
		this.right = right;
		this.left = left;
	}
	
	public void setStartX(float x) {
		startX = x;
		this.x = x;
	}
	
	public void setStartY(float y) {
		startY = y;
		this.y = y;
	}

}
