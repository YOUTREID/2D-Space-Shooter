package dev.johnny.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import dev.johnny.main.classes.EntityA;
import dev.johnny.main.classes.EntityB;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 240; // a constant for width of the game
	public static final int HEIGHT = 320;	//a constant for height of the game
	public static final int SCALE = 2;
	public final String TITLE = "Reeeeee ~~~~";
	
	private boolean running = false; //the class implements runnable, it is used to create thread
	private Thread thread;  //even though this program only has 1 execution, I used thread just for practice
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage guide = null;
	
	private boolean isShooting = false;
	
	private int enemy_count = 5;
	private int enemy_killed = 0;
	private int score = 0;
	public static boolean isInGame = false;

	private Player p;
	private Controller c;
	private Textures tex;
	public Menu menu;
	
	public LinkedList<EntityA> ea;
	
	public int getHEALTH() {// a simple get() method
		return HEALTH;
	}

	public void setHEALTH(int hEALTH) { //a set() method
		HEALTH = hEALTH;
	}

	public LinkedList<EntityB> eb;
	
	public int HEALTH = 200;
	
	public static enum STATE{MENU, GAME, HELP}; //creating enums
	
	public static STATE State = STATE.MENU;
	
	public void init() { //initiate bufferedimageloader, graphics, key listner and mouselistener,
		//creates a new texture and controller as well as passing a player variable, initializing a menu
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/sheet.png");
			background = loader.loadImage("/background.png");
			guide = loader.loadImage("/guide.png");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		tex = new Textures(this);
		c = new Controller(tex, this);
		p = new Player(230, 500, tex, this, c);

		menu = new Menu();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		c.createEnemy(enemy_count);
	}
	
	private synchronized void start() {// sets the running state to true and starts the thread
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() { //stops the game from running
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run() { //updates fps and ticks on console output and render graphics
		init();
		long lastTime = System.nanoTime();
		final double ticks = 60.0;
		double ns = Math.pow(10, 9) / ticks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, fps" + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() { //for each tick, the enemy and player status are checked
		if(State == STATE.GAME) {
			p.tick();
			c.tick();
		}
		if(enemy_killed >= enemy_count) {
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
	}
	
	private void render() { //render updates all the graphics on the panel
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		
		//////draw here //////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this); 
		
		if(State == STATE.GAME) { //rendering graphics in game 
			isInGame = true;
			g.drawImage(background, 0, 0, null);
			p.render(g);
			c.render(g);
			
			//health bar
			g.setColor(Color.gray);
			g.fillRect(5, 5, 200, 25);
			
			g.setColor(Color.green);
			g.fillRect(5, 5, HEALTH, 25);
			
			g.setColor(Color.white);
			g.drawRect(5, 5, 200, 25);
			///////////////////////
			
			
		}else if(State == STATE.MENU) {
			g.drawImage(background,  0,  0,  null);
			menu.render(g);
			
		}else if((State == STATE.HELP) && (isInGame == false))  {
			g.drawImage(guide, 0, 0, null);
		}
		
		//////////////////////
		
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e) { // sees if certain key is pressed 
		int key = e.getKeyCode();
		
		if(State == STATE.GAME) {
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			p.setVelX(4);
		}else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			p.setVelX(-4);
		}else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			p.setVelY(4);
		}else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			p.setVelY(-4);
		}else if(key == KeyEvent.VK_SPACE && !isShooting) {
			isShooting = true;
			c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
		}
		}
	}
	public void keyReleased(KeyEvent e) { //sees if certain key is released
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			p.setVelX(0);
		}else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			p.setVelX(0);
		}else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			p.setVelY(0);
		}else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			p.setVelY(0);
		}else if(key == KeyEvent.VK_SPACE) {
			isShooting = false;
		}
	}
	
	public static void main(String[] args) { //sets the size of the window as well as closing mechanics 
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		//setting up display & window
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}


	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}
	
	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
}
