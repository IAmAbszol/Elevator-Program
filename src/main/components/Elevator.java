package main.components;

import java.util.Comparator;
import java.util.PriorityQueue;

import main.components.interfaces.ElevatorInterface;
import main.components.states.ElevatorDirection;
import main.components.states.ElevatorStatus;
import main.pcu.ElevatorControlSystem;

/*
 * Elevator main object.
 * Controls the elevator object and keeps track of various information.
 */

public class Elevator extends ElevatorControlSystem implements ElevatorInterface, Comparable <Elevator> {

	private long id;
	
	private int currentFloor;
	private int maxFloors;
	private int calls;
	
	private PriorityQueue<Integer> floorQueue;
	private ElevatorDirection direction;
	
	public Elevator(long uniqueID, int currentFloor, int maxFloors) {
		super(uniqueID);
		
		id = uniqueID;
		
		this.currentFloor = currentFloor;
		this.maxFloors = maxFloors;
		calls = 0;
		
		direction = ElevatorDirection.UP;
		
		Comparator<Integer> comparator = new FloorComparator();
		floorQueue = new PriorityQueue<Integer>(maxFloors, comparator);
		
	}
	
	public long getID() {
		return id;
	}
	
	public void callElevator() {
		calls += 1;
	}
	
	public Integer getCalls() {
		return calls;
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	public int nextDestination() {
		return floorQueue.peek();
	}
	
	public void removeDestination() {
		floorQueue.poll();
	}
	
	//-----------------------------------------------------------------------
	
	public void ascend() {
		if (currentFloor < maxFloors) {
			this.ascendElevator(currentFloor + 1);
			currentFloor += 1;
		}
	}

	public void descend() {
		if (currentFloor > 0) {
			this.descendElevator(currentFloor - 1);
			currentFloor -= 1;
		}
	}

	public void registerNewDestination(int floor) {
		if (floorQueue.isEmpty()) {
			if (floor < currentFloor) {
				direction = ElevatorDirection.DOWN;
			} else if (floor > currentFloor) {
				direction = ElevatorDirection.UP;
			}
			floorQueue.add(floor);
			return;
		}
		if (direction == ElevatorDirection.UP && floor > currentFloor) {
			floorQueue.add(floor);
		} else if (direction == ElevatorDirection.DOWN && floor < currentFloor) {
			floorQueue.add(floor);
		}
	}

	public ElevatorDirection direction() {
		if (currentFloor == floorQueue.peek()) {
			direction = ElevatorDirection.IDLE;
		}
		return direction;
	}

	public ElevatorStatus status() {
		return (floorQueue.size() > 0) ? ElevatorStatus.FULL : ElevatorStatus.EMPTY;
	}
	
	public int compareTo(Elevator e) {
		return this.getCalls().compareTo(e.getCalls());
	}

	class FloorComparator implements Comparator<Integer> {

		public int compare(Integer x, Integer y) {
			if (direction == ElevatorDirection.UP) {
				if (x > y) {
					return 1;
				} else if (x < y) {
					return -1;
				}
			} else if (direction == ElevatorDirection.DOWN) {
				if (x < y) {
					return 1;
				} else if (x > y) {
					return -1;
				}
			}
			return 0;
		}
		
	}

}
