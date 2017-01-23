package game;

import java.util.ArrayList;

public class Level {

	private ArrayList<Wall> walls;
	private Goal goal;
	private float startX, startY;
	
	public Level() {
		walls = new ArrayList<Wall>();
		walls.add(new Wall(0, Game.HEIGHT, Game.WIDTH, Ball.height));
		walls.add(new Wall(-Ball.width, 0, Ball.width, Game.WIDTH));
		walls.add(new Wall(Game.WIDTH, 0, Ball.width, Game.HEIGHT));
		walls.add(new Wall(0, -Ball.height, Game.WIDTH, Ball.height));
		walls.add(new Wall(0, 100, 700, Ball.height));
		walls.add(new Wall(100, 300, 700, Ball.height));
		walls.add(new Goal(0, 750, Game.WIDTH, Ball.height));
		goal = new Goal(0,0,0,0);
		startX = 50;
		startY = 1;
	}
	
	public ArrayList<Wall> getWalls() {
		walls.add(goal); //so goal is always last
		return walls;
	}
	
	public float getStartX() {
		return startX;
	}
	
	public  float getStartY() {
		return startY;
	}
	
}
