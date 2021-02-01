import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	LinkedList<Tail> tail = new LinkedList<Tail>();
	
	public void tick() {
		if (!Game.pause && !Snake.dead ) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
		for (int i = 0; i < tail.size(); i++) {
			Tail tempTail = tail.get(i);
			tempTail.tick();
		}
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		for (int i = 0; i < tail.size(); i++) {
			Tail tempTail = tail.get(i);
			tempTail.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearAll() {
		object.clear();
		tail.clear();
	}
	public void addFirstTail(Snake snake) {
		this.tail.add(new Tail(-100, -100, snake));
	}
	public void addTail() {
		this.tail.add(new Tail(-100, -100, tail.get(tail.size()-1)));
	}
}
