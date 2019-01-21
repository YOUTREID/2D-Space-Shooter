package dev.johnny.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityB { // template for entityB (enemy)

	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
	
}
