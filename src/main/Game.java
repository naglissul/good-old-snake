package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import gameStates.StateHandler;
import gameStates.StateName;

public class Game extends Canvas implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = 480;
	private Thread thread;
	private boolean running = false;
	
	private StateHandler handler;
	public static int speed = 10;
	
	public Game() {
		
		addKeyListener(this);
		handler = new StateHandler();
		handler.setState(StateName.LEVEL1);
		new Window(WIDTH, HEIGHT, "SNAKE", this);
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
		this.requestFocus(); 
		//game loop
		long oldTime = System.currentTimeMillis();
		while (running) {
			if (System.currentTimeMillis() >= oldTime+1000/speed) {
				tick();
				render();
				oldTime = System.currentTimeMillis();
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//SETTING WINDOW
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
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
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		handler.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
}
