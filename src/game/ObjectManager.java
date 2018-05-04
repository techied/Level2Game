package game;

import java.awt.*;
import java.util.ArrayList;

class ObjectManager {

    private ArrayList<GameObject> objects;

    ObjectManager() {
        objects = new ArrayList<>();
    }

    void add(GameObject o) {
        objects.add(o);
    }

    void update() {

        for (GameObject object : objects) {
            object.update();
        }
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).isDead()) {
                objects.remove(i);
                i--;
            }
        }
    }

    void draw(Graphics g) {
        for (GameObject object : objects) {
            object.draw(g);
        }
    }

    void clear() {
        objects.clear();
    }

}
