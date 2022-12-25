package ApplicationLogic;

import java.util.ArrayList;
import java.util.Random;

import Controllers.RoomKeyHandler;
import dataStructures.Location;
import domain.Key;
import domain.RoomObject;

public class AlienBehaviourStrategyA implements AlienBehaviourStrategy {

	private ArrayList<RoomObject> objectList;
	private Key key;
	private Location doorLocation;
	
	public AlienBehaviourStrategyA(ArrayList<RoomObject> objectList, Key key, Location doorLocation) {
		this.objectList = objectList;
		this.key = key;
		this.doorLocation = doorLocation;
	}
	
	@Override
	public boolean changeLocationOfTheKey() {
			Random random = new Random();
			Location location;
			do {
				int randInt = random.nextInt(objectList.size());
				location = objectList.get(randInt).getLocation();
			} while (location.getLocationX() == doorLocation.getLocationX() && 
				location.getLocationY() == doorLocation.getLocationY());
			key.setLocation(location);
			System.out.printf("%d %d\n", location.getLocationX(), location.getLocationY());
			return false;
	}
}