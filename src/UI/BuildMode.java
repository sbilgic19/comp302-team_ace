package UI;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import Controllers.MouseHandler;
import dataStructures.Location;
import domain.GameInfo;
import domain.RoomObject;

public class BuildMode {
	
	private JLabel[][] buildModeMap;
	private ImageIcon[] icons;
	
	private int rowStep;
	private int colStep;
	private int numRow;
	private int numCol;
	
	private Border border1;
	private Border border2;
	
	private Location previous;
	private ImageIcon selectedIcon;
	
	private MouseHandler mouseHandler;
	private BuildPanel buildPanel;
	
	private ArrayList<RoomObject> objectList;
	private Location doorLocation;
	
	public BuildMode(GameFrame gameFrame, BuildPanel buildPanel) {
		
		this.buildPanel = buildPanel;
		objectList = new ArrayList<RoomObject>();
		
		border1 = BorderFactory.createDashedBorder(Color.BLUE);
		border2 = BorderFactory.createLineBorder(Color.RED);

		float frameWidth = 1468;
		float frameHeight = 674;
		
		numRow = gameFrame.getNumRow();
		numCol = gameFrame.getNumCol() + 1;
		
		buildModeMap = new JLabel[numRow][numCol];
		icons = buildPanel.getIcons();
		
		for (int ii = 0; ii < numRow; ii++) {
			for (int jj = 0; jj < numCol; jj++) {
				buildModeMap[ii][jj] = new JLabel();
				buildPanel.add(buildModeMap[ii][jj]);
				if (jj == numCol - 1) {
					if (ii < icons.length) {
						buildModeMap[ii][jj].setIcon(icons[ii]);
					}
				}
				else {
					buildModeMap[ii][jj].setBorder(border1);
				}
			}
		}
		
		rowStep = Math.round(frameHeight / numRow);
		colStep = Math.round(frameWidth / numCol);
		
		previous = new Location(14, 29);
		buildModeMap[0][5].setIcon(buildPanel.getCrossIcon());
		
		mouseHandler = new MouseHandler(this);
		buildPanel.addMouseListener(mouseHandler);
	}
	
	public void removeMouseHandler() {
		buildPanel.removeMouseListener(mouseHandler);
	}
	
	public int getRowStep() {
		return rowStep;
	}
	
	public int getColStep() {
		return colStep;
	}
	
	public int getNumRow() {
		return numRow;
	}
	
	public int getNumCol() {
		return numCol;
	}
	
	public Location getDoorLocation() {
		return doorLocation;
	}
	
	public void setDoorLocation(Location doorL) {
		this.doorLocation = doorL;
	}
	
	public int getIconNumber() {
		return icons.length;
	}
	
	public JLabel[][] getBuildModeMap() {
		return buildModeMap;
	}
	
	public ArrayList<RoomObject> getObjectList() {
		return objectList;
	}
	
	public void setRoomObject(Location location) {

		if (location.getLocationX() == 0 && location.getLocationY() == 5) {
			JOptionPane.showMessageDialog(null, "This location is reserved for the player!",  
					"Alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (doorLocation != null && previous.getLocationX() == 0) {
			JOptionPane.showMessageDialog(null, "Already placed the door!",  
					"Alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (doorLocation == null && previous.getLocationX() == 0) {
			doorLocation = location;
			GameInfo.getInstance().setDoorLocation(doorLocation);
		}
		
		int index = objectListSearch(location);
		
		if (index == -1) {
			RoomObject tempObject = new RoomObject(location, previous.getLocationX());
			objectList.add(tempObject);
		}
		else {
			if (selectedIcon == null) {
				if (objectList.get(index).getTypeID() == 0) {
					doorLocation = null;
					GameInfo.getInstance().setDoorLocation(null);
				}
				objectList.remove(index);
			}
			else {
				RoomObject tempObject = objectList.get(index);
				tempObject.setTypeID(previous.getLocationX());
			}
		}

		buildModeMap[location.getLocationX()][location.getLocationY()].setIcon(selectedIcon);
	}
	
	public void setSelectedIcon(Location location, boolean flag) {
		buildModeMap[previous.getLocationX()][previous.getLocationY()].setBorder(null);
		previous = location;
		if (flag) {
			selectedIcon = icons[location.getLocationX()];
		}
		else {
			selectedIcon = null;
		}
		buildModeMap[location.getLocationX()][location.getLocationY()].setBorder(border2);
	}
	
	private int objectListSearch(Location location) {
		for (int ii = 0; ii < objectList.size(); ii++) {
			RoomObject tempObject = objectList.get(ii);
			Location tempLocation = tempObject.getLocation();
			if (location.getLocationX() == tempLocation.getLocationX() && 
				location.getLocationY() == tempLocation.getLocationY()) {
					return ii;
			}
		}
		return -1;
	}
}