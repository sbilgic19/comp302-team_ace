package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import UI.BuildMode;
import UI.BuildPanel;
import UI.GameFrame;

public class BuildModeButtonHandler implements ActionListener {
	
	private GameFrame gameFrame;
	private BuildMode buildMode;
	private BuildPanel buildPanel;
	
	public BuildModeButtonHandler(GameFrame gameFrame, BuildMode buildMode, BuildPanel buildPanel) {
		this.gameFrame = gameFrame;
		this.buildMode = buildMode;
		this.buildPanel = buildPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (buildMode.getBuildPanel().getDoorFlag() == false) {
			JOptionPane.showMessageDialog(null, "You cannot start the game without adding a door to game map",  
					"Alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		if (buildPanel.getBuildingObjectCounter() > 0) {
			JLabel[][] buildModeMap = buildMode.getBuildModeMap();
			gameFrame.switchGameView(buildModeMap);
			buildMode.removeMouseHandler();
		}
	}
}