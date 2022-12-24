package ApplicationLogic;

import java.util.ArrayList;
import java.util.Random;

import UI.GameFrame;
import UI.GamePanel;
import dataStructures.Location;
import domain.Key;
import domain.aliens.*;

public class AlienLogic {
	GameFrame gameFrame;
	private final Random r = new Random();
	
	public AlienLogic(GameFrame gameFrame) {
		super();
		this.gameFrame = gameFrame;
	}
	
	public Boolean ChangeLocationOfKey(Alien alien, Key key) {
		
		return false;
		
	}
	
	
	public TimeWastingAlien getTimeWastingAlien() {
		Location doorLocation = gameFrame.getDoorLocation();
		int rowCount = gameFrame.getNumRow();
		int columnCount = gameFrame.getNumCol();
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
				  if (GamePanel.getGameMap()[i][j].getIcon() != null) {
					  if (i == 0 && j == 5) {
						  continue;
					  }
					  object_locations.add(new Location(i,j));
					  
				  }
			  }
			}
		
		int rand = r.nextInt(object_locations.size());
		TimeWastingAlien timeWastingAlien = new TimeWastingAlien(object_locations.get(rand));
		return timeWastingAlien;
		
	}
	
	
	
	
	
	
}
