package UI;

import javax.swing.*;


import Controllers.PowerUpHandler;
import Controllers.RoomKeyHandler;
import domain.powerUps.PowerUp;
import domain.Player;
import domain.Key;
import domain.Location;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    private static JLabel[][] gameMap;
    private static ImageIcon playerFrontIcon;
    private static ImageIcon playerBackIcon;
    private static ImageIcon playerLeftIcon;
    private static ImageIcon playerRightIcon;
    private static ImageIcon keyIcon;
    private static ImageIcon bagIcon;
    private static ImageIcon timerIcon;
    
    private static Icon extraLifeIcon;
    private static Icon extraTimeIcon;

    private static int numRow;
    private static int numCol;
    private GameFrame gameFrame;
    private Key key;
    private PowerUp powerUp;
    private Player player;
     
    RoomKeyHandler roomKeyHandler;
    PowerUpHandler powerUpHandler;
    
    public GamePanel(GameFrame gameFrame) {
    	
    	numRow = gameFrame.getNumRow();
    	numCol = gameFrame.getNumCol();

    	IconFactory iconFactory = IconFactory.getInstance();
        playerFrontIcon = iconFactory.generateIcon("../assets/playerFrontIcon.png", 50, 50);
        playerBackIcon = iconFactory.generateIcon("../assets/playerBackIcon.png", 50, 50);
        playerLeftIcon = iconFactory.generateIcon("../assets/playerLeftIcon.png", 50, 50);
        playerRightIcon = iconFactory.generateIcon("../assets/playerRightIcon.png", 50, 50);
        keyIcon = iconFactory.generateIcon("../assets/keyIcon.png", 50, 50);
        bagIcon = iconFactory.generateIcon("../assets/bagIcon.png", 50, 50);
        timerIcon = iconFactory.generateIcon("../assets/timerIcon.png", 50, 50);
        
        extraLifeIcon = iconFactory.generateIcon("../assets/extraLifeIcon.png", 50, 50);
        extraTimeIcon = iconFactory.generateIcon("../assets/extraTimeIcon.png", 50, 50);

        this.gameFrame = gameFrame;
        
        this.setLayout(new GridLayout(numRow, numCol, 0, 0));
        roomKeyHandler = gameFrame.getRoomKeyHandler();
        powerUpHandler = new PowerUpHandler(gameFrame);
    }

    public void setGameMap(JLabel[][] buildModeMap) {
    	float frameWidth = 1468;
		float frameHeight = 674;
        gameMap = new JLabel[numRow][numCol];

        for (int ii = 0; ii < numRow; ii++) {
            for (int jj = 0; jj < numCol; jj++) {
				gameMap[ii][jj] = buildModeMap[ii][jj];
				gameMap[ii][jj].setBorder(null);
				add(gameMap[ii][jj]);
				
				gameMap[ii][jj].addMouseListener(new MouseAdapter() {
					@Override
                    public void mouseClicked(MouseEvent e) {
						if(!GameState.getInstance().isPaused() && key != null) {
							int locX_key = key.getLocation().getLocationX();
                    		int locY_key =key.getLocation().getLocationY();
                    		if(e.getSource() == gameMap[locX_key][locY_key] && key != null) {
                    			System.out.println(locX_key+" " + locY_key);
                            	Boolean b = roomKeyHandler.takeKey(key);
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
                    	if(!GameState.getInstance().isPaused() && powerUp != null) {
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
                        	switch(powerUpType) {
	                        	case "ExtraLife":
	                        		gameFrame.updatePlayerLivesView(4);
	                        		System.out.println("ExtraLife Used");
	                        		break;
	                        	case "ExtraTime":
	                        		gameFrame.increaseSecond(5);
	                        		System.out.println("ExtraTime Used");
	                        		break;
	                        	default:
                        	}
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

    }

	public static void updatePlayerView(int xPlayerPosition, int yPlayerPosition,
                                 int newXPlayerPosition, int newYPlayerPosition, int playerLogoPosition) {
        gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
        ImageIcon[] playerIcons = {playerFrontIcon, playerBackIcon, playerLeftIcon, playerRightIcon};
        gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(playerIcons[playerLogoPosition]);
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
    	}
    }
    
    public ImageIcon[] getKeyPanelIcons() {
    	ImageIcon[] icons = {keyIcon, bagIcon, timerIcon};
     	return icons;
    }
    
    public static JLabel[][] getGameMap() {
    	return gameMap;
    }
    
    public Player getPlayer() {
    	return this.player;
    }
}