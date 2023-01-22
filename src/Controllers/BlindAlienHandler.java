package Controllers;

import ApplicationLogic.BlindAlienLogic;
import UI.GameController;
import UI.GameFrame;
import dataStructures.Location;
import domain.Player;
import domain.aliens.BlindAlien;
import domain.aliens.ShooterAlien;

import java.io.Serializable;

public class BlindAlienHandler implements Serializable {
	BlindAlienLogic blindAlienLogic;
	Player player;
	GameController gameController;
	public BlindAlienHandler(Player player, GameController gameController) {
		super();
		this.player = player;
		this.blindAlienLogic = new BlindAlienLogic(gameController,player);
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

