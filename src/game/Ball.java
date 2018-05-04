package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class Ball extends GameObject {

    static final int height = 50;
    static final int width = 50;

    private float velX, velY, startX, startY;
    private boolean won = false, right = false, left = false, rightGoal = false, leftGoal = false, upGoal = false, downGoal = false, rightSpike = false, leftSpike = false, upSpike = false, downSpike = false;
    private ArrayList<Wall> walls;
    private TreeSet<Float> wallsLeft = new TreeSet<>();
    private TreeSet<Float> wallsRight = new TreeSet<>();
    private TreeSet<Float> wallsUp = new TreeSet<>();
    private TreeSet<Float> wallsDown = new TreeSet<>();

    Ball(float x, float y) {
        vector.x = x;
        vector.y = y;
        this.velX = 0;
        this.velY = 0;
        walls = new ArrayList<>();
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
            if (((wall.getY1() > vector.y && wall.getY1() < vector.y + height)
                    || (wall.getY2() > vector.y && wall.getY2() < vector.y + height)
                    || (vector.y > wall.getY1() && vector.y + height < wall.getY2()))) {
                float leftD = Math.abs(wall.getX1() - vector.x - width);
                leftGoal = evalSpike(isGoal, isSpike, leftD, wallsLeft)[0];
                leftSpike = evalSpike(isGoal, isSpike, leftD, wallsLeft)[1];
                wallsLeft.add(leftD);
            }
            if (((wall.getY1() > vector.y && wall.getY1() < vector.y + height)
                    || (wall.getY2() > vector.y && wall.getY2() < vector.y + height)
                    || (vector.y > wall.getY1() && vector.y + height < wall.getY2()))) {
                float rightD = Math.abs(vector.x - wall.getX2());
                rightGoal = evalSpike(isGoal, isSpike, rightD, wallsRight)[0];
                rightSpike = evalSpike(isGoal, isSpike, rightD, wallsRight)[1];
                wallsRight.add(rightD);
            }
            if (((wall.getX1() > vector.x && wall.getX1() < vector.x + width)
                    || (wall.getX2() > vector.x && wall.getX2() < vector.x + width)
                    || (vector.x > wall.getX1() && vector.x + width < wall.getX2()))) {
                float upD = Math.abs(wall.getY1() - vector.y - height);
                upGoal = evalSpike(isGoal, isSpike, upD, wallsUp)[0];
                upSpike = evalSpike(isGoal, isSpike, upD, wallsUp)[1];
                wallsUp.add(upD);
            }
            if (((wall.getX1() > vector.x && wall.getX1() < vector.x + width)
                    || (wall.getX2() > vector.x && wall.getX2() < vector.x + width)
                    || (vector.x > wall.getX1() && vector.x + width < wall.getX2()))) {
                float downD = Math.abs(vector.y - wall.getY2());
                downGoal = evalSpike(isGoal, isSpike, downD, wallsLeft)[0];
                downSpike = evalSpike(isGoal, isSpike, downD, wallsLeft)[1];
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

    private boolean[] evalSpike(boolean isGoal, boolean isSpike, float d, TreeSet<Float> walls) {
        boolean[] exit = new boolean[2];
        if (isGoal && d < walls.first()) {
            exit[0] = true;
            exit[1] = false;
        } else if (isSpike && d < walls.first()) {
            exit[0] = false;
            exit[1] = true;
        } else if (d < walls.first()) {
            exit[0] = false;
            exit[1] = false;
        }
        return exit;
    }

    void addWall(Wall w) {
        walls.add(w);
    }

    public void draw(Graphics g) {
        //g.setColor(new Color(0, 0, 160)); BLUE
        g.setColor(Color.BLACK);
        g.fillOval(Math.round(vector.x), Math.round(vector.y), Math.round(width), Math.round(height));
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
        float xDefraction = 1.0f;
        if (wallsRight.first() + velX < 0f) {
            vector.x -= wallsRight.first();
            velX = -velX;
            velX *= xDefraction;
            xCollision = true;
            if (rightGoal) {
                won = true;
                reset();
                return;
            }
            if (rightSpike) {
                vector.x = startX;
                vector.y = startY;
                velX = 0;
                velY = 0;

            }
        }
        if (wallsLeft.first() - velX < 0f) {
            vector.x += wallsLeft.first();
            velX = -velX;
            velX *= xDefraction;
            xCollision = true;
            if (leftGoal) {
                won = true;
                reset();
                return;
            }
            if (leftSpike) {
                vector.x = startX;
                vector.y = startY;
                velX = 0;
                velY = 0;

            }
        }
        float yDefraction = 1.0f;
        if (wallsUp.first() - velY < 0f) {
            vector.y += wallsUp.first();
            velY = -velY;
            velY *= yDefraction;
            yCollision = true;
            if (upGoal) {
                won = true;
                reset();
                return;
            }
            if (upSpike) {
                vector.x = startX;
                vector.y = startY;
                velX = 0;
                velY = 0;
            }
        }
        if (wallsDown.first() + velY < 0f) {
            vector.y -= wallsDown.first();
            velY = -velY;
            velY *= yDefraction;
            yCollision = true;
            if (downGoal) {
                won = true;
                reset();
                return;
            }
            if (downSpike) {
                vector.x = startX;
                vector.y = startY;
                velX = 0;
                velY = 0;
            }
        }
        if (!xCollision)
            vector.x += velX;
        if (!yCollision)
            vector.y += velY;
    }

    private void reset() {
        walls.clear();
        velX = 0;
        velY = 0;
    }

    boolean hasWon() {
        if (won) {
            won = false;
            return true;
        }
        return false;
    }

    void moveX(boolean right, boolean left) {
        this.right = right;
        this.left = left;
    }

    void setStartX(float x) {
        startX = x;
        this.vector.x = x;
    }

    void setStartY(float y) {
        startY = y;
        this.vector.y = y;
    }

}
