package UI;

import javax.swing.*;

import Controllers.TimeWastingAlienHandler;
import Controllers.BlindAlienHandler;
import Controllers.PowerUpHandler;
import Controllers.RoomKeyHandler;
import Controllers.ShooterAlienHandler;
import dataStructures.Location;
import domain.GameInfo;
import domain.powerUps.PowerUp;
import domain.Player;
import domain.aliens.ShooterAlien;
import domain.aliens.TimeWastingAlien;
import domain.Key;
import domain.powerUps.PowerUpFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;


public class GamePanel extends JPanel {

    private JLabel[][] gameMap;
    private ImageIcon playerFrontIcon;
    private ImageIcon playerBackIcon;
    private ImageIcon playerLeftIcon;
    private ImageIcon playerRightIcon;
    private ImageIcon playerFrontWithVestIcon;
    private ImageIcon playerBackWithVestIcon;
    private ImageIcon playerLeftWithVestIcon;
    private static ImageIcon playerRightWithVestIcon;
    private static ImageIcon keyIcon;
    private static ImageIcon bagIcon;
    private static ImageIcon timerIcon;
    private static ImageIcon timeWastingAlienIcon;
    private static ImageIcon blindIcon;
    private static ImageIcon shooterIcon;
    
    private ImageIcon extraLifeIcon;
    private ImageIcon extraTimeIcon;
    private ImageIcon protectionVestIcon;
    private ImageIcon hintIcon;
    private ImageIcon plasticBottleIcon;
    private ImageIcon plasticBottleIconEast;
    private ImageIcon plasticBottleIconWest;
    private ImageIcon plasticBottleIconSouth;

    private int numRow;
    private int numCol;
    private GameFrame gameFrame;
    private Key key;
    private PowerUp powerUp;
    //private Player player;
    private boolean isPowerUpActive;
    
    private ImageIcon openDoorIcon;
     
    RoomKeyHandler roomKeyHandler;
    PowerUpHandler powerUpHandler;
    TimeWastingAlienHandler timeWastingAlienHandler;
    ShooterAlienHandler shooterAlienHandler;
    BlindAlienHandler blindAlienHandler;
    
    //private GameController gameController;
    public GamePanel(GameFrame gameFrame) {
    	
    	//this.gameController = gameController;
    	this.gameFrame = gameFrame;
    	numRow = this.gameFrame.getNumRow();
    	numCol = this.gameFrame.getNumCol();

    	IconFactory iconFactory = IconFactory.getInstance();
        playerFrontIcon = iconFactory.generateIcon("../assets/playerFrontIcon.png", 50, 50);
        playerBackIcon = iconFactory.generateIcon("../assets/playerBackIcon.png", 50, 50);
        playerLeftIcon = iconFactory.generateIcon("../assets/playerLeftIcon.png", 50, 50);
        playerRightIcon = iconFactory.generateIcon("../assets/playerRightIcon.png", 50, 50);

        playerFrontWithVestIcon = iconFactory.generateIcon("../assets/armoredFront.jpg", 50, 50);
        playerBackWithVestIcon = iconFactory.generateIcon("../assets/armoredBack.jpg", 50, 50);
        playerLeftWithVestIcon = iconFactory.generateIcon("../assets/armoredLeft.jpg", 50, 50);
        playerRightWithVestIcon = iconFactory.generateIcon("../assets/armoredRight.jpg", 50, 50);
        

        blindIcon = iconFactory.generateIcon("../assets/blind.jpg", 50, 50);
        shooterIcon = iconFactory.generateIcon("../assets/shooter.jpg", 50, 50);

        keyIcon = iconFactory.generateIcon("../assets/keyIcon.png", 50, 50);
        bagIcon = iconFactory.generateIcon("../assets/bagIcon.png", 50, 50);
        timerIcon = iconFactory.generateIcon("../assets/timerIcon.png", 50, 50);
        
        extraLifeIcon = iconFactory.generateIcon("../assets/extraLifeIcon.png", 50, 50);
        extraTimeIcon = iconFactory.generateIcon("../assets/extraTimeIcon.png", 50, 50);
        protectionVestIcon = iconFactory.generateIcon("../assets/protectionVest.png", 50, 50);
        hintIcon = iconFactory.generateIcon("../assets/hintIcon.png", 50, 50);
        plasticBottleIcon = iconFactory.generateIcon("../assets/plasticbottleNorth.png", 50, 50);

        plasticBottleIconEast = iconFactory.generateIcon("../assets/plasticbottleEast.png", 50, 50);
        plasticBottleIconWest = iconFactory.generateIcon("../assets/plasticbottleWest.png", 50, 50);
        plasticBottleIconSouth = iconFactory.generateIcon("../assets/plasticbottleSouth.png", 50, 50);

        openDoorIcon = iconFactory.generateIcon("../assets/doorIcon2.png", 50, 50);
        
        timeWastingAlienIcon = iconFactory.generateIcon("../assets/timeWastingAlienIcon.png", 50, 50);
        
    
        
        this.setLayout(new GridLayout(numRow, numCol, 0, 0));
        roomKeyHandler = this.gameFrame.getRoomKeyHandler();

        shooterAlienHandler = this.gameFrame.getShooterAlienHandler();
        blindAlienHandler = this.gameFrame.getBlindAlienHandler();
        powerUpHandler = this.gameFrame.getPowerUpHandler();

    }

