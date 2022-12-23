package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ApplicationLogic.AuthorizationLogic;
import domain.User;
import UI.GameFrame;
import UI.LoginPanel;


public class LoginAuthorizationHandler implements ActionListener {
	
	private GameFrame gameFrame;
	private AuthorizationLogic authLogic;
	private LoginPanel loginPanel;
	
	public LoginAuthorizationHandler(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.loginPanel = gameFrame.getLoginPanel();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().compareTo("Login") == 0) {
			String username = loginPanel.getUsernameMessage();
			String password = loginPanel.getPasswordMessage();
			authLogic = new AuthorizationLogic();
			if(authLogic.loginAuthorization(username, password)) {
				//gameFrame.switchBuildView();
				gameFrame.showNewLoadGameSelectionView();
			}else {
				gameFrame.showPopUpOnScreen("Alert!! Either username or password is incorrect. ", "Alert" ,JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}