package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.GameFrame;

public class ButtonHandler implements ActionListener {
	
	private GameFrame gameFrame;
	
	public ButtonHandler(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().compareTo("Login") == 0) {
			String username = gameFrame.getUsernameMessage();
			String password = gameFrame.getPasswordMessage();
			if (username.compareTo("akural19") == 0 &&
				password.compareTo("test123") == 0) {
				gameFrame.switchGameView();
			}
		}	
	}
}