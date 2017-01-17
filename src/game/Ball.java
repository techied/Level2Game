package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TreeSet;

public class Ball extends GameObject {

	public static final int height = 50;
	public static final int width = 50;

	private float velX, velY;
	private boolean inMotion = false, right = false, left = false;
	private ArrayList<Wall> walls;
	private TreeSet<Float> wallsLeft = new TreeSet<Float>();
	private TreeSet<Float> wallsRight = new TreeSet<Float>();
	private TreeSet<Float> wallsUp = new TreeSet<Float>();
	private TreeSet<Float> wallsDown = new TreeSet<Float>();

	public Ball(float x, float y, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		walls = new ArrayList<Wall>();
	}

	public void update() {
		// counter++;
		if (inMotion) {
			// if (counter > 2) {
			velY -= Game.GRAVITY;
			if (right) {
				velX += 0.1f;
			}
			if (left) {
				velX -= 0.1f;
			}
			// counter = 0;
			// }
			for (Wall wall : walls) {
				if ((wall.getX1() - x - width > 0) && ((wall.getY1() > y && wall.getY1() < y + height)
						|| (wall.getY2() > y && wall.getY2() < y + height)
						|| (y > wall.getY1() && y + height < wall.getY2())))
					wallsLeft.add(wall.getX1() - x - width);
				if ((x - wall.getX2() > 0) && ((wall.getY1() > y && wall.getY1() < y + height)
						|| (wall.getY2() > y && wall.getY2() < y + height)
						|| (y > wall.getY1() && y + height < wall.getY2())))
					wallsRight.add(x - wall.getX2());
				if ((wall.getY1() - y - height > 0) && ((wall.getX1() > x && wall.getX1() < x + width)
						|| (wall.getX2() > x && wall.getX2() < x + width)
						|| (x > wall.getX1() && x + width < wall.getX2())))
					wallsUp.add(wall.getY1() - y - height);
				if ((y - wall.getY2() > 0) && ((wall.getX1() > x && wall.getX1() < x + width)
						|| (wall.getX2() > x && wall.getX2() < x + width)
						|| (x > wall.getX1() && x + width < wall.getX2())))
					wallsDown.add(y - wall.getY2());
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
		} /*
			 * for (Wall wall : walls) { if ((x + width >= wall.getX1()) && (x +
			 * width <= wall.getX2()) && ((y + height >= wall.getY1()) && (y +
			 * height <= wall.getY2()) || ((y >= wall.getY1()) && (y <=
			 * wall.getY2())))) { velX = -velX; checkGoal(wall);
			 * System.out.println("l"); } // left side if ((x >= wall.getX1())
			 * && (x <= wall.getX2()) && ((y + height >= wall.getY1()) && (y +
			 * height <= wall.getY2()) || ((y >= wall.getY1()) && (y <=
			 * wall.getY2())))) { velX = -velX; checkGoal(wall);
			 * System.out.println("r"); } //right side if ((y + height >=
			 * wall.getY1()) && (y + height <= wall.getY2()) && ((x + width >=
			 * wall.getX1()) && (x + width <= wall.getX2()) || ((x >=
			 * wall.getX1()) && (x <= wall.getX2())))) { velY = -velY;
			 * checkGoal(wall); System.out.println("t"); } //top side if ((y >=
			 * wall.getY1()) && (y <= wall.getY2()) && ((x + width >=
			 * wall.getX1()) && (x + width<= wall.getX2()) || ((x >=
			 * wall.getX1()) && (x <= wall.getX2())))) { velY = -velY;
			 * checkGoal(wall); System.out.println("b"); } //bottom side
			 * 
			 * }
			 */
	}

	public void addWall(Wall w) {
		walls.add(w);
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
		if (inMotion) {
			System.out.println("x: " + x);
			System.out.println("y: " + y);
			System.out.println("velX: " + velX);
			System.out.println("velY: " + velY);
			System.out.println(wallsRight.first() + " Walls Right");
			System.out.println(wallsLeft.first() + " Walls Left");
			System.out.println(wallsUp.first() + " Walls Up");
			System.out.println(wallsDown.first() + " Walls Down");
			wallsLeft.clear();
			wallsRight.clear();
			wallsUp.clear();
			wallsDown.clear();
		}
	}

	public void setInMotion(boolean a) {
		inMotion = a;
	}

	private void move() {
		if (!(wallsRight.first() + velX < 1f || wallsLeft.first() - velX < 1f)) {
			x += velX;
		} else {
			if (velX > 0)
				x += wallsLeft.first();
			else
				x -= wallsRight.first();
			velX = -velX;
		}
		if (!(wallsUp.first() - velY < 1f || wallsDown.first() + velY < 1f)) {
			y += velY;
		} else {
			if (velY > 0)
				y += wallsUp.first();
			else
				y -= wallsDown.first();
			velY = -velY;

		}

	}

	public void moveX(boolean right, boolean left) {
		this.right = right;
		this.left = left;
	}

}
