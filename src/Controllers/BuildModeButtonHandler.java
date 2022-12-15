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
		
		JLabel[][] buildModeMap = buildMode.getBuildModeMap();
		int counter = 0;
		
		for (int ii = 0; ii < gameFrame.getNumRow(); ii++) {
			for (int jj = 0; jj < gameFrame.getNumCol(); jj++) {
				if (buildModeMap[ii][jj].getIcon() != null) {
					counter++;
				}
			}
		}
		counter -= 2; 
		buildPanel.setBuildingObjectCounter(counter);
				
		if (buildMode.getDoorLocation() == null) {
			JOptionPane.showMessageDialog(null, "Room door is not placed!",  
					"Alert", JOptionPane.ERROR_MESSAGE);
		}
		else if (buildPanel.getBuildingObjectCounter() > 0) {
			gameFrame.switchGameView(buildModeMap);
			buildMode.removeMouseHandler();
		}
		else {
			JOptionPane.showMessageDialog(null, "Required at least one room object except room door!",  
					"Alert", JOptionPane.ERROR_MESSAGE);
		}
	}
}