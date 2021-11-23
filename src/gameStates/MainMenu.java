package gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class MainMenu extends GameState {
	
	private int currChoice = 0;
	private String[] options = {"New Game", "Choose speed", "Help", "Quit"};
	
	public MainMenu(StateHandler handler) {
		super(handler);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Menu: " + options[currChoice], 280, 208);
		g.drawString("UP and DOWN - choose. ENTER - select", 220, 250);
	}

	public void select() {
		if (currChoice == 0) {
			handler.setState(StateName.LEVEL1);
		}
		if (currChoice == 1) {
			handler.setState(StateName.CHOOSESPEED);
		}
		if (currChoice == 2) {
			handler.setState(StateName.HELP);
		}
		if (currChoice == 3) {
			System.exit(1);
		}
	}

	@Override
	public void keyPressed(int key) {
		
		if (key == KeyEvent.VK_ENTER) {
			select();
		}
		
		if (key == KeyEvent.VK_UP) {
			currChoice--;
			if (currChoice == -1) {
				currChoice = options.length - 1;
			}
		}
		
		if (key == KeyEvent.VK_DOWN) {
			currChoice++;
			if (currChoice == options.length) {
				currChoice = 0;
			}
		}
		
	}
	
	@Override
	public void init() {
	}
	
	@Override
	public void tick() {		
	}
	
}
