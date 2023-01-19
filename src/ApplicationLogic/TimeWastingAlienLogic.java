package ApplicationLogic;

import java.util.ArrayList;
import java.util.Random;

import UI.GameController;

import UI.GameFrame;
import UI.GamePanel;
import UI.GameState;

import UI.GameTime;
import dataStructures.Location;
import domain.GameInfo;
import domain.Key;
import domain.RoomObject;
import domain.aliens.AlienFactory;
import domain.aliens.TimeWastingAlien;

public class TimeWastingAlienLogic {
	TimeWastingAlien timeWastingAlien;
	private GameController gameController;
	private final Random random = new Random();
	Key key;
	
	public TimeWastingAlienLogic(GameController gameController, Key key) {
		this.gameController = gameController;
		this.key = key;
	}
	
	
	public Boolean changeLocationOfKey(Key key) {
		if(!GameState.getInstance().isPaused()) {
			return determineBehaviourStrategy();
		}
		
		return false;
	}
	
	
private boolean determineBehaviourStrategy() {
		int currentTime = GameTime.getInstance().getSeconds();
		int levelTime = gameController.getGameFrame().getLevelTime();
		TimeWastingAlienBehaviourStrategy[] behaviours = timeWastingAlien.getBehaviours();

		if ((currentTime * 100) / (float) levelTime < 30 && !key.getIsTaken()) {
			System.out.println("A");
			return behaviours[0].changeLocationOfTheKey();
			
		}
		
		else if ((currentTime * 100)/ (float) levelTime > 70  && !key.getIsTaken()) {
			System.out.println("B");
			return behaviours[1].changeLocationOfTheKey();
		}
		
		else {
			System.out.println("C");
			return behaviours[2].changeLocationOfTheKey();
		}
}	
	
	
	public void placeTimeWastingAlien() {
		gameController.getGameFrame().getGamePanel().placeAlien(timeWastingAlien.getLocation(), timeWastingAlien.getAlienType());
	}
	
	
	public void deactivate() {
		if(timeWastingAlien != null) {
			if(timeWastingAlien.getIsActive()) {
				timeWastingAlien.setIsActive(false);
				gameController.getGameFrame().getGamePanel().setNullIcon(timeWastingAlien.getLocation());
			}
		}
	}
	
	
	
	public TimeWastingAlien getRandomTimeWastingAlien() {
		if(this.timeWastingAlien != null) {
			this.deactivate();
		}
		Location doorLocation = GameInfo.getInstance().getDoorLocation();
		int rowCount = gameController.getGameFrame().getNumRow();
		int columnCount = gameController.getGameFrame().getNumCol();
		Boolean flag = false;
		ArrayList<Location> object_locations = new ArrayList<>(); 
		for (int i = 0; i < rowCount; i++) {
			  for ( int j = 0; j < columnCount ; j++) {
				  if (i == 0 && j == 5) {
					  continue;
				  }
				  else if (i == doorLocation.getLocationX() && j == doorLocation.getLocationY()) {
					  continue;
				  }
				  if (gameController.getGameFrame().getGamePanel().getGameMap()[i][j].getIcon() == null) {
					  if (i == 0 && j == 5) {
						  continue;
					  }
					  object_locations.add(new Location(i,j));
					  
				  }
			  }
			}
		
		int rand = random.nextInt(object_locations.size());
		
		TimeWastingAlien timeWastingAlien = (TimeWastingAlien) AlienFactory.getInstance().getAlien("TimeWasting" , object_locations.get(rand));
		TimeWastingAlienBehaviourStrategy[] behaviours = timeWastingAlien.getBehaviours();
		behaviours[0].setFieldInstances(excludeDoorRoom(), key,this);
		behaviours[1].setFieldInstances(excludeDoorRoom(), key,this);
		behaviours[2].setFieldInstances(excludeDoorRoom(), key,this);
		setTimeWastingAlien(timeWastingAlien);
		placeTimeWastingAlien();
		return timeWastingAlien;
	}


	public void setTimeWastingAlien(TimeWastingAlien timeWastingAlien) {
		this.timeWastingAlien = timeWastingAlien;
	}
	public TimeWastingAlien getTimeWastingAlien() {
		return this.timeWastingAlien;
	}
	
	private ArrayList<RoomObject> excludeDoorRoom() {
		
		Location doorLocation = GameInfo.getInstance().getDoorLocation();
		ArrayList<RoomObject> objectList = GameInfo.getInstance().getListOfObjects();
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
