package game;

public class MovingWall extends Wall {

	private float velX, velY;
	
	public MovingWall(float x, float y, float width, float height, float velX, float velY) {
		super(x, y, width, height);
		this.velX = velX;
		this.velY = velY;
	}

	public void update() {
		super.update();
		if (x + width + velX > Game.WIDTH) {
			velX = -velX;
			x = Game.WIDTH - width;
		} 
		else if (x + velX < 0) {
			velX = -velX;
			x = 0;
		} else {
			x += velX;
		}
		if (y + height + velY > Game.HEIGHT) {
			velY = -velY;
			y = Game.HEIGHT - height;
		} 
		else if (y + velY < 0) {
			velY = -velY;
			y = 0;
		}
		else {
			y += velY;
		}
	}
	
}
