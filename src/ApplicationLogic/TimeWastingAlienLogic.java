package ApplicationLogic;
import java.util.ArrayList;
import java.util.Random;
import UI.GameFrame;
import UI.GamePanel;
import dataStructures.Location;
import domain.Key;
import domain.RoomObject;
import domain.aliens.*;

public class TimeWastingAlienLogic {
	
	private GameFrame gameFrame;
	private Key key;
	private Random random = new Random();
	
	
	public TimeWastingAlienLogic(GameFrame gameFrame, Key key) {
		this.gameFrame = gameFrame;
		this.key = key;
	}

	public TimeWastingAlien getTimeWastingAlien() {
		
		int numRow = gameFrame.getNumRow();
		int numCol = gameFrame.getNumCol();
		
		int randRow;
		int randCol;
		
		while (true) {
			randRow = random.nextInt(numRow);
			randCol = random.nextInt(numCol);
			if (GamePanel.getGameMap()[randRow][randCol].getIcon() == null) {
				break;
			}
		}
		GamePanel.getGameMap()[randRow][randCol].setIcon(GamePanel.getTimeWastingAlienIcon());
		TimeWastingAlien timeWastingAlien = new TimeWastingAlien(new Location(randRow, randCol));
		TimeWastingAlienBehaviourStrategy[] behavioursArray = timeWastingAlien.getBehaviours();
		
		behavioursArray[0].setFieldInstances(excludeDoorRoom(), key);
		behavioursArray[1].setFieldInstances(excludeDoorRoom(), key);
		
		return timeWastingAlien;	
	}
	
	private ArrayList<RoomObject> excludeDoorRoom() {
		
		Location doorLocation = gameFrame.getDoorLocation();
		ArrayList<RoomObject> objectList = gameFrame.getObjectList();
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