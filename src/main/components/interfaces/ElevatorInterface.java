package main.components.interfaces;

import main.components.states.ElevatorDirection;
import main.components.states.ElevatorStatus;

public interface ElevatorInterface {

	public void ascend();
	public void descend();
	public void registerNewDestination(int floor);
	public ElevatorDirection direction();
	public ElevatorStatus status();
	
}
