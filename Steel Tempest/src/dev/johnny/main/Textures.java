//haesung
package dev.johnny.main;

import java.awt.image.BufferedImage;

public class Textures { // makes textures with sprite sheet
	
	public BufferedImage player, missle, enemy;
	
	private SpriteSheet ss;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
	
		getTextures();
	}
	
	private void getTextures() {
		player = ss.grabImage(1, 1, 31, 31);
		missle = ss.grabImage(2, 1, 32, 32);
		enemy = ss.grabImage(3, 1, 32, 32);
	}
}
