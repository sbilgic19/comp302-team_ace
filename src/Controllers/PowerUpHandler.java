package Controllers;


import ApplicationLogic.PowerUpLogic;
import UI.GameFrame;
import domain.Player;
import domain.powerUps.PowerUp;

public class PowerUpHandler {
	
	PowerUpLogic powerUpLogic;
	GameFrame gameFrame;
	
	
	
	public PowerUpHandler(GameFrame gameFrame, Player player) {
		powerUpLogic = new PowerUpLogic(gameFrame, player);
		this.gameFrame = gameFrame;
	}
	public PowerUp getRandomPowerUp() 
	{
		return powerUpLogic.getPowerUp();
	}

	public void usePowerUp(PowerUp powerUp)
	{
		powerUpLogic.usePowerUp(powerUp);
	}

	public GameFrame getGameFrame(){
		return this.gameFrame;
	}








}
