package domain.powerUps;

import dataStructures.Location;
import domain.Player;

public class PlasticBottlePowerUp implements PowerUp{

    Location location;
    Player player;
    String powerUpType;
    String movementDirection;
    PowerUpBehavior behavior;
    int bottleIconPosition = 0;

    public PlasticBottlePowerUp(Location location, Player player, String movementDirection) {
        this.location = location;
        this.player = player;
        this.powerUpType = "PlasticBottle";
        this.movementDirection = movementDirection;
        Location location1 = new Location(player.getLocation().getLocationX(), player.getLocation().getLocationY());
        this.behavior = new PlasticBottleThrowBehavior(location1, movementDirection);
    }

    @Override
    public void triggerEffect() {
        behavior.performBehavior();
        this.location = ((PlasticBottleThrowBehavior)behavior).getNewLocation();
    }

    @Override
    public String getPowerUpType() {
        return powerUpType;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public String getMovementDirection(){
        return movementDirection;
    }

    public int getBottleIconPosition(){return this.bottleIconPosition; }
    public void setBottleIconPosition(int bottleIconPosition){
        this.bottleIconPosition = bottleIconPosition;
    }
}
