package main;

import java.util.ArrayList;

import main.components.Elevator;
import main.components.MainController;

/*
 * Partial thanks to joeblau's sample-elevator-control-system
 * on what states and what missing functions I should have.
 */

public class Main {
	
	public static void main(String[] args) {
		
		MainController controller = new MainController(4, 10);
		controller.addLocation(2);
		controller.addLocation(4);
		controller.check();
		
		for (int i = 0; i < 4; i++) {
			controller.check();
			System.out.println("------------------------------");
		}
		
	}
	
}