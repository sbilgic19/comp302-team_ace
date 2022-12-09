package UI;

import javax.swing.*;

import java.awt.*;

public class GamePanel extends JPanel {

    private static JLabel[][] gameMap;
    private static Icon playerIcon;
    private static Icon tableIcon;
    private static final int numRow = 30;
    private static final int numCol = 45;

    public GamePanel() {

        playerIcon = IconFactory.getInstance().generateIcon("../assets/playerIcon.png", 40, 40);
        tableIcon = IconFactory.getInstance().generateIcon("../assets/tableIcon.png", 40, 40);

        this.setLayout(new GridLayout(numRow, numCol, 0, 0));

    }

    public void setGameMap() {
        gameMap = new JLabel[numRow][numCol];
        for (int ii = 0; ii < numRow; ii++) {
            for (int jj = 0; jj < numCol; jj++) {
                gameMap[ii][jj] = new JLabel();
                add(gameMap[ii][jj]);
            }
        }
        gameMap[0][6].setIcon(tableIcon);
        gameMap[0][5].setIcon(playerIcon);
    }
    
	public static JLabel[][] getGameMap() {
		return gameMap;
	}
    

	public static void updatePlayerView(int xPlayerPosition, int yPlayerPosition,
                                 int newXPlayerPosition, int newYPlayerPosition) {
        gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
        gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(playerIcon);
    }




    


}
