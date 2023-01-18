package ApplicationLogic;

import java.util.ArrayList;

import Controllers.TimeWastingAlienHandler;
import dataStructures.Location;
import domain.Key;
import domain.RoomObject;
import domain.aliens.TimeWastingAlien;

public class TimeWastingAlienBehaviourStrategyC implements TimeWastingAlienBehaviourStrategy {

	private int totalTime = 0;;
	private TimeWastingAlienLogic timeWastingAlienLogic;
	

	@Override
	public boolean changeLocationOfTheKey() {
		totalTime++;
		if (totalTime >= 2) {
			timeWastingAlienLogic.deactivate();
		}
		return false;
	}
	
	@Override
	public void setFieldInstances(ArrayList<RoomObject> objectList, Key key, TimeWastingAlienLogic timeWastingAlienLogic) {
		this.timeWastingAlienLogic = timeWastingAlienLogic;
	}
}