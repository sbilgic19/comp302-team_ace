package domain.powerUps;

import dataStructures.Location;

public class PowerUpFactory {
	private static PowerUpFactory factory;
	
	/** 
	 * Singleton pattern used to create PowerUpFactory.
	 * 
	 * @return the PowerUpFactory
	 */
    public static PowerUpFactory getInstance() 
    {
        if (factory == null) {
            factory = new PowerUpFactory();
        }
        return factory;
    }
    
    public PowerUp getPowerUp(String spellType, Location location)
    {
    	switch (spellType)
    	{
    		case "ExtraLife":
    			return new ExtraLifePowerUp(location);
    			
    		default:
    			return null;
    	}
    }
    
}
