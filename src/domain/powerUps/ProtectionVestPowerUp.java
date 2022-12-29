package domain.powerUps;

import dataStructures.Location;

public class ProtectionVestPowerUp {

    Location location;
    String powerUpType;
    PowerUpBehavior behavior;

    public ProtectionVestPowerUp(Location location, PowerUpBehavior behavior) {
        this.location = location;
        this.powerUpType = "ProtectionVest";
        this.behavior = behavior;
    }
}
