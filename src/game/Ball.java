package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TreeSet;

public class Ball extends GameObject {

	public static final int height = 50;
	public static final int width = 50;

	private float velX, velY, startX, startY;
	private boolean won = false, right = false, left = false, rightGoal = false, leftGoal = false, upGoal = false, downGoal = false, rightSpike = false, leftSpike = false, upSpike = false, downSpike = false;
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
		restCollisionDetection();
	}

	public void update() {

			velY -= Game.BALL_SPEED_INCREMENT;
			if (right) {
				velX -= Game.BALL_SPEED_INCREMENT;
			}
			if (left) {
				velX += Game.BALL_SPEED_INCREMENT;
			}
			for (Wall wall : walls) {
				boolean isGoal = wall instanceof Goal;
				boolean isSpike = wall instanceof Spike;
				if (((wall.getY1() > y && wall.getY1() < y + height)
						|| (wall.getY2() > y && wall.getY2() < y + height)
						|| (y > wall.getY1() && y + height < wall.getY2()))) {
					float leftD = Math.abs(wall.getX1() - x - width);
					if (isGoal && leftD < wallsLeft.first()) {
						leftGoal = true;
						leftSpike = false;
					}
					else if (isSpike && leftD < wallsLeft.first()) {
						leftSpike = true;
						leftGoal = false;
					} else if (leftD < wallsLeft.first()) {
						leftSpike = false;
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
						rightSpike = false;
					}
					else if (isSpike && rightD < wallsRight.first()) {
						rightSpike = true;
						rightGoal = false;
					} else if (rightD < wallsRight.first()) {
						rightSpike = false;
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
						upSpike = false;
					}
					else if (isSpike && upD < wallsUp.first()) {
						upSpike = true;
						upGoal = false;
					} else if (upD < wallsUp.first()) {
						upSpike = false;
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
						downSpike = false;
					}
					else if (isSpike && downD < wallsDown.first()) {
						downSpike = true;
						downGoal = false;
					} else if (downD < wallsDown.first()) {
						downSpike = false;
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
		 
		if (x == startX && y == startY) {
			
		}
	}

	public void addWall(Wall w) {
		walls.add(w);
	}

	public void draw(Graphics g) {
		//g.setColor(new Color(0, 0, 160)); BLUE
		g.setColor(Color.BLACK);
		g.fillOval(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
		/*
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("velX: " + velX);
		System.out.println("velY: " + velY);
		try {
			System.out.println(wallsRight.first() + " Walls Right");
			System.out.println(wallsLeft.first() + " Walls Left");
			System.out.println(wallsUp.first() + " Walls Up");
			System.out.println(wallsDown.first() + " Walls Down");
		} catch (Exception e) {
			
		}
		*/
		restCollisionDetection();
	}

	private void restCollisionDetection() {
		wallsLeft.clear();
		wallsRight.clear();
		wallsUp.clear();
		wallsDown.clear();
		wallsDown.add(Float.MAX_VALUE);
		wallsLeft.add(Float.MAX_VALUE);
		wallsRight.add(Float.MAX_VALUE);
		wallsUp.add(Float.MAX_VALUE);
		leftGoal = false;
		rightGoal = false;
		upGoal = false;
		downGoal = false;
		leftSpike = false;
		rightSpike = false;
		upSpike = false;
		downSpike = false;
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
			if (rightSpike) {
				x = startX;
				y = startY;
				velX = 0;
				velY = 0;
				
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
			if (leftSpike) {
				x = startX;
				y = startY;
				velX = 0;
				velY = 0;
				
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
			if (upSpike) {
				x = startX;
				y = startY;
				velX = 0;
				velY = 0;
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
			if (downSpike) {
				x = startX;
				y = startY;
				velX = 0;
				velY = 0;
			}
		}
		if (!xCollision)
			x += velX;
		if (!yCollision)
			y += velY;
	}

	private void reset() { 
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
