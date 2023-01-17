package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import UI.BuildMode;
import UI.BuildPanel;
import UI.GameController;
import UI.GameFrame;
import domain.GameInfo;

public class BuildModeButtonHandler implements ActionListener {
	
	//private GameFrame gameFrame;
	private BuildMode buildMode;
	private BuildPanel buildPanel;
	private GameController gameController;
	
	public BuildModeButtonHandler(GameController gameController, BuildMode buildMode, BuildPanel buildPanel) {
		this.gameController = gameController;
		this.buildMode = buildMode;
		this.buildPanel = buildPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		JLabel[][] buildModeMap = buildMode.getBuildModeMap();
		int counter = 0;
		
		for (int ii = 0; ii < gameController.getGameFrame().getNumRow(); ii++) {
			for (int jj = 0; jj < gameController.getGameFrame().getNumCol(); jj++) {
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
			GameInfo.getInstance().setListOfObjects(buildMode.getObjectList());
			gameController.switchGameView(buildModeMap);
			buildMode.removeMouseHandler();
		}
		else {
			JOptionPane.showMessageDialog(null, "Required at least one room object except room door!",  
					"Alert", JOptionPane.ERROR_MESSAGE);
		}
		//GameInfo.getInstance().setListOfObjects(buildMode.getObjectList());
//		for (int i=0; i<buildMode.getObjectList().size(); i++) {
//			System.out.printf("Location of the object is x:%d, y:%d, \n", buildMode.getObjectList().get(i).getLocation().getLocationX(), buildMode.getObjectList().get(i).getLocation().getLocationY());
//		}
	}
}