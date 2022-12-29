package domain.powerUps;

import dataStructures.Location;

public class HintPowerUp {

    Location location;
    String powerUpType;
    PowerUpBehavior behavior;

    public HintPowerUp(Location location, PowerUpBehavior behavior) {
        this.location = location;
        this.powerUpType = "Hint";
        this.behavior = behavior;
    }
}
