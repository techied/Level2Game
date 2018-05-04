package game;

import java.awt.*;

public class Wall extends GameObject {

    Wall(float x, float y, float width, float height) {
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

    float getX1() {
        return vector.x;
    }

    float getX2() {
        return vector.x + size.x;
    }

    float getY1() {
        return vector.y;
    }

    float getY2() {
        return vector.y + size.y;
    }

}
