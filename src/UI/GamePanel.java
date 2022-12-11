package UI;

import javax.swing.*;

import Controllers.PowerUpHandler;
import dataStructures.Location;
import domain.powerUps.PowerUp;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    private static JLabel[][] gameMap;
    private static Icon playerIcon;

    private static Icon tableIcon;
    private static Icon extraLifeIcon;
    private static Icon extraTimeIcon;

    private static int numRow;
    private static int numCol;
    private GameFrame gameFrame;
    
    private PowerUp powerUp;
    
    PowerUpHandler powerUpHandler;
    
    
    

    public GamePanel(GameFrame gameFrame) {
    	
    	numRow = gameFrame.getNumRow();
    	numCol = gameFrame.getNumCol();

        playerIcon = IconFactory.getInstance().generateIcon("../assets/playerIcon.png", 50, 50);
        tableIcon = IconFactory.getInstance().generateIcon("../assets/tableIcon.png", 50, 50);
        extraLifeIcon = IconFactory.getInstance().generateIcon("../assets/extraLifeIcon.png", 50, 50);
        extraTimeIcon = IconFactory.getInstance().generateIcon("../assets/extraTimeIcon.png", 50, 50);
        
        this.gameFrame = gameFrame;
        
        this.setLayout(new GridLayout(numRow, numCol, 0, 0));
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
                    	if(!GameState.getInstance().isPaused() && powerUp != null) {
                    		int locX = powerUp.getLocation().getLocationX();
                    		int locY = powerUp.getLocation().getLocationY();
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

        gameMap[0][6].setIcon(tableIcon);
        gameMap[0][5].setIcon(playerIcon);
        powerUp = powerUpHandler.getRandomPowerUp();

    }

	public static void updatePlayerView(int xPlayerPosition, int yPlayerPosition,
                                 int newXPlayerPosition, int newYPlayerPosition) {
        gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
        gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(playerIcon);
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
    
    public static JLabel[][] getGameMap() {
    	return gameMap;
    }    
}