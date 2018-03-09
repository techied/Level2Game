package game;

import com.sun.javafx.geom.Vec2f;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends GameObject {

    public Wall(float x, float y, float width, float height) {
        vector.x = x;
        vector.y = y;
        size.x = width;
        size.y = height;
    }

    public void update() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(Math.round(vector.x), Math.round(vector.y), Math.round(size.x), Math.round(size.y));
    }

    public float getX1() {
        return vector.x;
    }

    public float getX2() {
        return vector.x + size.x;
    }

    public float getY1() {
        return vector.y;
    }

    public float getY2() {
        return vector.y + size.y;
    }

}
