package dev.johnny.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import dev.johnny.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA { // makes bullet and renders it location and returns variables
	
	private Textures tex;
	private Game game;
	
	public Bullet(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.game = game;
		this.tex = tex;
	}
	
	public void tick() {
		y -= 10;
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.missle, (int)x, (int)y, null);
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

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}
