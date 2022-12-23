package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ApplicationLogic.AuthorizationLogic;
import domain.User;
import UI.GameFrame;


public class LoginAuthorizationHandler implements ActionListener {
	
	private GameFrame gameFrame;
	private AuthorizationLogic authLogic;
	
	public LoginAuthorizationHandler(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().compareTo("Login") == 0) {
			String username = gameFrame.getUsernameMessage();
			String password = gameFrame.getPasswordMessage();
			authLogic = new AuthorizationLogic();
			if(authLogic.loginAuthorization(username, password)) {
				gameFrame.switchBuildView();
			}else {
				gameFrame.showPopUpOnScreen("Alert!! Either username or password is incorrect. ", "Alert" ,JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}