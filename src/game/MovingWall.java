package game;

public class MovingWall extends Wall {

	private float velX, velY, leftXBound, rightXBound, upYBound, downYBound;
	
	public MovingWall(float x, float y, float width, float height, float velX, float velY, float leftXBound, float rightXBound, float upYBound, float downYBound) {
		super(x, y, width, height);
		this.velX = velX;
		this.velY = velY;
		this.rightXBound = rightXBound;
		this.leftXBound = leftXBound;
		this.upYBound = upYBound;
		this.downYBound = downYBound;
	}

	public void update() {
		super.update();
		if (x + width + velX > rightXBound) {
			velX = -velX;
			x = rightXBound - width;
		} 
		else if (x + velX < leftXBound) {
			velX = -velX;
			x = leftXBound;
		} else {
			x += velX;
		}
		if (y + height + velY > downYBound) {
			velY = -velY;
			y = downYBound - height;
		} 
		else if (y + velY < upYBound) {
			velY = -velY;
			y = upYBound;
		}
		else {
			y += velY;
		}
	}
	
}
