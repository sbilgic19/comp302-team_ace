package Controllers;


import ApplicationLogic.PowerUpLogic;
import UI.GameController;
import UI.GameFrame;
import domain.Player;
import domain.powerUps.PowerUp;

public class PowerUpHandler {
	
	PowerUpLogic powerUpLogic;
	GameController gameController;
	
	
	
	public PowerUpHandler(GameController gameController, Player player) {
		powerUpLogic = new PowerUpLogic(gameController, player);
		this.gameController = gameController;
	}
	public PowerUp getRandomPowerUp() 
	{
		return powerUpLogic.getPowerUp();
	}

	public void usePowerUp(PowerUp powerUp)
	{
		powerUpLogic.usePowerUp(powerUp);
	}

	public GameController getGameController(){
		return this.gameController;
	}








}
