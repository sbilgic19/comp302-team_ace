package ApplicationLogic;

import java.util.ArrayList;
import java.util.Random;

import dataStructures.Location;
import domain.Key;
import domain.RoomObject;

public class TimeWastingAlienBehaviourStrategyB implements TimeWastingAlienBehaviourStrategy {

	private ArrayList<RoomObject> objectList;
	private Key key;
	private int counter = 0;

	@Override
	public boolean changeLocationOfTheKey() {
		counter++;
		if (counter == 3) {
			Random random = new Random();
			Location location;
			do {
				int randInt = random.nextInt(objectList.size());
				location = objectList.get(randInt).getLocation();
			} while (location.getLocationX() == key.getLocation().getLocationX() && 
					location.getLocationY() == key.getLocation().getLocationY());
			key.setLocation(location);
			System.out.printf("%d %d\n", location.getLocationX(), location.getLocationY());
			counter = 0;
		}
		return true;
	}
	
	public void setFieldInstances(ArrayList<RoomObject> objectList, Key key) {
		this.objectList = objectList;
		this.key = key;
	}
}