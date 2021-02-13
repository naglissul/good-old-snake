package gameObjects;

import gameStates.Level1;

public class Tail extends Snake {
	private Snake follow;
	public Tail(int x, int y, Level1 levelHandler, Snake follow) {
		super(x, y, levelHandler);
		id = ID.Tail;
		this.follow = follow;
		x = follow.oldX;
		y = follow.oldY;
	}

	public void tick() {
		
		oldX = x;
		oldY = y;
		
		// COORDINATES that follow snake
		x = follow.oldX;
		y = follow.oldY;
		
		//EDGES
		checkEdges();
		
	}
}
