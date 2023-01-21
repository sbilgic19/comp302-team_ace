package ApplicationLogic;

import java.util.ArrayList;

import Controllers.TimeWastingAlienHandler;
import dataStructures.Location;
import domain.Key;
import domain.RoomObject;

public interface TimeWastingAlienBehaviourStrategy {
	boolean changeLocationOfTheKey();
	void setFieldInstances(ArrayList<RoomObject> objectList, Key key, TimeWastingAlienLogic timeWastingAlienLogic);
}