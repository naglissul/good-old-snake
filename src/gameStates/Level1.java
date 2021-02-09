package gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import gameObjects.Food;
import gameObjects.GameObject;
import gameObjects.ID;
import gameObjects.Snake;
import gameObjects.Tail;

public class Level1 extends GameState {
	
	Level1(StateHandler handler) {
		super(handler);
	}

	public LinkedList<GameObject> objects;
	public LinkedList<Tail> tail;
	private Snake snake;
	
	private boolean pause = false, gridEnable = false;
	public final int FRAME_WIDTH = 37, FRAME_HEIGHT = 26;
	private int frameX = 16, frameY = 16;
	public int score = 0;
	public int tailReserve;
	
	private int randX, randY;

	public Random r;
	
	
	public void init() {
		objects = new LinkedList<GameObject>();
		tail = new LinkedList<Tail>();
		r = new Random();
		
		snake = new Snake(18, 12, this);
		objects.add(snake);
		tail.add(new Tail(-10, -10, this, snake));
		tailReserve = 2;
		objects.add(new Food(r.nextInt(FRAME_WIDTH-1)+1, r.nextInt(FRAME_HEIGHT-1)+1, ID.Food));
	}

	public void keyPressed(int key) {
		
		if (key == KeyEvent.VK_P) {
			pause = !pause;
		}
		
		if (!pause) {
			for (int i = 0; i < objects.size(); i++) {
				GameObject tempObj = objects.get(i);
				
				//keyPressed for Snake
				if (tempObj.getId() == ID.Snake) {
					Snake tempObject = (Snake) tempObj;
					if (tempObject.getVelY() == 0 && tempObject.getOldX() != tempObject.getX()) { // restricting snake's movement and preventing from front to front crash
						if (key == KeyEvent.VK_UP) {
							tempObject.setVelX(0);
							tempObject.setVelY(-1);
						}
						if (key == KeyEvent.VK_DOWN) {
							tempObject.setVelX(0);
							tempObject.setVelY(1);
						}
					}
					else if (tempObject.getVelX() == 0 && tempObject.getOldY() != tempObject.getY()){
						if (key == KeyEvent.VK_LEFT) {
							tempObject.setVelY(0);
							tempObject.setVelX(-1);
						}
						if (key == KeyEvent.VK_RIGHT) {
							tempObject.setVelY(0);
							tempObject.setVelX(1);
						}
					}
				}
				
			}
		}
		
		if (key == KeyEvent.VK_E) gridEnable = !gridEnable;
	}
	
	public void render(Graphics g) {
		// OBJECTS
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.render(g);
		}
		for (int i = 0; i < tail.size(); i++) {
			Tail tempTail = tail.get(i);
			tempTail.render(g);
		}
		// PAUSE
		if (pause) {
			g.setColor(Color.WHITE);
			g.drawString("PAUSE", 304, 208);
		}
		// GRID
		if (gridEnable) {
			g.setColor(Color.BLUE);
			for (int i = 0; i < 39; i++) {
				g.drawLine(frameX+grid(i), frameY, frameX+grid(i), grid(FRAME_HEIGHT)+frameY);
			}

			for (int i = 0; i < 27; i++) {
				g.drawLine(frameX, frameY+grid(i), grid(FRAME_WIDTH)+frameX, frameY+grid(i));
			}
		}
		// FRAME
		g.setColor(Color.GREEN);
		g.drawRect(frameX, frameY, FRAME_WIDTH*16, FRAME_HEIGHT*16);
		// SCORE
		g.setColor(Color.white);
		g.drawString("SCORE: " + score, 40, 50);
			
	}
	
	public void tick() {
		if (!pause) {
			for (int i = 0; i < objects.size(); i++) {
				GameObject tempObject = objects.get(i);
				tempObject.tick();
			}
			for (int i = 0; i < tail.size(); i++) {
				Tail tempTail = tail.get(i);
				tempTail.tick();
			}
			
			// Just you should add tail objects after all that suff, cuz otherwise this will mess it up
			if (tailReserve > 0) {
				addTail();
				tailReserve--;
			}
		}
		
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
	}
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
	public void addTail() {
		tail.add(new Tail(-10, -10, this, tail.get(tail.size()-1)));
	}
	public void addFood(ID d) {
		// this should prevent food from appearing on the snake or on other food
		// Rectangle object seemed to me that he had a tendency of making a riot, so i used basic way to recognize, if boundaries intersected
		Food food;
		int foodSize;
		if (d == ID.BigFood) foodSize = 2;
		else foodSize = 1;
		
		randX = r.nextInt(FRAME_WIDTH-foodSize)+1;
		randY = r.nextInt(FRAME_HEIGHT-foodSize)+1;
		food = new Food(randX, randY, d);
		
		for (int g = 0; g < objects.size(); g++) {
			GameObject tempObj = objects.get(g);
			if ((food.getX() != tempObj.getX() || food.getY() != tempObj.getY()) && (food.getX() + foodSize - 1 != tempObj.getX() || food.getY() + foodSize - 1 != tempObj.getY())) {
				
				// and if everything is okay with all objects...
				if (g == objects.size()-1) {
					//...loop around all the tails...
					for (int j = 0; j < tail.size(); j++) {
						Tail tempObjct = tail.get(j);
						if ((food.getX() != tempObjct.getX() || food.getY() != tempObjct.getY()) && (food.getX() + foodSize - 1 != tempObjct.getX() || food.getY() + foodSize - 1 != tempObjct.getY())) continue;
						else {
							// and if something is wrong... repeat
							randX = r.nextInt(FRAME_WIDTH-foodSize)+1;
							randY = r.nextInt(FRAME_HEIGHT-foodSize)+1;
							food = new Food(randX, randY, d);
							g = -1;
							break;
						}
					}
				}
				continue;
			}
			else {
				// if something's wrong - repeat
				randX = r.nextInt(FRAME_WIDTH-1)+1;
				randY = r.nextInt(FRAME_HEIGHT-1)+1;
				food = new Food(randX, randY, d);
				g = -1;
			}
		}
		// and finally this food satisfies our whim, therefore we can add it
		addObject(food);
	}
}

	
