package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.User;
import UI.GameFrame;
import authorization.Authorization;


public class LoginAuthorizationHandler implements ActionListener {
	
	private GameFrame gameFrame;
	private Authorization authLogic;
	
	public LoginAuthorizationHandler(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().compareTo("Login") == 0) {
			String username = gameFrame.getUsernameMessage();
			String password = gameFrame.getPasswordMessage();
			authLogic = new Authorization();
			if(authLogic.loginAuthorization(username, password)) {
				gameFrame.switchBuildView();
			}else {
				gameFrame.showPopUpOnScreen("Alert!! Either username or password is incorrect. ", "Alert" ,JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}