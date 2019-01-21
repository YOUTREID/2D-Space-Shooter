//minh
package dev.johnny.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{ // checks if mouse clicks on menu buttons 
	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//PLAY BUTTON
		if(mx >= Game.WIDTH / 2 + 80 && mx <= Game.WIDTH / 2 + 180) {
			if(my >= 150 && my <= 200) {
				Game.State = Game.STATE.GAME;
			}
		}
		
		//QUIT BUTTON
		if(mx >= Game.WIDTH / 2 + 80 && mx <= Game.WIDTH / 2 + 180) {
			if(my >= 350 && my <= 400 && Game.isInGame == false) {
				System.exit(1);
			}
		}

		//HELP BUTTON
		if(mx >= Game.WIDTH / 2 + 80 && mx <= Game.WIDTH / 2 + 180) {
			if(my >= 250 && my <= 300 && Game.isInGame == false) {
				Game.State = Game.STATE.HELP;
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
}
