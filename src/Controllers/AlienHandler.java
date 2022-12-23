package Controllers;

import ApplicationLogic.AlienLogic;
import UI.GameFrame;
import domain.Player;
import domain.aliens.*;

public class AlienHandler {
	AlienLogic ailenLogic;
	GameFrame gameFrame;
	Alien alien;
	Player player;
	public AlienHandler(AlienLogic ailenLogic, GameFrame gameFrame, Alien alien, Player player) {
		super();
		this.ailenLogic = ailenLogic;
		this.gameFrame = gameFrame;
		this.alien = alien;
		this.player = player;
	}
	
	
	
	public TimeWastingAlien getTimeWastingAlien() {
		return null;
		
	}
	
	
	
}
