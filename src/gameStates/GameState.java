package gameStates;

import java.awt.Graphics;

public abstract class GameState {
	
	public StateHandler handler;
	
	GameState(StateHandler handler) {
		this.handler = handler;
	}
	public abstract void init();
	public abstract void render(Graphics g);
	public abstract void tick();
	public abstract void keyPressed(int key);
	public int grid(int a) {
		return a*16;
	}
}
