package domain;


import dataStructures.Location;
import domain.powerUps.PowerUp;


import java.io.Serializable;
import java.util.ArrayList;

import domain.aliens.Alien;

public class GameInfo implements Serializable{
    private static GameInfo instance;
    private Player player;
    private Location playerLocation;
    private int time = 0;
    private ArrayList<Location> doorLocationList = new ArrayList<>();
    private boolean isLoaded= false;
    private ArrayList<ArrayList<RoomObject>> listOfObjectsOfAllLevels = new ArrayList<>();
    private Alien activeAlien;
    private PowerUp activePowerUp;
    private ArrayList<Key> keyList = new ArrayList<>();
    private int currentLevel = 1;


    private GameInfo(){}

    public static GameInfo getInstance(){
        if(instance == null){
            instance = new GameInfo();
        }

        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public Location getDoorLocation(){return doorLocationList.get(currentLevel - 1);}
    public ArrayList<Location> getDoorLocationList(){return doorLocationList;}
    public void setDoorLocationList(ArrayList<Location> doorList) { this.doorLocationList = doorList;}
    public void addDoorLocation(Location location) {
        if(doorLocationList.size() >= currentLevel) {
            this.doorLocationList.set(currentLevel - 1, location);
        } else {
            this.doorLocationList.add(location);
        }
    }
    public Location getPlayerLocation(){return playerLocation; }
    public void setPlayerLocation(Location location){this.playerLocation = location; }

    public PowerUp getActivePowerUp() {
        return activePowerUp;
    }

    public void setActivePowerUp(PowerUp powerUp){
        this.activePowerUp = powerUp;
    }
    public void setKeyList(ArrayList<Key> keyList){this.keyList = keyList;}
    public void addKey(Key k){
        if(keyList.size() >= currentLevel) {
            this.keyList.set(currentLevel - 1, k);
        } else {
            this.keyList.add(k);
        }
    }
    public Key getKey(){
        if(keyList.size() >= currentLevel) {
             return this.keyList.get(currentLevel - 1);
        } else {
            return null;
        }
    }
    public ArrayList<Key> getKeyList(){return this.keyList;}
    public void setCurrentLevel(int level){this.currentLevel = level;}
    public int getCurrentLevel(){return this.currentLevel;}
    public void levelUp(){this.currentLevel = this.currentLevel + 1;}

    public void setListOfObjectsOfAllLevels(ArrayList<ArrayList<RoomObject>> listOfObjectsOfAllLevels) {
        this.listOfObjectsOfAllLevels = listOfObjectsOfAllLevels;
    }
    public void addObjectList(ArrayList<RoomObject> objectList) {
        if(listOfObjectsOfAllLevels.size() >= currentLevel) {
            this.listOfObjectsOfAllLevels.set(currentLevel - 1, objectList);
        } else {
            this.listOfObjectsOfAllLevels.add(objectList);
        }
    }

    public ArrayList<ArrayList<RoomObject>> getListOfObjectsOfAllLevels() {
        return listOfObjectsOfAllLevels;
    }
    public ArrayList<RoomObject> getCurrentObjects(){
        return this.listOfObjectsOfAllLevels.get(currentLevel - 1);
    }
    public void setActiveAlien(Alien alien){ this.activeAlien= alien;}
    public Alien getActiveAlien(){return activeAlien; }

    public boolean getIsLoaded(){
        return isLoaded;
    }
    public void setIsLoaded(Boolean load) {
        this.isLoaded = load;
    }
}
