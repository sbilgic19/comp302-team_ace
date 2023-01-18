package ApplicationLogic;

import java.util.ArrayList;
import java.util.Random;

import Controllers.TimeWastingAlienHandler;
import dataStructures.Location;
import domain.Key;
import domain.RoomObject;


public class TimeWastingAlienBehaviourStrategyA implements TimeWastingAlienBehaviourStrategy {

	private ArrayList<RoomObject> objectList;
	private Key key;
	boolean flag = true;
	private TimeWastingAlienLogic timeWastingAlienLogic;
	int counter = 0;
	
	@Override
	public boolean changeLocationOfTheKey() {
			Random random = new Random();
			Location location;
			
			if (flag) {
			do {
				int randInt = random.nextInt(objectList.size());
				location = objectList.get(randInt).getLocation();
			} while (location.getLocationX() == key.getLocation().getLocationX() && 
				location.getLocationY() == key.getLocation().getLocationY());
			key.setLocation(location);
			System.out.printf("%d %d\n", location.getLocationX(), location.getLocationY());
			flag = false;
			return true;
			}
			counter++;
			if(counter >= 2) {
				timeWastingAlienLogic.deactivate();
			}
			return false;
	}
	
	public void setFieldInstances(ArrayList<RoomObject> objectList, Key key,TimeWastingAlienLogic timeWastingAlienLogic) {
		this.objectList = objectList;
		this.key = key;
		this.timeWastingAlienLogic = timeWastingAlienLogic;
	}
}