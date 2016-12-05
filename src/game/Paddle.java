package game;

public class Paddle extends GameObject {

	private boolean right, left;
	private final int speed = 7;
	
	public Paddle() {
		width = 300;
		height = 40;
		x = (Game.WIDTH / 2) - (width / 2);
		y = Game.HEIGHT - height - 50;
		right = false;
		left = false;
	}
	
	public void update() {
		if (right) {
			x += speed;
		}
		if (left) {
			x -= speed;
		}
		if (x + width > Game.WIDTH + speed) {
			x -= speed;
		}
		if (x < 0 - speed) {
			x += speed;
		}
	}
	
	public void inputRight(boolean r) {
		right = r;
	}
	
	public void inputLeft(boolean l) {
		left = l;
	}
	
}
