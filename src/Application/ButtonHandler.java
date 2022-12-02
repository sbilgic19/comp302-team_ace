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
			for(User user: Authorization.getRecordedUsers()){
				if (username.compareTo(user.getUsername()) == 0 &&
						password.compareTo(user.getPassword()) == 0) {
					gameFrame.switchGameView();
				}
				//	else {gameFrame.giveAnErrorPopUpInTheScreen();	}
			}
		}
	}
}