package domain;


import dataStructures.Location;

import java.io.Serializable;
import java.util.ArrayList;

public class GameInfo implements Serializable{
    private static GameInfo instance;
    private Player player;
    private Location playerLocation;
    private int time;
    private Location doorLocation;
    private ArrayList<RoomObject> listOfObjects;

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

    public ArrayList<RoomObject> getListOfObjects() {
        return listOfObjects;
    }

    public void setListOfObjects(ArrayList<RoomObject> listOfObjects) {
        this.listOfObjects = listOfObjects;
    }
    public Location getDoorLocation(){ return doorLocation; }
    public void setDoorLocation(Location location){ this.doorLocation = location; }
    public Location getPlayerLocation(){return playerLocation; }
    public void setPlayerLocation(Location location){this.playerLocation = location; }
}
