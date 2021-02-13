package gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import gameObjects.GameObject;
import gameObjects.Snake;
import gameObjects.Tail;
import main.Game;

public class ChooseSpeed extends Level1 {
	
	private int currChoice = Game.speed / 2;

	ChooseSpeed(StateHandler handler) {
		super(handler);
	}

	@Override
	public void init() {
		objects = new LinkedList<GameObject>();
		tail = new LinkedList<Tail>();
		
		snake = new Snake(18, 12, this);
		tail.add(new Tail(17, 12, this, snake));
		for (int i = 0; i < 7; i++) {
			tail.add(new Tail(16 - i, 12, this, tail.get(i)));
		}
		objects.add(snake);
	}

	@Override
	public void render(Graphics g) {
		
		for (int i = 0; i < tail.size(); i++) {
			Snake tempTail = tail.get(i);
			tempTail.render(g);
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Speed: " + currChoice, 280, 208);
	}


	@Override
	public void keyPressed(int key) {

		if (key == KeyEvent.VK_ENTER) {
			handler.setState(StateName.MAINMENU);
		}
		
		if (key == KeyEvent.VK_UP) {
			currChoice++;
			if (currChoice == 21) {
				currChoice = 1;
			}
			Game.speed = currChoice * 2;
		}
		
		if (key == KeyEvent.VK_DOWN) {
			currChoice--;
			if (currChoice == 0) {
				currChoice = 20;
			}
			Game.speed = currChoice * 2;
		}
		
	}

	@Override
	public void tick() {
		
		for (int i = 0; i < tail.size(); i++) {
			Snake tempTail = tail.get(i);
			tempTail.tick();
		}
		objects.get(0).tick();
	}
}
