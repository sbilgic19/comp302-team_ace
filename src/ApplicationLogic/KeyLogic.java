package ApplicationLogic;

import java.util.ArrayList;


import java.util.Random;

import UI.GameController;
import dataStructures.Location;
import domain.GameInfo;
import domain.Key;
import domain.Player;

public class KeyLogic {
	
	/*
The KeyLogic class handles the logic for adding, taking, and generating keys in the game. 
It takes in a GameFrame object in its constructor and uses it to access information about the game 
such as the door location and the number of rows and columns in the game map.

The class has three public methods:

addKey(Key k): adds the given Key object to the game.

takeKey(Player p, Key k): attempts to have the given Player object take the given Key object. 
It returns true if the Player is within one unit of the Key and the Key is successfully taken, and false otherwise.

getKey(): generates and returns a new Key object with a randomly chosen location in the game map, 
avoiding locations occupied by other objects and the door location.
	 */
	GameController gameController;
	private final Random r = new Random();
	
	public KeyLogic(GameController gameController) {
		super();
		this.gameController = gameController;
	}

	
	public boolean takeKey(Player p , Key k) {

		if ( Math.abs(p.getLocation().getLocationX()- k.getLocation().getLocationX()) <= 1 && Math.abs(p.getLocation().getLocationY()- k.getLocation().getLocationY()) <= 1 && !k.getIsTaken() ) {
			k.taken(p);
			System.out.println("key taken");
			return true;
		}
		return false;
	}
	
	
	public Key getKey() {
		Key k = null;
		//System.out.println("getKey called");
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
				  if (gameController.getGameFrame().getGamePanel().getGameMap()[i][j].getIcon() != null) {
					  if (i == 0 && j == 5) {
						  continue;
					  }
					  object_locations.add(new Location(i,j));
					  
				  }
			  }
			}
		
		int rand = r.nextInt(object_locations.size());
		
		k = new Key(object_locations.get(rand));
		GameInfo.getInstance().addKey(k);
		return k;
	}
}