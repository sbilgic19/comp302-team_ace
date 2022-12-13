package Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import UI.BuildMode;
import domain.Location;

public class MouseHandler extends MouseAdapter {

	private BuildMode buildMode;
	
	public MouseHandler(BuildMode buildMode) {
		this.buildMode = buildMode;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		int xClickPosition = event.getX() - 8;
		int yClickPosition = event.getY() - 6;

		int rowStep = buildMode.getRowStep();
		int colStep = buildMode.getColStep();
		
		int xPosition;
		int yPosition;

		if (yClickPosition != 1469) {
			xPosition = yClickPosition / rowStep;
		}
		else {
			xPosition = yClickPosition / rowStep - 1;
		}
		if (xClickPosition != 704) {
			yPosition = xClickPosition / colStep;
		}
		else {
			yPosition = xClickPosition / colStep - 1;
		}
		
		if (yPosition == (buildMode.getNumCol() - 1)) {
			if (xPosition < buildMode.getIconNumber() - 1) {
				buildMode.setSelectedIcon(new Location(xPosition, yPosition), true);
			}
			else if (xPosition == buildMode.getIconNumber() - 1) {
				buildMode.setSelectedIcon(new Location(xPosition, yPosition), false);
			}
		}
		else {
			Location location = new Location(xPosition, yPosition);
			buildMode.setRoomObject(location);
		}
	}
}