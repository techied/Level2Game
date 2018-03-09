package game;

import com.sun.javafx.geom.Vec2f;

import java.awt.Graphics;

public class GameObject {

    Vec2f vector;
    Vec2f size;
    protected boolean isAlive;


    public GameObject() {
        vector = new Vec2f();
        size = new Vec2f();
        isAlive = true;
    }

    public void update() {
        if (vector.x > Game.WIDTH || vector.x < 0 || vector.y < 0 || vector.y > Game.HEIGHT) {
            isAlive = false;
        }
    }

    public boolean isDead() {
        return !isAlive;
    }

    public void draw(Graphics g) {
    }

    public void reverseDirection() {

    }

    public void setX(float x) {
        vector.x = x;
    }

    public void setY(float y) {
        vector.y = y;
    }

}
