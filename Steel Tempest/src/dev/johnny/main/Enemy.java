package dev.johnny.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import dev.johnny.main.classes.EntityA;
import dev.johnny.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB{ // renders enemy entities and update score and enemy killed in the linked list
	
	private Textures tex;
	Random r = new Random();
	private Game game;
	private Controller c;
	
	private int speed = r.nextInt(3) + 1;
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game) {
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
	}
	
	public void tick() {
		y += speed;
		
		if(y > (Game.HEIGHT * Game.SCALE)) {
			y = -10;
			x = r.nextInt(Game.WIDTH * Game.SCALE);
		}
		
		for(int i = 0; i < game.ea.size(); i++) {
			EntityA tempEnt = game.ea.get(i);

			if(Fizzix.Collision(this, tempEnt)) {
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				game.setScore(game.getScore() + 1);
			}
		}
	}
			
	
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int)x, (int)y, null);
		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Score:", 330, 40);
		g.drawString(Integer.toString(game.getScore()), 430, 41);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}
}
