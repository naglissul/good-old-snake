package gameObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import gameStates.Level1;
import gameStates.StateName;
import main.Game;


public class Snake extends GameObject {
	
	public static int bigFoodFreq = 5;
	public static int count = 0;
	public int oldX;
	public int oldY;
	private Level1 levelHandler;

	public Snake(int x, int y, Level1 levelHandler) {
		super(x, y);
		velX = 1;
		velY = 0;
		id = ID.Snake;
		this.levelHandler = levelHandler;
	}

	@Override
	public void tick() {
		
		oldX = x;
		oldY = y;
		
		x += velX;
		y += velY;
		
		//EDGES
		checkEdges();

		//COLLISION WITH FOOD
		eat();
		
		//COLLISION WITH TAIL
		die();
		
	}
	
	protected void checkEdges() {
		if (x > levelHandler.FRAME_WIDTH) {
			x = 1;
		}
		if (x < 1) {
			x = levelHandler.FRAME_WIDTH;
		}
		if (y > levelHandler.FRAME_HEIGHT) {
			y = 1;
		}
		if (y < 1) {
			y = levelHandler.FRAME_HEIGHT;
		}
		
	}
	
	private void eat() {
		for (int i = 0; i < levelHandler.objects.size(); i++) {
			GameObject tempObject = levelHandler.objects.get(i);
			
			// Eat small food
			if (tempObject.getId() == ID.Food && getBounds().intersects(tempObject.getBounds())) {
				levelHandler.removeObject(tempObject);
				levelHandler.addFood(ID.Food);
				count++;
				levelHandler.score += Game.speed / 5;
				levelHandler.tailReserve += Game.speed / 5;
			}
			
			// Eat BigFood
			if (tempObject.getId() == ID.BigFood && getBounds().intersects(tempObject.getBounds())) {
				levelHandler.removeObject(tempObject);
				for (int g = 0; g < levelHandler.objects.size(); g++) {
					GameObject t2 = levelHandler.objects.get(g);
					if (t2.getId() == ID.BigFoodTime) {
						levelHandler.score += BigFoodTime.foodLeft / 5;
						levelHandler.tailReserve += BigFoodTime.foodLeft / 5;
						BigFoodTime.foodLeft = 150;
						levelHandler.removeObject(t2);
					}
				}
			}
			
			// BigFood time's over
			if (tempObject.getId() == ID.BigFoodTime) {
				if (BigFoodTime.foodLeft <= 0) {
					levelHandler.removeObject(tempObject);
					for (int g = 0; g < levelHandler.objects.size(); g++) {
						GameObject t2 = levelHandler.objects.get(g);
						if (t2.getId() == ID.BigFood) {
							levelHandler.removeObject(t2);
						}
					}
				}
			}
			
			// It's time for BigFood
			if (count == bigFoodFreq) {
				levelHandler.addFood(ID.BigFood);
				levelHandler.addObject(new BigFoodTime(20, 20));
				count = 0;
			}
		}
	}
	
	private void die() {
		for (int i = 0; i < levelHandler.tail.size(); i++) {
			Tail tempTail = levelHandler.tail.get(i);
			if (getBounds().intersects(tempTail.getBounds())) {
				velX = 0;
				velY = 0;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				levelHandler.handler.setState(StateName.GAMEOVER);
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(grid(x), grid(y), 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(grid(x), grid(y), 16, 16);
	}
	
	public int getOldX() {
		return oldX;
	}
	
	public int getOldY() {
		return oldY;
	}
}
