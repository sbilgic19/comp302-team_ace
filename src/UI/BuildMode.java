package UI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import Controllers.MouseHandler;
import domain.Location;

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
	
	public BuildMode(GameFrame gameFrame, BuildPanel buildPanel) {
		
		this.buildPanel = buildPanel;
		
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
	
	public int getIconNumber() {
		return icons.length;
	}
	
	public JLabel[][] getBuildModeMap() {
		return buildModeMap;
	}
	
	public void setRoomObject(Location location) {
		if (location.getLocationX() == 0 & location.getLocationY() == 5) {
			JOptionPane.showMessageDialog(null, "This location is reserved for the player!",  
					"Alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		buildModeMap[location.getLocationX()][location.getLocationY()].setIcon(selectedIcon);
		if (selectedIcon != null) {
			int count = buildPanel.getBuildingObjectCounter() + 1;
			buildPanel.setBuildingObjectCounter(count);
		} 
		else {
			int count = buildPanel.getBuildingObjectCounter() - 1;
			buildPanel.setBuildingObjectCounter(count);
		}
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
}