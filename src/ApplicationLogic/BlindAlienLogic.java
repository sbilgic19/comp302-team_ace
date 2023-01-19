package ApplicationLogic;

import java.util.ArrayList;
import java.util.Random;

import UI.GameController;
import dataStructures.Location;
import domain.GameInfo;
import domain.Player;
import domain.aliens.AlienFactory;
import domain.aliens.BlindAlien;

public class BlindAlienLogic {
	BlindAlien blindAlien;
	private GameController gameController;
	private final Random random = new Random();
	
	public BlindAlienLogic(GameController gameController, Player player) {
		this.gameController = gameController;
	}
	
	
	
	
	
	public Boolean shoot(Player player) {
		
		if(blindAlien.getIsActive()) {
			if(Location.distance(player.getLocation(), blindAlien.getLocation()) <= 1) {
				System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
				System.out.println("isProtected: "+ player.isProtected());

					player.decreaseLives();
					gameController.getGameFrame().updatePlayerLivesView(player.getLives());
					return true;
			
				
			}
		}
		
		
		return false;
	}
	
	
	public boolean move() {
		if (this.blindAlien != null) {
			int randomMove = new Random().nextInt(4);
			
			switch (randomMove){
			case 0:
				return moveable(0,1);
			case 1:
				return moveable(1,0);
			case 2:
				return moveable(-1,0);
			case 3:
				return moveable(0,-1);
				
			}
		
		}	
		return false;
	}
	
	
	public boolean moveable(int changeInX, int changeInY) {
		if ( Math.abs(changeInX) +  Math.abs(changeInY) <= 1) {
			int xPosition = blindAlien.getLocation().getLocationX();
			int yPosition = blindAlien.getLocation().getLocationY();

			int newXPosition = xPosition + changeInX;
			int newYPosition = yPosition + changeInY;
			
			if (newXPosition >= 0 && newXPosition < gameController.getGameFrame().getNumRow() && newYPosition >= 0 
						&& newYPosition < gameController.getGameFrame().getNumCol()) {  
				if(gameController.getGameFrame().getGamePanel().getGameMap()[newXPosition][newYPosition].getIcon() == null) {
					gameController.getGameFrame().getGamePanel().updateBlindAlienView(xPosition, yPosition,
							newXPosition, newYPosition);
					blindAlien.getLocation().setLocationX(newXPosition);
					blindAlien.getLocation().setLocationY(newYPosition);
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void placeBlindAlien() {
		gameController.getGameFrame().getGamePanel().placeAlien(blindAlien.getLocation(), blindAlien.getAlienType());
	}
	
	
	public void deactivate() {
		blindAlien.setIsActive(false);
		gameController.getGameFrame().getGamePanel().setNullIcon(blindAlien.getLocation());
	}
	
	
	
	public BlindAlien getBlindAlien() {
		if(this.blindAlien != null) {
			this.deactivate();
		}
		Location doorLocation = GameInfo.getInstance().getDoorLocation();
		int rowCount = gameController.getGameFrame().getNumRow();
		int columnCount = gameController.getGameFrame().getNumCol();
		Boolean flag = false;
		ArrayList<Location> object_locations = new ArrayList<>(); 
		for (int i = 0; i < rowCount; i++) {
			  for ( int j = 0; j < columnCount ; j++) {
				  if (i == 0 && j == 5) {
					  continue;
				  }
				  else if (i == doorLocation.getLocationX() && j == doorLocation.getLocationY()) {
					  continue;
				  }
				  if (gameController.getGameFrame().getGamePanel().getGameMap()[i][j].getIcon() == null) {
					  if (i == 0 && j == 5) {
						  continue;
					  }
					  object_locations.add(new Location(i,j));
					  
				  }
			  }
			}
		
		int rand = random.nextInt(object_locations.size());
		
		BlindAlien blindAlien = (BlindAlien) AlienFactory.getInstance().getAlien("Blind" , object_locations.get(rand));
		setBlindAlien(blindAlien);
		placeBlindAlien();
		return blindAlien;
	}


	public void setBlindAlien(BlindAlien blindAlien) {
		this.blindAlien = blindAlien;
	}


	
	
	

}

