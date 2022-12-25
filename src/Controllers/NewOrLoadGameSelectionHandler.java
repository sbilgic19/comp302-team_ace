package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ApplicationLogic.AuthorizationLogic;
import Database.Client;
import UI.GameFrame;
import domain.GameInfo;
import domain.RoomObject;

public class NewOrLoadGameSelectionHandler implements ActionListener {

	private GameFrame gameFrame;
	private Client client;
	
	public NewOrLoadGameSelectionHandler(GameFrame gameFrame, Client client) {
		this.gameFrame = gameFrame;
		this.client = client;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getActionCommand().compareTo("New Game") == 0) {
			gameFrame.switchBuildView();
		}else if (event.getActionCommand().compareTo("Load Game") == 0) {
			GameInfo gameInfo = client.loadGame("Game1");
			GameInfo.getInstance().setTime(gameInfo.getTime());
			GameInfo.getInstance().setPlayer(gameInfo.getPlayer());
			GameInfo.getInstance().setListOfObjects(gameInfo.getListOfObjects());

			ImageIcon[] images = gameFrame.getBuildPanel().getIcons();
			ImageIcon icon;
			int rowCount = gameFrame.getNumRow();
			int columnCount = gameFrame.getNumCol();

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
			//gameMap[gameInfo.getPlayer().getLocation().getLocationX()][gameInfo.getPlayer().getLocation().getLocationY()].setIcon();
			gameFrame.switchBuildView();
			gameFrame.switchGameView(gameMap);


		}
	}

}
