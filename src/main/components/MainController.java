package main.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

import main.components.interfaces.ControllerInterface;

public class MainController implements ControllerInterface {
	
	private int numberOfElevators;
	private int numberOfFloors;
	
	private ArrayList<Elevator> elevators;
	private PriorityQueue<Integer> floors;
	
	public MainController(int numberOfElevators, int numberOfFloors) {
		if (numberOfElevators < 0 || numberOfFloors < 0) {
			System.err.println("Integers must be positive.");
		}
		this.numberOfElevators = numberOfElevators;
		this.numberOfFloors = numberOfFloors;
		
		elevators = new ArrayList<Elevator>(numberOfElevators);
		floors = new PriorityQueue<Integer>(numberOfFloors);
		initializeElevators();
	}
	
	private void initializeElevators() {
		for (int i = 0; i < numberOfElevators; i++) {
			elevators.add(new Elevator(i, 0, numberOfFloors));
		}
	}

	public ArrayList<Elevator> getElevators() {
		return elevators;
	}
	
	public void addLocation(int floor) {
		floors.add(floor);
	}
	
	public void goTo(int elevatorID, int floor) {
		elevators.get(elevatorID).registerNewDestination(floor);
	}

	public void check() {
		Collections.sort(elevators);
		for (Elevator elevator : elevators) {
			switch (elevator.status()) {
				case FULL:
					switch (elevator.direction()) {
						case DOWN:
							elevator.descend();
							break;
						case IDLE:
							elevator.removeDestination();
							break;
						case UP:
							elevator.ascend();
							break;
					
					}
					break;
				case EMPTY:
					if (!floors.isEmpty()) {
						elevator.registerNewDestination(floors.poll());
						elevator.callElevator();
					}
					break;
			
			}
		}
	}

}