    public void setGameMap(JLabel[][] buildModeMap) {
    	//float frameWidth = 1468;
		//float frameHeight = 674;
        gameMap = new JLabel[numRow][numCol];

        for (int ii = 0; ii < numRow; ii++) {
            for (int jj = 0; jj < numCol; jj++) {
				gameMap[ii][jj] = buildModeMap[ii][jj];
				gameMap[ii][jj].setBorder(null);
				add(gameMap[ii][jj]);
				
				gameMap[ii][jj].addMouseListener(new MouseAdapter() {
					@Override
                    public void mouseClicked(MouseEvent e) {
						if(!GameState.getInstance().isPaused() && key != null && !key.getIsTaken()  && !GameState.getInstance().isGameOver()) {
							int locX_key = key.getLocation().getLocationX();
                    		int locY_key =key.getLocation().getLocationY();
                    		if(e.getSource() == gameMap[locX_key][locY_key] && key != null ) {
                    			roomKeyHandler.takeKey(key);
                    			System.out.println(locX_key+" " + locY_key);
                    			gameFrame.updateKeyView(true);
                            	System.out.println(true);
                    		}
						}
						
					}
				});

				
                gameMap[ii][jj].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    	if(!GameState.getInstance().isPaused() && powerUp != null && !GameState.getInstance().isGameOver()) {
                    		int locX = powerUp.getLocation().getLocationX();
                    		int locY = powerUp.getLocation().getLocationY();
                    		int locX_key = 0;
                    		int locY_key = 0;
                    		if(key != null) {
                    		locX_key = key.getLocation().getLocationX();
                    		locY_key =key.getLocation().getLocationY();
                    		
                    		}
                    		String powerUpType = powerUp.getPowerUpType();
                        if(e.getSource() == gameMap[locX][locY]) {
                        	//powerUp.triggerEffect();
                        	powerUpHandler.usePowerUp(powerUp);
                          gameFrame.getGameController().removePowerUp();
                          GameInfo.getInstance().setActivePowerUp(null);
                        	gameFrame.updatePlayerLivesView(gameFrame.getGameController().getPlayer().getLives());
                        	gameMap[locX][locY].setIcon(null);
                        	powerUp = null;
                        	
                        }

                        
                       }
                    }
                });
                
                
            }
        }

        key = roomKeyHandler.getRandomKey();
        PowerUpFactory.getInstance().setKey(key);
        System.out.println(key.getLocation().getLocationX()+" " + key.getLocation().getLocationY());
        gameMap[0][5].setIcon(playerFrontIcon);
        Timer powerUpCreatorTimer = new Timer();
      powerUpCreatorTimer.schedule(new TimerTask() {
          @Override
          public void run() {
            powerUp = powerUpHandler.getRandomPowerUp();
            gameFrame.getGameController().setPowerUp(powerUp);
            GameInfo.getInstance().setActivePowerUp(powerUp);
          }
        }, 0,12000);

        Timer powerUpRemoveTimer = new Timer();
        powerUpRemoveTimer.schedule(new TimerTask() {
          @Override
          public void run() {
            if (GameInfo.getInstance().getActivePowerUp() != null)
            {
              gameFrame.getGamePanel().getGameMap()[powerUp.getLocation().getLocationX()][powerUp.getLocation().getLocationY()].setIcon(null);
              gameFrame.getGameController().removePowerUp();
              GameInfo.getInstance().setActivePowerUp(null);
            }
          }
        }, 6 * 1000, 12 * 1000);
        timeWastingAlienHandler = new TimeWastingAlienHandler(gameFrame.getGameController(), key);
        
        
        if (!key.getIsTaken()) {
        	//TimeWastingAlien alien = alienHandler.getTimeWastingAlien();
        	//alien.setLevelTime(this.gameFrame.getLevelTime()); //no point
        	//GameTime.getInstance().setTimeWastingAlien(alien);
        }

    }
    
    
    
    
    
    public void alienProducer() {

    	
    	Timer alienTimer = new Timer();
    	TimerTask tt = new TimerTask() {
    	    @Override
    	    public void run() {
    	    	int alienNumber;
    	        if (!key.getIsTaken()) {
    	        	alienNumber = new Random().nextInt(3);
    	        }
    	        else {
    	        	alienNumber = new Random().nextInt(2)+1;
    	        }
    	        	
    	    	System.out.println(alienNumber);
    	    	switch(alienNumber) {
    	    	case 0:
    	    		timeWastingAlienRandomizer();
    	    		break;
    	    	case 1:
    	    		shooterAlieanRandomizer();
    	    		break;
    	    	case 2:
    	    		blindAlieanRandomizer();
    	    		break;
    	    }
    	    	
    	    	
    	    if(GameState.getInstance().isGameOver()) {
    	    	alienTimer.cancel();
    	    	}
    	    }
    	};
    	
    	alienTimer.scheduleAtFixedRate(tt, 1000, 10000);
    	
    	Timer stopTimer = new Timer();
    	stopTimer.scheduleAtFixedRate(new TimerTask() {
    	    @Override
    	    public void run() {
    	    	
    	    	if(GameState.getInstance().isGameOver()) {
    	    		tt.cancel();
    	    		stopTimer.cancel();
    	    	}
    	    }
    	}, 250, 250);
    	
    			
    }
    
    public void timeWastingAlienRandomizer() {
    	
        int minDelay = 7000; // 5 seconds
        int maxDelay = 10000; // 10 seconds
        int randomDelay = new Random().nextInt(maxDelay - minDelay + 1) + minDelay;
    	int t  = 0;
    	timeWastingAlienHandler.getTimeWastingAlien();
    	Timer wastingTimer = new Timer();
    	
    	wastingTimer.scheduleAtFixedRate(new TimerTask() {
    	    @Override
    	    public void run() {
    	    	if (!GameState.getInstance().isPaused()) {
    	    		
    	    		boolean b  = timeWastingAlienHandler.changeLocationOfKey();
	    	        if (b) {
	    	            System.out.println("changed");
	    	        }

    	    	}
    	    	
    	    	if(GameState.getInstance().isGameOver()) {
    	    		wastingTimer.cancel();    	    		//System.out.println("heyyo");
    	    	}

    	    }
    	}, 1000, 1000);
    	

    	new Timer().schedule(new TimerTask() {
    	    @Override
    	    public void run() {
    	    	wastingTimer.cancel();
    	    	if(!GameState.getInstance().isGameOver()) {
    	    		timeWastingAlienHandler.deactivate();
    	    	}
    	    }
    	}, randomDelay);

    }
    
    
    public void shooterAlieanRandomizer() {
    	
        int minDelay = 7000; // 5 seconds
        int maxDelay = 10000; // 10 seconds
        int randomDelay = new Random().nextInt(maxDelay - minDelay + 1) + minDelay;
    	
    	shooterAlienHandler.getShooterAlien();
    	Timer shooterTimer = new Timer();
    	shooterTimer.scheduleAtFixedRate(new TimerTask() {
    	    @Override
    	    public void run() {
    	    	if (!GameState.getInstance().isPaused()) {
	    	        if (shooterAlienHandler.shoot()) {
	    	            System.out.println("Shooted");
	    	        }
    	    	}
    	    	
    	    	if(GameState.getInstance().isGameOver()) {
    	    		shooterTimer.cancel();
    	    		//System.out.println("heyyo");
    	    	}

    	    }
    	}, 1000, 1000);

    	new Timer().schedule(new TimerTask() {
    	    @Override
    	    public void run() {
    	        shooterTimer.cancel();
    	    	if(!GameState.getInstance().isGameOver()) {
    	    		shooterAlienHandler.deactivate();
    	    	}
    	    }
    	}, randomDelay);

    }
    
    public void blindAlieanRandomizer() {
    	
        int minDelay = 7000; // 5 seconds
        int maxDelay = 10000; // 10 seconds
        int randomDelay = new Random().nextInt(maxDelay - minDelay + 1) + minDelay;
    	
    	blindAlienHandler.getBlindAlien();
    	Timer blindTimer = new Timer();
    	Timer blindshootTimer = new Timer();
    	blindTimer.scheduleAtFixedRate(new TimerTask() {
    	    @Override
    	    public void run() {
    	    	if (!GameState.getInstance().isPaused()) {
    	    		blindAlienHandler.move();
    	    	}
    	    	
    	    	
    	    	if(GameState.getInstance().isGameOver()) {
    	    		blindTimer.cancel();
    	    	}
    	    }
    	}, 500, 500);
    	

    	blindTimer.scheduleAtFixedRate(new TimerTask() {
    	    @Override
    	    public void run() {
    	    	if (!GameState.getInstance().isPaused()) {
    	    		blindAlienHandler.shoot();
    	    	}
    	    	
    	    	if(GameState.getInstance().isGameOver()) {
    	    		blindshootTimer.cancel();
    	    	}
    	    }
    	}, 250, 250);

    	new Timer().schedule(new TimerTask() {
    	    @Override
    	    public void run() {
    	    	blindTimer.cancel();
    	    	blindshootTimer.cancel();
    	    	if(!GameState.getInstance().isGameOver()) {
    	    		blindAlienHandler.deactivate();
    	    	}
    	    }
    	}, randomDelay);

    }
    
    

	public void updatePlayerView(int xPlayerPosition, int yPlayerPosition,
                                 int newXPlayerPosition, int newYPlayerPosition, int playerLogoPosition) {
        gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
        ImageIcon[] playerIcons = {playerFrontIcon, playerBackIcon, playerLeftIcon, playerRightIcon};
        ImageIcon[] protectedPlayerIcons = {playerFrontWithVestIcon, playerBackWithVestIcon, playerLeftWithVestIcon, playerRightWithVestIcon};
        if(gameFrame.getGameController().getPlayer().isProtected() == true){
            gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(protectedPlayerIcons[playerLogoPosition]);
        }else{
            gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(playerIcons[playerLogoPosition]);
        }
    }

	
	
	public void updateBlindAlienView(int xPosition, int yPosition,
                                 int newXPosition, int newYPosition) {
		
        gameMap[xPosition][yPosition].setIcon(null);
        gameMap[newXPosition][newYPosition].setIcon(blindIcon);
      
	}


    public boolean updateBottleView(int oldXLoc, int oldYLoc, int newXPlayerPosition, int newYPlayerPosition, int bottleIconPosition) {
        //gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
      System.out.println("OldBottle:" + oldXLoc);
      System.out.println("OldBottle " + oldYLoc);
        System.out.println("Bottle:" + newXPlayerPosition);
        System.out.println("Bottle " + newYPlayerPosition);
        System.out.println("Player: " +gameFrame.getGameController().getPlayer().getLocation().getLocationX());
        System.out.println("Player: "+ gameFrame.getGameController().getPlayer().getLocation().getLocationY());
        if(oldXLoc != gameFrame.getGameController().getPlayer().getLocation().getLocationX() || oldYLoc != gameFrame.getGameController().getPlayer().getLocation().getLocationY()){
            gameMap[oldXLoc][oldYLoc].setIcon(null);
        }
        ImageIcon[] plasticBottleIcons = {plasticBottleIcon, plasticBottleIconEast, plasticBottleIconSouth, plasticBottleIconWest};
        if(gameMap[newXPlayerPosition][newYPlayerPosition].getIcon() == null && newXPlayerPosition < numRow-1 && newYPlayerPosition < numCol-1
                && newXPlayerPosition > 0 && newYPlayerPosition > 0){
            gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(plasticBottleIcons[bottleIconPosition % 4]);
            return true;
        }else {
            //gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(null);
            return false;
        }
    }

    
    public void placePowerUp(Location location, String powerUpType)
    {
    	switch(powerUpType) {
          case "ExtraLife":
            gameMap[location.getLocationX()][location.getLocationY()].setIcon(extraLifeIcon);
            break;
          case "ExtraTime":
    		    gameMap[location.getLocationX()][location.getLocationY()].setIcon(extraTimeIcon);
    		    break;
          case "ProtectionVest":
            gameMap[location.getLocationX()][location.getLocationY()].setIcon(protectionVestIcon);
            break;
          case "Hint":
            gameMap[location.getLocationX()][location.getLocationY()].setIcon(hintIcon);
            break;
          case "PlasticBottle":
            gameMap[location.getLocationX()][location.getLocationY()].setIcon(plasticBottleIcon);
            break;
        }

    }
    
    public void placeAlien(Location location, String alienType) {
    	switch(alienType) {
    	case "TimeWasting":
    		gameMap[location.getLocationX()][location.getLocationY()].setIcon(timeWastingAlienIcon);
    		break;
    	case "Shooter":
    		gameMap[location.getLocationX()][location.getLocationY()].setIcon(shooterIcon);
    		break;
    	case "Blind":
    		gameMap[location.getLocationX()][location.getLocationY()].setIcon(blindIcon);
    		break;
    	}
    }
    
    public ImageIcon[] getGamePanelIcons() {
    	ImageIcon[] icons = {keyIcon, bagIcon, timerIcon, protectionVestIcon};
     	return icons;
    }
    
    public ImageIcon getOpenDoorIcon() {
    	return openDoorIcon;
    }
     
    public JLabel[][] getGameMap() {
    	return this.gameMap;
    }
    
