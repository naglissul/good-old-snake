import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Snake extends GameObject {
	public static int speed = 4;
	public static int score = 0;
	public static boolean dead = false;
	private int count = 0;
	//physical coordinates in the grid
	protected int phX, phY;
	protected int oldX, oldY;
	private int randX, randY;

	
	public Snake(int x, int y) {
		super(x, y);
		velX = speed;
		velY = 0;
		id = ID.Snake;
		setPh();
		oldX = phX;
		oldY = phY;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//EDGES
		checkEdges();
		
		//PHYSICAL COORDINATES
		setPh();
		
		//COLLISION WITH FOOD
		eat();
		
		checkEdges();
		
		//COLLISION WITH TAIL
		die();
	}
	
	protected void setPh() {
		phX = (x-10)/16*16+10;
		phY = (y-10)/16*16+10;
	}
	
	protected void checkEdges() {
		if (x > Game.FRAME_WIDTH + 9) x = 10;
		else if (x < 10) x = Game.FRAME_WIDTH + 9;
		if (y > Game.FRAME_HEIGHT + 9) y = 10;
		else if (y < 10) y = Game.FRAME_HEIGHT + 9;
		
	}
	
	private void eat() {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject tempObject = Game.handler.object.get(i);
			if (tempObject.getId() == ID.Food && getBounds().intersects(tempObject.getBounds())) {
				Game.handler.removeObject(tempObject);
					randX = Game.r.nextInt(Game.FRAME_WIDTH)+10;
					randY = Game.r.nextInt(Game.FRAME_HEIGHT)+10;
					Game.handler.addObject(new Food(randX, randY, ID.Food));
					
					count++;
					score += speed;
					for (int g = 0; g < speed/2; g++) {
						Game.handler.addTail();
					}

					
					
					
			}
			if (tempObject.getId() == ID.BigFood && getBounds().intersects(tempObject.getBounds())) {
				Game.handler.removeObject(tempObject);
				
				score += speed*(BigFoodTime.foodLeft+15)/15;
				for (int g = 0; g < speed*(BigFoodTime.foodLeft+30)/30; g++) {
					Game.handler.addTail();
				}
				for (int g = 0; g < Game.handler.object.size(); g++) {
					GameObject t2 = Game.handler.object.get(g);
					if (t2.getId() == ID.BigFoodTime) {
						BigFoodTime.foodLeft = 150;
						Game.handler.removeObject(t2);
					}
				}
								
			}
			if (tempObject.getId() == ID.BigFoodTime) {
				if (BigFoodTime.foodLeft <= 0) {
					BigFoodTime.foodLeft = 150;
					Game.handler.removeObject(tempObject);
					for (int g = 0; g < Game.handler.object.size(); g++) {
						GameObject t2 = Game.handler.object.get(g);
						if (t2.getId() == ID.BigFood) {
							Game.handler.removeObject(t2);
						}
					}
				}
			}
			if (count == 5) {
				randX = Game.r.nextInt(Game.FRAME_WIDTH-16)+10;
				randY = Game.r.nextInt(Game.FRAME_HEIGHT-16)+10;
				Game.handler.addObject(new Food(randX, randY, ID.BigFood));
				Game.handler.addObject(new BigFoodTime(20, 20));
				count = 0;
			}
		}
	}
	
	private void die() {
		for (int i = 0; i < Game.handler.tail.size(); i++) {
			Tail tempTail = Game.handler.tail.get(i);
			if (getBounds().intersects(tempTail.getBounds())) {
				velX = 0;
				velY = 0;
				dead = true;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(phX, phY, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(phX, phY, 16, 16);
	}
	
	
}
