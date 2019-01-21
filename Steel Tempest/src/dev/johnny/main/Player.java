package dev.johnny.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.johnny.main.Game.STATE;
import dev.johnny.main.classes.EntityA;
import dev.johnny.main.classes.EntityB;

public class Player extends GameObject implements EntityA{ // updates player object (location, health, etc.)

	private double velX = 0;
	private double velY = 0; 
	private Textures tex;
	Game game;
	Controller controller;
	 
	public Player(double x, double y, Textures tex, Game game, Controller controller) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
	} 
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0)
			x = 0;
		if(x >= 480 - 20)
			x = 480 - 20;
		if(y <= 0)
			y = 0;
		if(y >= 640 - 32)
			y = 640 - 32;
		
		for(int i = 0; i < game.eb.size(); i++) {
			EntityB tempEnt = game.eb.get(i);
			
			if(Fizzix.Collision(this, tempEnt)) {
				controller.removeEntity(tempEnt);
				game.HEALTH -= 20;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				game.setScore(game.getScore() + 1);
			}
		}
		
		if(game.getHEALTH() == 0) {
			
			Game.State = STATE.MENU;
			Game.isInGame = false;
			game.setEnemy_killed(0);
			game.setScore(0);
			game.setEnemy_count(5);
			x = 240;
			y = 500;
			game.setHEALTH(200);
			
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.player, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}
}
