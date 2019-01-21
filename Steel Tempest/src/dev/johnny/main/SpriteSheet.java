//haesung
package dev.johnny.main;

import java.awt.image.BufferedImage;

public class SpriteSheet { // takes part of sprite sheet
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}
}
