package domain.powerUps;

import dataStructures.Location;
import domain.Key;
import domain.Player;

public class HintPowerUp implements PowerUp{

    Location location;
    String powerUpType;
    PowerUpBehavior behavior;
    final int duration = 10;


    /**
     * Constructor of Hint PowerUp.
     * @param location location of the powerUp in the map.
     */
    public HintPowerUp(Location location, Player player, Key key) {
        this.location = location;
        this.powerUpType = "Hint";
        this.behavior = new ShowHintBehavior(player, key);
    }

    /**
     * Triggers the effect of the powerUp.
     */
    @Override
    public void triggerEffect() {
        behavior.performBehavior();
    }

    /**
     * Getter for the type of the PowerUp.
     * @return type of the powerUp
     */
    @Override
    public String getPowerUpType() {
        return this.powerUpType;
    }

    /**
     * Getter for the Location of the powerUp.
     * @return the Location of the PowerUp
     */
    @Override
    public Location getLocation() {
        return this.location;
    }

    public int getDuration() {
        return duration;
    }
}
