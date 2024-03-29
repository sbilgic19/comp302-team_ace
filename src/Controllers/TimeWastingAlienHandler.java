package Controllers;

import ApplicationLogic.TimeWastingAlienLogic;
import UI.BuildMode;
import UI.GameController;
import UI.GameFrame;
import domain.Key;
import domain.aliens.*;

import java.io.Serializable;

public class TimeWastingAlienHandler implements Serializable {
	TimeWastingAlienLogic alienLogic;
	BuildMode buildMode;
	GameController gameController;
	Key key;
	public TimeWastingAlienHandler(GameController gameController, Key key) {
		super();
		this.alienLogic = new TimeWastingAlienLogic(gameController, key);
		this.gameController = gameController;
		this.key = key;

	}
	
	public TimeWastingAlienHandler(GameController gameController) {
		this.alienLogic = new TimeWastingAlienLogic(gameController, key);
		this.gameController = gameController;
		
	}
	
	public TimeWastingAlien getTimeWastingAlien() {
		return alienLogic.getRandomTimeWastingAlien();
	}
	
	public void deactivate() {
		alienLogic.deactivate();
	}
	public boolean changeLocationOfKey() {
		return alienLogic.changeLocationOfKey(key);
	}
	
	public void placeTimeWastingAlien() {
		alienLogic.placeTimeWastingAlien();
	}

	public TimeWastingAlienLogic getAlienLogic() {
		return alienLogic;
	}
	
	
}