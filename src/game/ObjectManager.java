package game;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {

	private ArrayList<GameObject> objects;
	
	public ObjectManager() {
		objects = new ArrayList<GameObject>();
	}
	
	public void add(GameObject o) {
		objects.add(o);
	}
	
	public void update() {

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
	
	public void draw(Graphics g) {
		for (GameObject object : objects) {
			object.draw(g);
		}
	}

	public void clear() {
		objects.clear();
	}
	
}
