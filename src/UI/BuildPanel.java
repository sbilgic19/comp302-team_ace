package UI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BuildPanel extends JPanel {

	private ImageIcon barrelIcon;
	private ImageIcon doorIcon;
	private ImageIcon jarIcon;
	private ImageIcon vaultIcon;
	private ImageIcon windowIcon;
	private ImageIcon woodenBoxIcon;
	private ImageIcon firstTreeIcon;
	private ImageIcon secondTreeIcon;
	private ImageIcon thirdTreeIcon;
	private ImageIcon woodIcon;
	private ImageIcon eraserIcon;
	
	private IconFactory iconFactory;
	
	private int panelHeight;
	private int panelWidth;

	private int buildingObjectCounter = 0;
	
	public BuildPanel(GameFrame gameFrame) {
	
		handleIcons();
		Insets insets = gameFrame.getInsets();
		
		panelWidth = 1500 - (insets.left + insets.right);
		panelHeight = 750 - (insets.top + insets.bottom);
		setPreferredSize(new Dimension(panelWidth, panelHeight)); 
		requestFocus();
		setLayout(new GridLayout(gameFrame.getNumRow(), gameFrame.getNumCol(), 0, 0));
	}
	
	private void handleIcons() {
		iconFactory = IconFactory.getInstance();
		barrelIcon = iconFactory.generateIcon("../assets/barrelIcon.png", 50, 50);
		doorIcon = iconFactory.generateIcon("../assets/doorIcon.png", 50, 50);
		jarIcon = iconFactory.generateIcon("../assets/jarIcon.png", 50, 50);
		vaultIcon = iconFactory.generateIcon("../assets/vaultIcon.png", 50, 50);
		windowIcon = iconFactory.generateIcon("../assets/windowIcon.png", 50, 50);
		woodenBoxIcon = iconFactory.generateIcon("../assets/woodenBoxIcon.png", 50, 50);
		firstTreeIcon = iconFactory.generateIcon("../assets/treeIcon1.png", 50, 50);
		secondTreeIcon = iconFactory.generateIcon("../assets/treeIcon2.png", 50, 50);
		thirdTreeIcon = iconFactory.generateIcon("../assets/treeIcon3.png", 50, 50);
		woodIcon = iconFactory.generateIcon("../assets/woodIcon.png", 50, 50);
		eraserIcon = iconFactory.generateIcon("../assets/eraserIcon.png", 50, 50);
	}

	public ImageIcon[] getIcons() {
		ImageIcon[] icons = {barrelIcon, doorIcon, jarIcon, vaultIcon, windowIcon, woodenBoxIcon, 
				firstTreeIcon, secondTreeIcon, thirdTreeIcon, woodIcon, eraserIcon};
		return icons;
	}

	public int getBuildingObjectCounter() {
		return buildingObjectCounter;
	}

	public void setBuildingObjectCounter(int buildingObjectCounter) {
		this.buildingObjectCounter = buildingObjectCounter;
	}
}