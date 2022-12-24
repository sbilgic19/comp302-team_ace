package UI;

import Database.Client;
import domain.GameInfo;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;




public class PausedGameScreen extends JDialog {
	

	private JButton saveGameButton;
	private JButton returnToGameButton;
	private Client client;
	
	
	public PausedGameScreen(Client client) {
		
		this.client = client;
		setLayout(new FlowLayout());
		setResizable(false);
		this.requestFocus();
		this.setAutoRequestFocus(true);
		saveGameButton = new JButton("Save Game");
		saveGameButton.setFocusable(false);
		
		returnToGameButton = new JButton("Return Back To Game");
		returnToGameButton.setFocusable(false);
		
		saveGameButton.setSize(100,100);
		returnToGameButton.setSize(100, 100);

		saveGameButton.addActionListener(e->{
			GameInfo.getInstance().setTime(GameTime.getInstance().getSeconds());
			client.saveGame("Game1",GameInfo.getInstance());
		});
		add(saveGameButton);
		add(returnToGameButton);
		
	}

	
//	

	public JButton getSaveGameButton() {
		return saveGameButton;
	}
	public JButton getReturnToGameButton() {
		return returnToGameButton;
	}
	
	
}
