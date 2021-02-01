import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends GameObject {
	
	public Food(int x, int y, ID id) {
		super(x, y);
		this.id = id;
	}

	@Override
	public void tick() {
		x = (x-10)/16*16+10;
		y = (y-10)/16*16+10;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		if (id == ID.BigFood) g.fillRect(x, y, 32, 32);
		else g.fillRect(x, y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		if (id == ID.BigFood) return new Rectangle(x, y, 32, 32);
		else return new Rectangle(x, y, 16, 16);
	}

}
