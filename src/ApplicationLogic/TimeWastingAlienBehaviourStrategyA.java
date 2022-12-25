package ApplicationLogic;

import java.util.ArrayList;
import java.util.Random;

import dataStructures.Location;
import domain.Key;
import domain.RoomObject;

public class TimeWastingAlienBehaviourStrategyA implements TimeWastingAlienBehaviourStrategy {

	private ArrayList<RoomObject> objectList;
	private Key key;
	
	@Override
	public boolean changeLocationOfTheKey() {
			Random random = new Random();
			Location location;
			do {
				int randInt = random.nextInt(objectList.size());
				location = objectList.get(randInt).getLocation();
			} while (location.getLocationX() == key.getLocation().getLocationX() && 
				location.getLocationY() == key.getLocation().getLocationY());
			key.setLocation(location);
			System.out.printf("%d %d\n", location.getLocationX(), location.getLocationY());
			return false;
	}
	
	public void setFieldInstances(ArrayList<RoomObject> objectList, Key key) {
		this.objectList = objectList;
		this.key = key;
	}
}