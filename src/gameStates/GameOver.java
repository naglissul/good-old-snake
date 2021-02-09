package gameStates;

import java.awt.Color;
import java.awt.Graphics;

public class GameOver extends GameState {

	GameOver(StateHandler handler) {
		super(handler);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("you're dead", 304, 208);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void keyPressed(int key) {
		// TODO Auto-generated method stub
		
	}
}
