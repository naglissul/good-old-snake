package gameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class StateHandler {
	
	public GameState currState;
	
	public StateHandler() {
		currState = new Level1(this);
	}
	
	private void generalSuff(int key) {
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		
		if (key == KeyEvent.VK_R) {
			setState(StateName.LEVEL1);
		}
	}
	
	public void setState(StateName state) {
		if (state == StateName.MAINMENU) currState = new MainMenu(this);
		if (state == StateName.LEVEL1) currState = new Level1(this);
		if (state == StateName.LEVEL2) currState = new Level2(this);
		if (state == StateName.GAMEOVER) currState = new GameOver(this);
		if (state == StateName.CHOOSESPEED) currState = new ChooseSpeed(this);
		currState.init();
	}
	
	public void tick() {
		currState.tick();
	}
	
	public void render(Graphics g) {
		currState.render(g);
	}
	
	public void keyPressed(int k) {
		generalSuff(k);
		currState.keyPressed(k);
	}
}
