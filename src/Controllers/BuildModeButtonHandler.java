package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import UI.BuildMode;
import UI.GameFrame;

public class BuildModeButtonHandler implements ActionListener {
	
	private GameFrame gameFrame;
	private BuildMode buildMode;
	
	public BuildModeButtonHandler(GameFrame gameFrame, BuildMode buildMode) {
		this.gameFrame = gameFrame;
		this.buildMode = buildMode;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JLabel[][] buildModeMap = buildMode.getBuildModeMap();
		gameFrame.switchGameView(buildModeMap);
		buildMode.removeMouseHandler();
	}
}