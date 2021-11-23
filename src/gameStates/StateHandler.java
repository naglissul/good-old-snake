package gameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class StateHandler {
	
	public GameState currState;
	
	public StateHandler() {
		setState(StateName.MAINMENU);
	}
	
	private void generalSuff(int key) {
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		
		if (key == KeyEvent.VK_R) {
			setState(StateName.LEVEL1);
		}
	}
	
	public void setState(StateName state) {
		if (state == StateName.MAINMENU) {
			currState = new MainMenu(this);
		}
		if (state == StateName.LEVEL1) {
			currState = new Level1(this);
		}
		if (state == StateName.GAMEOVER) {
			currState = new GameOver(this);
		}
		if (state == StateName.HELP) {
			currState = new Help(this);
		}
		if (state == StateName.CHOOSESPEED) {
			currState = new ChooseSpeed(this);
		}
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
