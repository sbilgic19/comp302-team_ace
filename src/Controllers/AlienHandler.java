package Controllers;

import ApplicationLogic.AlienLogic;
import UI.GameFrame;
import domain.Key;
import domain.aliens.*;

public class AlienHandler {
	AlienLogic alienLogic;
	GameFrame gameFrame;
	Key key;
	public AlienHandler(GameFrame gameFrame,Key key) {
		super();
		this.alienLogic = new AlienLogic(gameFrame);
		this.gameFrame = gameFrame;
		this.key = key;

	}
	
	
	public boolean ChangeLocationOfKey(Alien alien, Key key) {
		return alienLogic.ChangeLocationOfKey(alien , key);
		
	}
	
	
	public TimeWastingAlien getTimeWastingAlien() {
		return alienLogic.getTimeWastingAlien();
		
	}
	
	
	
}
