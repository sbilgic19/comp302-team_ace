package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ApplicationLogic.AuthorizationLogic;
import UI.GameFrame;
import UI.LoginPanel;
import Database.Client;


public class LoginAuthorizationHandler implements ActionListener {
	
	private final GameFrame gameFrame;
	private final LoginPanel loginPanel;
	private final Client client;
	
	public LoginAuthorizationHandler(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.loginPanel = gameFrame.getLoginPanel();
		this.client = gameFrame.getGameController().getClient();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().compareTo("Login") == 0) {
			String username = loginPanel.getUsernameMessage();
			String password = loginPanel.getPasswordMessage();
			if(client.login(username, password)) {
				gameFrame.showNewLoadGameSelectionView();
			}else {
				gameFrame.showPopUpOnScreen("Alert!! Either username or password is incorrect. ", "Alert" ,JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}