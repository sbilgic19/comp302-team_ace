package domain.powerUps;

import dataStructures.Location;
import domain.Key;
import domain.Player;

public class PowerUpFactory {
	private static PowerUpFactory factory;
	private Player player;
	private Key key;
	
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
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setKey(Key key) {this.key = key;}
    
    public PowerUp getPowerUp(String spellType, Location location)
    {
    	switch (spellType)
    	{
			case "ExtraLife":
				return new ExtraLifePowerUp(location, player);
			case "ExtraTime":
				return new ExtraTimePowerUp(location);
			case "ProtectionVest":
				return new ProtectionVestPowerUp(location, player);
			case "Hint":
				return new HintPowerUp(location, player, key);
			case "PlasticBottle":
				return new PlasticBottlePowerUp(location,player,null);
    		default:
    			return null;
    	}
    }
    
}
