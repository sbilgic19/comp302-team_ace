package domain.powerUps;

import dataStructures.Location;

public class PlasticBottlePowerUp {

    Location location;
    String powerUpType;
    PowerUpBehavior behavior;

    public PlasticBottlePowerUp(Location location, PowerUpBehavior behavior) {
        this.location = location;
        this.powerUpType = "PlasticBottle";
        this.behavior = behavior;
    }
}
