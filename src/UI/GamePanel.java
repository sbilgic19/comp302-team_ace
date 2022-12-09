package UI;

import javax.swing.*;

import Controllers.PowerUpHandler;
import dataStructures.Location;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {

    private static JLabel[][] gameMap;
    private static Icon playerIcon;
    private static Icon extraLifeIcon;
    private static final int numRow = 30;
    private static final int numCol = 45;
    private GameFrame gameFrame;
    
    

    public GamePanel(GameFrame gameFrame) {

        playerIcon = IconFactory.getInstance().generateIcon("../assets/playerIcon.png", 40, 40);
        extraLifeIcon = IconFactory.getInstance().generateIcon("../assets/playerIcon.png", 40, 40);
        
        this.gameFrame = gameFrame;

        this.setLayout(new GridLayout(numRow, numCol, 0, 0));

    }

    public void setGameMap() {
    	
        gameMap = new JLabel[numRow][numCol];
        for (int ii = 0; ii < numRow; ii++) {
            for (int jj = 0; jj < numCol; jj++) {
                gameMap[ii][jj] = new JLabel();
                add(gameMap[ii][jj]);
                
                gameMap[ii][jj].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    	if(!GameState.getInstance().isPaused()) {
                        if(e.getSource() == gameMap[10][10]) {
                        	gameFrame.updatePlayerLivesView(4);
                        	System.out.println("ExtraLife Used");
                        	gameMap[10][10].setText(null);
                        }
                        else if(e.getSource() == gameMap[15][15]) {
                        	gameFrame.increaseSecond(5);
                        	System.out.println("ExtraTime Used");
                        	gameMap[15][15].setText(null);
                        }}
                    }
                });
                
                
            }
        }
        gameMap[0][0].setIcon(playerIcon);
        PowerUpHandler.getInstance().getRandomPowerUp();
    }
    public static void updatePlayerView(int xPlayerPosition, int yPlayerPosition,
                                 int newXPlayerPosition, int newYPlayerPosition) {
        gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
        gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(playerIcon);
    }
    
    public static void placePowerUp(Location location, String powerUpType)
    {
    	//gameMap[location.getLocationX()][location.getLocationY()].setIcon(extraLifeIcon);
    	gameMap[10][10].setText("EL");
    	gameMap[15][15].setText("ET");
    }
    
    public static JLabel[][] getGameMap()
    {
    	return gameMap;
    }    
    
    


}