//    public Player getPlayer() {
//    	return this.player;
//    }
//    public void setPlayer(Player player) {
//        this.player = player;
//    }


    public ImageIcon getTimeWastingAlienIcon() {
    	return timeWastingAlienIcon;
    } 
    
    public void setNullIcon(Location location) {
    	gameMap[location.getLocationX()][location.getLocationY()].setIcon(null);
    }

  /**
   * remove borders in the gameMap.
   */
  public void removeBorders() {
      for (int row = 0; row < gameMap.length; row++) {
        for (int col = 0; col < gameMap[0].length; col++) {
          this.gameMap[row][col].setBorder(null);
        }
      }
    }

  /**
   * Sets borders around the key
   * @param keyRow row of the key
   * @param keyCol column of the key
   */
  public void addBordersHint(int keyRow, int keyCol)
    {
      Random rand = new Random();
      int offsetRow = rand.nextInt(3) - 1;
      int offsetCol = rand.nextInt(3) - 1;

      // Set the border of the labels within the rectangle to indicate the hint
      for (int row = keyRow + offsetRow; row < keyRow + offsetRow + 4; row++) {
        for (int col = keyCol + offsetCol; col < keyCol + offsetCol + 4; col++) {
          // Make sure the label is within the bounds of the game map
          if (row >= 0 && row < this.gameMap.length && col >= 0 && col < this.gameMap[0].length) {
            System.out.println("Border set");
            gameMap[row][col].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
          }
        }
      }
    }

    public Key getKey(){
      return this.key;
    }

    public void setPowerUp(PowerUp powerUp) {
      this.powerUp = powerUp;
    }

  public boolean getIsPowerUpActive() {
    return isPowerUpActive;
  }


}