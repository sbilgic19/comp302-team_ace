package domain.powerUps;

import dataStructures.Location;
import domain.Player;

public class ProtectionVestPowerUp {

    Location location;
    String powerUpType;
    PowerUpBehavior behavior;

    public ProtectionVestPowerUp(Location location, Player player) {
        this.location = location;
        this.powerUpType = "ProtectionVest";
        this.behavior = new WearVestBehavior(player);
    }
}
