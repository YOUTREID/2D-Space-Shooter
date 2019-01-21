package dev.johnny.main;

import java.awt.Rectangle;

public class GameObject {//just a game object to make bounds intersection earlier

	
	public double x, y;
	
	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds(int width, int height) {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
}
