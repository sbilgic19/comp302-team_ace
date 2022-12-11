package Controllers;


import ApplicationLogic.PowerUpLogic;
import UI.GameFrame;
import domain.powerUps.PowerUp;

public class PowerUpHandler {
	
	PowerUpLogic powerUpLogic;
	GameFrame gameFrame;
	
	
	
	public PowerUpHandler(GameFrame gameFrame) {
		powerUpLogic = new PowerUpLogic(gameFrame);
		this.gameFrame = gameFrame;
	}
	
	public PowerUp getRandomPowerUp() 
	{
		return powerUpLogic.getPowerUp();
	}
	
	
	 
	
	
}
