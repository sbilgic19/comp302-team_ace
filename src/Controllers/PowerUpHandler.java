package Controllers;

import java.util.Random;

import ApplicationLogic.PlayerMoveLogic;
import ApplicationLogic.PowerUpLogic;
import dataStructures.Location;
import domain.powerUps.PowerUp;
import domain.powerUps.PowerUpFactory;

public class PowerUpHandler {
	
	private static PowerUpHandler onlyInstance;
	PowerUpLogic powerUpLogic;
	
	private final Random r = new Random();
	
	private PowerUpHandler() {
		powerUpLogic = new PowerUpLogic();
	}
	
	public static PowerUpHandler getInstance() {
		if (onlyInstance == null) 
		{
			onlyInstance = new PowerUpHandler();
		} 
		return onlyInstance;
	}
	
	public void placePowerUp() 
	{
		
	}
	
	public PowerUp getRandomPowerUp() 
	{
		int rand = r.nextInt(1);
		PowerUp powerUp;
		
		//It will be random Location.
		Location location = new Location(10,10);
		powerUp = PowerUpFactory.getInstance().getPowerUp("ExtraLife", location);
		powerUpLogic.addPowerUp(location, "ExtraLife");
		
		/*switch (rand) 
		{
			case 0:
				
				powerUp = PowerUpFactory.getInstance().getPowerUp("ExtraLife", location);
				powerUpLogic.addPowerUp(location, "ExtraLife");
                break;
			case 1:
				powerUp = PowerUpFactory.getInstance().getPowerUp("ExtraLife", location);
				powerUpLogic.addPowerUp(location, "ExtraLife");
                break;
            default:
                powerUp = null;
		}*/
		return powerUp;
	}
	
	 
	
	
}
