import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = 480;
	public static final int FRAME_WIDTH = WIDTH-32, FRAME_HEIGHT = HEIGHT-64;
	private Thread thread;
	private static Snake snake;
	private boolean running = false;
	public static boolean pause = false;
	private boolean gridEnable = true;
	
	public static Handler handler;
	public static Random r;

	public static int count = 0;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput());
		
		new Window(WIDTH, HEIGHT, "SNAKE", this);
		r = new Random();
		

		//CREATING OBJECTS
		beginningPoint();
			
	}
	

	public static void beginningPoint() {
		snake = new Snake(WIDTH/2-30, HEIGHT/2-30);
		handler.addObject(snake);
		handler.addFirstTail(snake);
		handler.addTail();
		handler.addTail();
		handler.addObject(new Food(r.nextInt(FRAME_WIDTH)+10, r.nextInt(FRAME_HEIGHT)+10, ID.Food));

	}
	
	public static void restartPoint() {
		handler.clearAll();
		Snake.score = 0;
		Snake.dead = false;
	}
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// this line releases your from such a burden of clicking on the window
		this.requestFocus(); 
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) 
				render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		//which has to be analyzed... one day
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			//creating 3 buffers, which means whatever
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//SETTING WINDOW AND BACKGROUND
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// GRID
		if (gridEnable) {
			g.setColor(Color.blue);
			for (int i = 0; i < 39; i++) {
				g.drawLine(10+16*i, 10, 10+16*i, FRAME_HEIGHT+10);
			}

			for (int i = 0; i < 27; i++) {
				g.drawLine(10, 10+16*i, FRAME_WIDTH+10, 10+16*i);
			}
		}
		g.setColor(Color.green);
		g.drawRect(10, 10, FRAME_WIDTH, FRAME_HEIGHT);
		
		handler.render(g);
		
		g.setColor(Color.white);
		g.drawString("SCORE: " + Snake.score, 20, 50);
		if (Snake.dead) {
			g.setColor(Color.white);
			g.drawString("DED", 304, 208);
		}
		if (pause) {
			g.setColor(Color.white);
			g.drawString("PAUSE", 304, 208);
		}
	
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min) 
			return var = min;
		else
			return var;
	}
	
	public static void main(String[] args) {
		new Game();
	
	}

}
