package game;

import com.sun.javafx.geom.Vec2f;
import com.sun.javafx.geom.Vec4f;

public class MovingWall extends Wall {

	Vec2f vel;
	Vec4f bound;
	
	MovingWall(float x, float y, float width, float height, float velX, float velY, Vec4f bound) {
		super(x, y, width, height);
		vel = new Vec2f();
		this.bound = new Vec4f();
		vel.x = velX;
		vel.y = velY;
		this.bound.x = bound.w;
		this.bound.z = bound.x;
        this.bound.y = bound.y;
        this.bound.w = bound.z;
	}

	public void update() {
        super.update();
        if (vector.x + size.x + vel.x > bound.x) {
            vel.x = -vel.x;
            vector.x = bound.x - size.x;
        } else if (vector.x + vel.x < bound.z) {
            vel.x = -vel.x;
            vector.x = bound.z;
        } else {
            vector.x += vel.x;
        }

        if (vector.y + size.y + vel.y > bound.y) {
            vel.y = -vel.y;
            vector.y = bound.y - size.y;
        } else if (vector.y + vel.y < bound.w) {
            vel.y = -vel.y;
            vector.y = bound.w;
        } else {
            vector.y += vel.y;
        }

    }

}
