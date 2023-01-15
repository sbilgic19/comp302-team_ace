package Controllers;

import ApplicationLogic.BlindAlienLogic;
import UI.GameFrame;
import domain.Player;
import domain.aliens.BlindAlien;
import domain.aliens.ShooterAlien;

public class BlindAlienHandler {
	BlindAlienLogic blindAlienLogic;
	Player player;
	GameFrame gameFrame;
	public BlindAlienHandler(Player player, GameFrame gameFrame) {
		super();
		this.blindAlienLogic = new BlindAlienLogic(gameFrame,player);
		this.player = player;
		this.gameFrame = gameFrame;
	}
	
	public boolean shoot() {
		return blindAlienLogic.shoot(player);
		
	}
	
	public void deactivate() {
		blindAlienLogic.deactivate();
	}
	
	
	public boolean move() {
		return blindAlienLogic.move();
	}
	
	public BlindAlien getBlindAlien() {
		return blindAlienLogic.getBlindAlien();
	}
	
	
	

}

