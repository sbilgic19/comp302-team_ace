package ApplicationLogic;

import java.util.ArrayList;
import java.util.Random;

import Controllers.RoomKeyHandler;
import UI.BuildMode;
import UI.GameFrame;
import UI.GamePanel;
import dataStructures.Location;
import domain.Key;
import domain.RoomObject;
import domain.aliens.*;

public class TimeWastingAlienLogic {
	
	private GameFrame gameFrame;
	private Key key;
	private Random random = new Random();
	
	
	public TimeWastingAlienLogic(GameFrame gameFrame, Key key) {
		this.gameFrame = gameFrame;
		this.key = key;
	}

	public TimeWastingAlien getTimeWastingAlien() {
		
		int numRow = gameFrame.getNumRow();
		int numCol = gameFrame.getNumCol();
		
		int randRow;
		int randCol;
		
		while (true) {
			randRow = random.nextInt(numRow);
			randCol = random.nextInt(numCol);
			if (GamePanel.getGameMap()[randRow][randCol].getIcon() == null) {
				break;
			}
		}
		GamePanel.getGameMap()[randRow][randCol].setIcon(GamePanel.getTimeWastingAlienIcon());
		TimeWastingAlien timeWastingAlien = new TimeWastingAlien(gameFrame.getObjectList(), 
				new Location(randRow, randCol), key, gameFrame.getDoorLocation());
		return timeWastingAlien;	
	}
	
	
	
}