package ApplicationLogic;

import java.util.ArrayList;
import java.util.Random;

import UI.GameController;
import UI.GameFrame;
import UI.GamePanel;
import UI.GameState;
import dataStructures.Location;
import domain.Player;
import domain.aliens.AlienFactory;
import domain.aliens.ShooterAlien;
import domain.aliens.TimeWastingAlien;

public class ShooterAlienLogic {
	ShooterAlien shooterAlien;
	private GameController gameController;
	private final Random random = new Random();
	
	public ShooterAlienLogic(GameController gameController, Player player) {
		this.gameController = gameController;
	}
	
	
	public Boolean shoot(Player player) {
		
		if(shooterAlien.getIsActive() && !GameState.getInstance().isPaused()) {
			if(Location.distance(player.getLocation(), shooterAlien.getLocation()) <= 3) {
				//System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
				//System.out.println("isProtected: "+ player.isProtected());
				if(!player.isProtected()) {
					player.decreaseLives();
					gameController.getGameFrame().updatePlayerLivesView(player.getLives());
					return true;
				}
				
			}
		}
		
		
		return false;
	}
	
	
	public void placeShooterAlien() {
		gameController.getGameFrame().getGamePanel().placeAlien(shooterAlien.getLocation(), shooterAlien.getAlienType());
	}
	
	
	public void deactivate() {
		if( shooterAlien != null) {
			if(shooterAlien.getIsActive()) {
				shooterAlien.setIsActive(false);
				gameController.getGameFrame().getGamePanel().setNullIcon(shooterAlien.getLocation());
			}
		}
	}
	
	
	
	public ShooterAlien getShooterAlien() {
		if(this.shooterAlien != null) {
			this.deactivate();
		}
		Location doorLocation = gameController.getGameFrame().getDoorLocation();
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
		
		ShooterAlien shooterAlien = (ShooterAlien) AlienFactory.getInstance().getAlien("Shooter" , object_locations.get(rand));
		setShooterAlien(shooterAlien);
		placeShooterAlien();
		return shooterAlien;
	}


	public void setShooterAlien(ShooterAlien shooterAlien) {
		this.shooterAlien = shooterAlien;
	}


	
	
	

}
