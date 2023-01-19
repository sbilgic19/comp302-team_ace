package Controllers;

import ApplicationLogic.BlindAlienLogic;
import UI.GameController;
import UI.GameFrame;
import dataStructures.Location;
import domain.Player;
import domain.aliens.BlindAlien;
import domain.aliens.ShooterAlien;

public class BlindAlienHandler {
	BlindAlienLogic blindAlienLogic;
	Player player;
	GameController gameController;
	public BlindAlienHandler(Player player, GameController gameController) {
		super();
		this.blindAlienLogic = new BlindAlienLogic(gameController,player);
		this.player = player;
		this.gameController = gameController;
	}
	
	public boolean shoot() {
		return blindAlienLogic.shoot(player);
		
	}
	
	public void deactivate() {
		blindAlienLogic.deactivate();
	}
	
	
	public boolean move(Location loc) {
		return blindAlienLogic.move(loc);
	}
	
	public BlindAlien getBlindAlien() {
		return blindAlienLogic.getBlindAlien();
	}
	
	
	

}

