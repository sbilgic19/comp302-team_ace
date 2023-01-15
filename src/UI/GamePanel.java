package UI;

import javax.swing.*;

import Controllers.TimeWastingAlienHandler;
import Controllers.BlindAlienHandler;
import Controllers.PowerUpHandler;
import Controllers.RoomKeyHandler;
import Controllers.ShooterAlienHandler;
import dataStructures.Location;
import domain.powerUps.PowerUp;
import domain.Player;
import domain.aliens.ShooterAlien;
import domain.aliens.TimeWastingAlien;
import domain.Key;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

public class GamePanel extends JPanel {

    private static JLabel[][] gameMap;
    private static ImageIcon playerFrontIcon;
    private static ImageIcon playerBackIcon;
    private static ImageIcon playerLeftIcon;
    private static ImageIcon playerRightIcon;
    private static ImageIcon playerFrontWithVestIcon;
    private static ImageIcon playerBackWithVestIcon;
    private static ImageIcon playerLeftWithVestIcon;
    private static ImageIcon playerRightWithVestIcon;
    private static ImageIcon keyIcon;
    private static ImageIcon bagIcon;
    private static ImageIcon timerIcon;
    private static ImageIcon timeWastingAlienIcon;
    private static ImageIcon blindIcon;
    private static ImageIcon shooterIcon;
    
    private static Icon extraLifeIcon;
    private static Icon extraTimeIcon;
    private static Icon protectionVestIcon;

    private static int numRow;
    private static int numCol;
    private GameFrame gameFrame;
    private Key key;
    private PowerUp powerUp;
    private static Player player;
    
    private static ImageIcon openDoorIcon;
     
    RoomKeyHandler roomKeyHandler;
    PowerUpHandler powerUpHandler;
    TimeWastingAlienHandler alienHandler;
    ShooterAlienHandler shooterAlienHandler;
    BlindAlienHandler blindAlienHandler;
    public GamePanel(GameFrame gameFrame) {
    	
    	numRow = gameFrame.getNumRow();
    	numCol = gameFrame.getNumCol();

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

        openDoorIcon = iconFactory.generateIcon("../assets/doorIcon2.png", 50, 50);
        
        timeWastingAlienIcon = iconFactory.generateIcon("../assets/timeWastingAlienIcon.png", 50, 50);
        
        this.gameFrame = gameFrame;
        
        this.setLayout(new GridLayout(numRow, numCol, 0, 0));
        roomKeyHandler = gameFrame.getRoomKeyHandler();
        shooterAlienHandler = gameFrame.getShooterAlienHandler();
        blindAlienHandler = gameFrame.getBlindAlienHandler();
        powerUpHandler = new PowerUpHandler(gameFrame,player);
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
						if(!GameState.getInstance().isPaused() && key != null  && !GameState.getInstance().isGameOver()) {
							int locX_key = key.getLocation().getLocationX();
                    		int locY_key =key.getLocation().getLocationY();
                    		Boolean b = roomKeyHandler.takeKey(key);
                    		if(e.getSource() == gameMap[locX_key][locY_key] && key != null && b ) {
                    			System.out.println(locX_key+" " + locY_key);
                            	gameFrame.updateKeyView(b);
                            	System.out.println(b);
                            	key = null;
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
                            gameFrame.updatePlayerLivesView(player.getLives());
                        	gameMap[locX][locY].setIcon(null);
                        	powerUp = null;
                        	
                        }

                        
                       }
                    }
                });
                
                
            }
        }

        key = roomKeyHandler.getRandomKey();
        System.out.println(key.getLocation().getLocationX()+" " + key.getLocation().getLocationY());
        gameMap[0][5].setIcon(playerFrontIcon);
        powerUp = powerUpHandler.getRandomPowerUp();
        alienHandler = new TimeWastingAlienHandler(gameFrame, key);
        
        if (!key.getIsTaken()) {
        	TimeWastingAlien alien = alienHandler.getTimeWastingAlien();
        	alien.setLevelTime(gameFrame.getLevelTime()); //no point
        	GameTime.getInstance().setTimeWastingAlien(alien);
        }

    }
    
    
    public void alienProducer() {

    	
    	Timer alienTimer = new Timer();
    	TimerTask tt = new TimerTask() {
    	    @Override
    	    public void run() {
    	    	int alienNumber = new Random().nextInt(2)+1;
    	    	System.out.println(alienNumber);
    	    	switch(alienNumber) {
    	    	case 0:
    	    		System.out.println("TimeWasting");
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
    	    		System.out.println("heyyo");
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
    
    

	public static void updatePlayerView(int xPlayerPosition, int yPlayerPosition,
                                 int newXPlayerPosition, int newYPlayerPosition, int playerLogoPosition) {
        gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
        ImageIcon[] playerIcons = {playerFrontIcon, playerBackIcon, playerLeftIcon, playerRightIcon};
        ImageIcon[] protectedPlayerIcons = {playerFrontWithVestIcon, playerBackWithVestIcon, playerLeftWithVestIcon, playerRightWithVestIcon};
        if(player.isProtected() == true){
            gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(protectedPlayerIcons[playerLogoPosition]);
        }else{
            gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(playerIcons[playerLogoPosition]);
        }
    }
	
	
	public static void updateBlindAlienView(int xPosition, int yPosition,
                                 int newXPosition, int newYPosition) {
		
        gameMap[xPosition][yPosition].setIcon(null);
        gameMap[newXPosition][newYPosition].setIcon(blindIcon);
      
	}
    
    public static void placePowerUp(Location location, String powerUpType)
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
        }

    }
    
    public static void placeAlien(Location location, String alienType) {
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
    	ImageIcon[] icons = {keyIcon, bagIcon, timerIcon};
     	return icons;
    }
    
    public ImageIcon getOpenDoorIcon() {
    	return openDoorIcon;
    }
     
    public static JLabel[][] getGameMap() {
    	return gameMap;
    }
    
    public Player getPlayer() {
    	return this.player;
    }
    public static void setPlayer(Player player) {
        GamePanel.player = player;
    }


    public static ImageIcon getTimeWastingAlienIcon() {
    	return timeWastingAlienIcon;
    } 
    
    public static void setNullIcon(Location location) {
    	gameMap[location.getLocationX()][location.getLocationY()].setIcon(null);
    }
}