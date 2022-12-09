package Controllers;

import java.util.Random;

import dataStructures.Location;
import domain.powerUps.PowerUp;
import domain.powerUps.PowerUpFactory;

public class PowerUpHandler {
	
	private static PowerUpHandler onlyInstance;
	private final Random r = new Random();
	
	private PowerUpHandler() {}
	
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
		
		switch (rand) 
		{
			case 0:
				powerUp = PowerUpFactory.getInstance().getPowerUp("ExtraLife", location);
                break;
			case 1:
				powerUp = PowerUpFactory.getInstance().getPowerUp("ExtraLife", location);
                break;
            default:
                powerUp = null;
		}
		return powerUp;
	}
	
	
}
