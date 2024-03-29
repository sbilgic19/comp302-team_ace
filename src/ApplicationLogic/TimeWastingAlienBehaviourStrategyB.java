package ApplicationLogic;

import java.util.ArrayList;
import java.util.Random;


import dataStructures.Location;
import domain.GameInfo;
import domain.Key;
import domain.RoomObject;


public class TimeWastingAlienBehaviourStrategyB implements TimeWastingAlienBehaviourStrategy {

	private ArrayList<RoomObject> objectList;
	private Key key;
	private int counter = 0;
	private TimeWastingAlienLogic timeWastingAlienLogic;
	


	@Override
	public boolean changeLocationOfTheKey() {
		if(timeWastingAlienLogic.getTimeWastingAlien().getIsActive()) {
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
				GameInfo.getInstance().getKey().setLocation(location);
				System.out.printf("%d %d\n", location.getLocationX(), location.getLocationY());
				counter = 0;
			}
		}
		
		return true;
	}
	
	public void setFieldInstances(ArrayList<RoomObject> objectList, Key key, TimeWastingAlienLogic timeWastingAlienLogic) {
		this.objectList = objectList;
		this.key = key;
		this.timeWastingAlienLogic = timeWastingAlienLogic;
	}
}