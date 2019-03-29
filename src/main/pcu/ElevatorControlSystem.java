package main.pcu;

/*
 * Main control system to interact with the back-end of the controller via embedded C code.
 * The system would return true/false, if the doors are already closed, why close again?
 * 
 * All design implementation.
 */

public class ElevatorControlSystem {

	protected long registeredID;
	
	public ElevatorControlSystem(long uniqueID) {
		registeredID = uniqueID;
	}
	
	public void callOpenElevator() {
		System.out.println(registeredID + " Elevator door " + registeredID + " has opened.");
	}
	
	public void callCloseElevator() {
		System.out.println(registeredID + " Elevator door " + registeredID + " has closed");
	}
	
	public void ascendElevator(int floors) {
		System.out.println(registeredID + " Elevator has ascended " + floors + ".");
	}
	
	public void descendElevator(int floors) {
		System.out.println(registeredID + " Elevator has descended " + floors + ".");
	}
	
}
