//minh
package dev.johnny.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu { //initialize and draw menu text and buttons
	
	public Rectangle playButton = new Rectangle(Game.WIDTH/2 + 80, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH/2 + 80, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 80, 350, 100, 50);

	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 40);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Pew Pew", 160, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 19, playButton.y + 35);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
		g2d.draw(quitButton);
	}
}
