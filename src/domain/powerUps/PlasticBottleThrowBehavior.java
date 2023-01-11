package domain.powerUps;

import dataStructures.Location;

public class PlasticBottleThrowBehavior implements PowerUpBehavior{
    Location plasticBottleLocation;
    String movementDirection;
    public PlasticBottleThrowBehavior(Location location, String movementDirection){
        this.plasticBottleLocation = location;
        this.movementDirection = movementDirection;
    }
    @Override
    public void performBehavior() {
        if(movementDirection.equalsIgnoreCase("East")){
            plasticBottleLocation.setLocationY(plasticBottleLocation.getLocationY()+1);
        }else if(movementDirection.equalsIgnoreCase("West")){
            plasticBottleLocation.setLocationY(plasticBottleLocation.getLocationY()-1);
        }else if(movementDirection.equalsIgnoreCase("North")){
            plasticBottleLocation.setLocationX(plasticBottleLocation.getLocationX()-1);
        }else if(movementDirection.equalsIgnoreCase("South")){
            plasticBottleLocation.setLocationX(plasticBottleLocation.getLocationX()+1);
        }
    }
}
