package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import UI.*;
import domain.GameInfo;

public class BuildModeButtonHandler implements ActionListener {
	
	//private GameFrame gameFrame;
	private final BuildMode buildMode;
	private final BuildPanel buildPanel;
	private final GameController gameController;
	
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
		else if (validObjectCount(buildPanel.getBuildingObjectCounter())) {
			GameInfo.getInstance().addObjectList(buildMode.getObjectList());
			GameInfo.getInstance().levelUp();
			if(GameInfo.getInstance().getListOfObjectsOfAllLevels().size() == 6){
				GameInfo.getInstance().setCurrentLevel(1);
				gameController.switchGameView(gameController.arrayToMatrix(GameInfo.getInstance().getCurrentObjects()));
				buildMode.removeMouseHandler();
			} else {
				gameController.updateLevelView();
				buildMode.addNewLevel();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Required more room objects",
					"Alert", JOptionPane.ERROR_MESSAGE);
		}
	}
	public boolean validObjectCount(int objectCount){

		int currentLevel = GameInfo.getInstance().getCurrentLevel();

		switch (currentLevel){
			case 1:
				return objectCount >= 5;
			case 2:
				return objectCount >= 7;
			case 3:
				return objectCount >= 10;
			case 4:
				return objectCount >= 14;
			case 5:
				return objectCount >= 19;
			case 6:
				return objectCount >= 25;
			default:
				return false;
		}
	}
}