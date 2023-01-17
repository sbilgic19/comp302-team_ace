package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ApplicationLogic.AuthorizationLogic;
import Database.Client;
import UI.GameController;
import UI.GameFrame;
import UI.IconFactory;
import domain.GameInfo;
import domain.RoomObject;

public class NewOrLoadGameSelectionHandler implements ActionListener {

	//private GameFrame gameFrame;
	private Client client;
	private GameController gameController;
	
	
	public NewOrLoadGameSelectionHandler(GameController gameController, Client client) {
		
		this.gameController = gameController;
		this.client = client;
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
			GameInfo.getInstance().setListOfObjects(gameInfo.getListOfObjects());

			System.out.println(gameInfo.getPlayer().getLives());
			System.out.println(gameInfo.getTime());


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

			for (RoomObject roomObject : gameInfo.getListOfObjects()) {

				icon = images[roomObject.getTypeID()];
				gameMap[roomObject.getLocation().getLocationX()][roomObject.getLocation().getLocationY()].setIcon(icon);
			}
			gameMap[gameInfo.getPlayer().getLocation().getLocationX()][gameInfo.getPlayer().getLocation().getLocationY()].setIcon(gameController.getGameFrame().getGamePanel().getPlayerFrontIcon());
			IconFactory iconFactory = IconFactory.getInstance();
			ImageIcon doorIcon = iconFactory.generateIcon("../assets/doorIcon1.png", 50, 50);
			gameMap[gameInfo.getDoorLocation().getLocationX()][gameInfo.getDoorLocation().getLocationY()].setIcon(doorIcon);
			gameController.switchBuildView();
			gameController.switchGameView(gameMap);


		}
	}

}
