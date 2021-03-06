package game;

import com.sun.javafx.geom.Vec2f;

import java.awt.*;

public class GameObject {

    Vec2f vector;
    Vec2f size;
    private boolean isAlive;


    GameObject() {
        vector = new Vec2f();
        size = new Vec2f();
        isAlive = true;
    }

    public void update() {
        if (vector.x > Game.WIDTH || vector.x < 0 || vector.y < 0 || vector.y > Game.HEIGHT) {
            isAlive = false;
        }
    }

    boolean isDead() {
        return !isAlive;
    }

    public void draw(Graphics g) {
    }

    public void setX(float x) {
        vector.x = x;
    }

    public void setY(float y) {
        vector.y = y;
    }

}
