package ApplicationLogic;
import java.util.ArrayList;
import java.util.Random;

import UI.GameController;
import UI.GameFrame;
import UI.GamePanel;
import dataStructures.Location;
import domain.Key;
import domain.RoomObject;
import domain.aliens.*;

public class TimeWastingAlienLogic {
	
	private GameController gameController;
	private Key key;
	private Random random = new Random();
	
	
	public TimeWastingAlienLogic(GameController gameController, Key key) {
		this.gameController = gameController;
		this.key = key;
	}

	public TimeWastingAlien getTimeWastingAlien() {
		
		int numRow = gameController.getGameFrame().getNumRow();
		int numCol = gameController.getGameFrame().getNumCol();
		
		int randRow;
		int randCol;
		
		while (true) {
			randRow = random.nextInt(numRow);
			randCol = random.nextInt(numCol);
			if (gameController.getGameFrame().getGamePanel().getGameMap()[randRow][randCol].getIcon() == null) {
				break;
			}
		}
		gameController.getGameFrame().getGamePanel().getGameMap()[randRow][randCol].setIcon(gameController.getGameFrame().getGamePanel().getTimeWastingAlienIcon());
		TimeWastingAlien timeWastingAlien = new TimeWastingAlien(new Location(randRow, randCol));
		TimeWastingAlienBehaviourStrategy[] behavioursArray = timeWastingAlien.getBehaviours();
		
		behavioursArray[0].setFieldInstances(excludeDoorRoom(), key);
		behavioursArray[1].setFieldInstances(excludeDoorRoom(), key);
		
		return timeWastingAlien;	
	}
	
	private ArrayList<RoomObject> excludeDoorRoom() {
		
		Location doorLocation = gameController.getGameFrame().getDoorLocation();
		ArrayList<RoomObject> objectList = gameController.getGameFrame().getObjectList();
		for (int ii = 0; ii < objectList.size(); ii++) {
			Location tempLocation = objectList.get(ii).getLocation();
			if (tempLocation.getLocationX() == doorLocation.getLocationX() 
					&& tempLocation.getLocationY() == tempLocation.getLocationY()) {
				objectList.remove(ii);
			}
		}	
		return objectList;
	}
}