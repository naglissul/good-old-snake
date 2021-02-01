

//Should run on another thread - get information from KeyInput about keystrokes, wait, do velocity change
//nope
//just follow old phX and old phY if they don't coincide with curr phY and phX
public class Tail extends Snake {
	private Snake follow;
	public Tail(int x, int y, Snake follow) {
		super(x, y);
		x = follow.x - 16;
		y = follow.y;
		id = ID.Tail;
		this.follow = follow;
		oldX = phX;
		oldY = phY;
	}

	public void tick() {
		
		//PHYSICAL COORDINATES that follow snake
		if (follow.oldX != follow.phX || follow.oldY != follow.phY) {
			phX = follow.oldX;
			follow.oldX = follow.phX;
			phY = follow.oldY;
			follow.oldY = follow.phY;
		}
		
		//EDGES
		checkEdges();
		
	}
}
