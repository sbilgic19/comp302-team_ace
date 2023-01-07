package domain.powerUps;

import dataStructures.Location;
import domain.Player;

public class ProtectionVestPowerUp implements PowerUp{

    Location location;
    String powerUpType;
    PowerUpBehavior behavior;

    public ProtectionVestPowerUp(Location location, Player player) {
        this.location = location;
        this.powerUpType = "ProtectionVest";
        this.behavior = new WearVestBehavior(player);
    }

    @Override
    public void triggerEffect() {
        behavior.performBehavior();
    }

    @Override
    public String getPowerUpType() {
        return "ProtectionVest";
    }

    @Override
    public Location getLocation() {
        return this.location;
    }
}
