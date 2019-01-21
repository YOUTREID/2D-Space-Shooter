package dev.johnny.main;

import dev.johnny.main.classes.EntityA;
import dev.johnny.main.classes.EntityB;

public class Fizzix { // detects collision between two types of entities
	
	public static boolean Collision(EntityA enta, EntityB entb) {
		
			
		if(enta.getBounds().intersects(entb.getBounds())) {
			return true;
		}

		return false;
	}
	
	public static boolean Collision(EntityB entb, EntityA enta) {
			
		if(entb.getBounds().intersects(enta.getBounds())) {
			return true;
		}

		return false;
	}
	
	
}
