package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Database.Client;
import UI.GameController;
import UI.GameState;
import domain.GameInfo;
import domain.RoomObject;

public class NewOrLoadGameSelectionHandler implements ActionListener {

	//private GameFrame gameFrame;
	private final Client client;
	private final GameController gameController;
	
	
	public NewOrLoadGameSelectionHandler(GameController gameController) {
		
		this.gameController = gameController;
		this.client = gameController.getClient();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getActionCommand().compareTo("New Game") == 0) {
			gameController.switchBuildView();
		}else if (event.getActionCommand().compareTo("Load Game") == 0) {
			GameInfo gameInfo = client.loadGame("Game1");
			GameInfo.getInstance().setTime(gameInfo.getTime());
			GameInfo.getInstance().setPlayer(gameInfo.getPlayer());
			GameInfo.getInstance().setIsLoaded(true);
			GameInfo.getInstance().setListOfObjectsOfAllLevels(gameInfo.getListOfObjectsOfAllLevels());
			GameInfo.getInstance().setDoorLocationList(gameInfo.getDoorLocationList());
			GameInfo.getInstance().setKeyList(gameInfo.getKeyList());
			GameInfo.getInstance().setCurrentLevel(gameInfo.getCurrentLevel());
			GameInfo.getInstance().setActiveAlien(gameInfo.getActiveAlien());
			GameInfo.getInstance().setActivePowerUp(gameInfo.getActivePowerUp());

			System.out.println(gameInfo.getPlayer().getLives());
			System.out.println(gameInfo.getTime());
			System.out.println(gameInfo.getPlayer().getBag().size());


			ImageIcon[] images = gameController.getGameFrame().getBuildPanel().getIcons();
			ImageIcon icon;
			int rowCount = gameController.getGameFrame().getNumRow();
			int columnCount = gameController.getGameFrame().getNumCol();

			JLabel[][] gameMap = new JLabel[rowCount][columnCount];
			for(int i = 0; i<rowCount; i++){
				for(int j = 0; j<columnCount; j++){
					gameMap[i][j] = new JLabel();
				}
			}

			for (RoomObject roomObject : gameInfo.getCurrentObjects()) {

				icon = images[roomObject.getTypeID()];
				gameMap[roomObject.getLocation().getLocationX()][roomObject.getLocation().getLocationY()].setIcon(icon);
			}

			gameController.switchBuildView();
			gameController.switchGameView(gameMap);


		}
	}

}
