package game;

public class Ball extends GameObject {

	private int velX, velY;
	
	public Ball(int x, int y, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		width = 50;
		height = 50;
	}
	
	public void update() {
		move();
		if (x + width + 1 > Game.WIDTH) {
			velX = -velX;
			x += velX;
		}
		if (x < 0 - 1) {
			velX = -velX;
			x += velX;
		}
		if (y + height + 1 > Game.HEIGHT) {
			velY = -velY;
			y += velY;
		} 
		if (y < 0 - 1) {
			velY = -velY;
			y += velY;
		}
	}
	
	private void move() {
		x += velX;
		y += velY;
	}
	
}
