package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ApplicationLogic.AuthorizationLogic;
import UI.GameFrame;

public class NewOrLoadGameSelectionHandler implements ActionListener {

	private GameFrame gameFrame;
	
	public NewOrLoadGameSelectionHandler(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getActionCommand().compareTo("New Game") == 0) {
			gameFrame.switchBuildView();
		}else if (event.getActionCommand().compareTo("Load Game") == 0) {
			System.out.println("Needs to load saved game from database");
		}
	}

}
