package ApplicationLogic;

import java.util.ArrayList;

import dataStructures.Location;
import domain.Key;
import domain.RoomObject;

public class TimeWastingAlienBehaviourStrategyC implements TimeWastingAlienBehaviourStrategy {

	private int totalTime = 0;;
	@Override
	public boolean changeLocationOfTheKey() {
		totalTime++;
		if (totalTime == 2) {
			return false;
		}
		return true;
	}
	@Override
	public void setFieldInstances(ArrayList<RoomObject> objectList, Key key) {
		return;
	}
}