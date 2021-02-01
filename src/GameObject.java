import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x, y, velX, velY;
	protected ID id;

	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	//rectangle intersection for collision
	public abstract Rectangle getBounds();
	
	//setters and getters
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public int getVelX() {
		return velX;
	}
	public int getVelY() {
		return velY;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public ID getId() {
		return id;
	}
	
}
