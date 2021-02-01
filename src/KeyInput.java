import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//loop through all GameObjects
		if (!Game.pause && !Snake.dead) {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject tempObject = Game.handler.object.get(i);
			
			//keyPressed for Snake
			if (tempObject.getId() == ID.Snake) {
				
				if (tempObject.getVelY() == 0) {
					if (key == KeyEvent.VK_UP) {
						tempObject.setVelX(0);
						tempObject.setVelY(-Snake.speed);
					}
					if (key == KeyEvent.VK_DOWN) {
						tempObject.setVelX(0);
						tempObject.setVelY(Snake.speed);
					}
				}
				else {
					if (key == KeyEvent.VK_LEFT) {
						tempObject.setVelY(0);
						tempObject.setVelX(-Snake.speed);
					}
					if (key == KeyEvent.VK_RIGHT) {
						tempObject.setVelY(0);
						tempObject.setVelX(Snake.speed);
					}
				}
			}
			
		}
		}

		if (key == KeyEvent.VK_P) {
			Game.pause = !Game.pause;
			if (Snake.dead) Game.pause = false;
			
		}
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		if (key == KeyEvent.VK_R) {
			Game.restartPoint();
			Game.beginningPoint();
		}
		
	}
	
}
