package Controllers;

import ApplicationLogic.ShooterAlienLogic;
import UI.GameController;
import UI.GameFrame;
import domain.Player;
import domain.aliens.ShooterAlien;

public class ShooterAlienHandler {
	ShooterAlienLogic shooterAlienLogic;
	Player player;
	GameController gameController;
	public ShooterAlienHandler(Player player, GameController gameController) {
		super();
		this.shooterAlienLogic = new ShooterAlienLogic(gameController,player);
		this.player = player;
		this.gameController = gameController;
	}
	
	public boolean shoot() {
		return shooterAlienLogic.shoot(player);
		
	}
	
	public void deactivate() {
		shooterAlienLogic.deactivate();
	}
	
	public ShooterAlien getShooterAlien() {
		return shooterAlienLogic.getShooterAlien();
	}
	
	
	

}
