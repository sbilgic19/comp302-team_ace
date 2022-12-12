package ApplicationLogic;

import java.util.Random;

import UI.GameFrame;
import UI.GamePanel;
import dataStructures.Location;
import domain.Key;
import domain.Player;

public class KeyLogic {
	GameFrame gameFrame;
	private final Random r = new Random();
	
	
	
	
	public KeyLogic(GameFrame gameFrame) {
		super();
		this.gameFrame = gameFrame;
	}

	public void addKey(Key k) {
		Location location = k.getLocation();
	}
	
	public boolean takeKey(Player p , Key k) {
		System.out.println("p.getLocation().getLocationX() :"+ p.getLocation().getLocationX());
		System.out.println("k.getLocation().getLocationX() :"+ k.getLocation().getLocationX());
		System.out.println("Math.abs(p.getLocation().getLocationX()- k.getLocation().getLocationX())  :" + Math.abs(p.getLocation().getLocationX()- k.getLocation().getLocationX()));
		System.out.println("Math.abs(p.getLocation().getLocationY()- k.getLocation().getLocationY())  :" + Math.abs(p.getLocation().getLocationY()- k.getLocation().getLocationY()));
		
		if ( Math.abs(p.getLocation().getLocationX()- k.getLocation().getLocationX()) <= 1 && Math.abs(p.getLocation().getLocationY()- k.getLocation().getLocationY()) <= 1 ) {
			k.taken(p);
			System.out.println("key taken");
			return true;
		}
		
		return false;
		
		
	}
	
	
	public Key getKey() {
		Key k = null;
		System.out.println("getKey called");
		int rowCount = gameFrame.getNumRow();
		int columnCount = gameFrame.getNumCol();
		Boolean flag = false;
		for (int i = 0; i < rowCount; i++) {
			  for ( int j = 0; j < columnCount ; j++) {
				  if (GamePanel.getGameMap()[i][j].getIcon() != null) {
					  k = new Key(new Location(i,j));
					  this.addKey(k);
					  flag = true;
					  break;
				  }
			  }
			  
			  if (flag) {
				  break;
			  }
			}
		
		
		
		
		return k;
	}
	

}