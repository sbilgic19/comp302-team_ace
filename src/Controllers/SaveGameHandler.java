package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ApplicationLogic.AuthorizationLogic;
import Database.Client;
import UI.GameController;
import UI.GameFrame;
import UI.GameTime;
import UI.LoginPanel;
import domain.GameInfo;

public class SaveGameHandler implements ActionListener{

	
	private GameController gameController;
	private Client client;
	
	public SaveGameHandler(GameController gameController, Client client) {
		this.gameController = gameController;
		this.client = client;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getActionCommand().compareTo("Save Game") == 0) {
			System.out.println("Save Game butonuna basıldı");
			GameInfo.getInstance().setDoorLocation(gameController.getGameFrame().getDoorLocation());
			GameInfo.getInstance().setPlayerLocation(gameController.getPlayer().getLocation());
			GameInfo.getInstance().setTime(GameTime.getInstance().getSeconds());
			client.saveGame("Game1",GameInfo.getInstance());
		}
		
	}

}
