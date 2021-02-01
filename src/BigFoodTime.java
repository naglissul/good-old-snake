import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BigFoodTime extends GameObject {
	public static int foodLeft = 150;
	private int track = 1, tr1 = 6 - Snake.speed, tr2 = 1;
	public BigFoodTime(int x, int y) {
		super(x, y);
		id = ID.BigFoodTime;
		if (tr1 <= 0) {
			tr1 = 1;
			tr2 = Snake.speed - 4;
		}
		
	}

	@Override
	public void tick() {
		if (track == tr1) {
			foodLeft -= tr2;
			track = 0;
		}
		track++;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(x, y, 150, 10);
		g.fillRect(x, y, foodLeft, 10);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
