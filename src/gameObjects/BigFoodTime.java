package gameObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Game;

public class BigFoodTime extends GameObject {
	
	public static int foodLeft = 150;
	
	public BigFoodTime(int x, int y) {
		super(x, y);
		id = ID.BigFoodTime;
	}

	@Override
	public void tick() {
		foodLeft -= Game.speed / 5;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(x, y, 150, 10);
		g.fillRect(x, y, foodLeft, 10);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
	
}
