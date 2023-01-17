package UI;

import Database.Client;
import domain.GameInfo;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import Controllers.SaveGameHandler;




public class PausedGameScreen extends JDialog {
	

	private JButton saveGameButton;
	private JButton returnToGameButton;
	
	private SaveGameHandler saveGameHandler;
	
	public PausedGameScreen(SaveGameHandler saveGameHandler) {
		
		
		this.saveGameHandler = saveGameHandler;
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

//		saveGameButton.addActionListener(e->{
//			GameInfo.getInstance().setTime(GameTime.getInstance().getSeconds());
//			client.saveGame("Game1",GameInfo.getInstance());
//			
//		});
		saveGameButton.addActionListener(saveGameHandler);
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
	public SaveGameHandler getSaveGameHandler() {
		return saveGameHandler;
	}
	public void setSaveGameHandler(SaveGameHandler handler) {
		this.saveGameHandler = handler;
	}
	
}
