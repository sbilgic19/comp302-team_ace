package ApplicationLogic;

import java.util.ArrayList;

import dataStructures.Location;
import domain.Key;
import domain.RoomObject;

public interface TimeWastingAlienBehaviourStrategy {
	public boolean changeLocationOfTheKey();
	public void setFieldInstances(ArrayList<RoomObject> objectList, Key key);
}