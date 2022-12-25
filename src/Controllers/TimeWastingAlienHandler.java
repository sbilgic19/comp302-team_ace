package Controllers;

import ApplicationLogic.TimeWastingAlienLogic;
import UI.BuildMode;
import UI.GameFrame;
import domain.Key;
import domain.aliens.*;

public class TimeWastingAlienHandler {
	TimeWastingAlienLogic alienLogic;
	BuildMode buildMode;
	GameFrame gameFrame;
	Key key;
	public TimeWastingAlienHandler(GameFrame gameFrame, Key key) {
		super();
		this.alienLogic = new TimeWastingAlienLogic(gameFrame, key);
		this.gameFrame = gameFrame;
		this.key = key;

	}
	
	public TimeWastingAlienHandler(GameFrame gameFrame) {
		this.alienLogic = new TimeWastingAlienLogic(gameFrame, key);
		this.gameFrame = gameFrame;
		
	}
	
	public TimeWastingAlien getTimeWastingAlien() {
		return alienLogic.getTimeWastingAlien();
	}	
}