package domain;


import domain.powerUps.PowerUp;

import java.io.Serializable;
import java.util.ArrayList;

import domain.aliens.Alien;

public class GameInfo implements Serializable{
    private static GameInfo instance;
    private Player player;
    private int time;
    private ArrayList<RoomObject> listOfObjects;
    ArrayList<Alien> listOfAlien = new ArrayList<Alien>();
    private PowerUp activePowerUp;


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
    
    public ArrayList<Alien> getAlienList(){
    	return listOfAlien;
    }
    
    public void addAlien(Alien a) {
    	listOfAlien.add(a);
    }
    
    public void removeAlien(Alien a) {
    	if(listOfAlien.contains(a)) {
    		listOfAlien.remove(a);
    	}
    }
    
    public void clearAlienList() {
    	listOfAlien.clear();
    }
    
    
    

    public ArrayList<RoomObject> getListOfObjects() {
        return listOfObjects;
    }

    public void setListOfObjects(ArrayList<RoomObject> listOfObjects) {
        this.listOfObjects = listOfObjects;
    }

    public PowerUp getActivePowerUp() {
        return activePowerUp;
    }

    public void setActivePowerUp(PowerUp powerUp){
        this.activePowerUp = powerUp;
    }
}
