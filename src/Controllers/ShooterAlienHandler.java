package Controllers;

import ApplicationLogic.ShooterAlienLogic;
import UI.GameFrame;
import domain.Player;
import domain.aliens.ShooterAlien;

public class ShooterAlienHandler {
	ShooterAlienLogic shooterAlienLogic;
	Player player;
	GameFrame gameFrame;
	public ShooterAlienHandler(Player player, GameFrame gameFrame) {
		super();
		this.shooterAlienLogic = new ShooterAlienLogic(gameFrame,player);
		this.player = player;
		this.gameFrame = gameFrame;
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
