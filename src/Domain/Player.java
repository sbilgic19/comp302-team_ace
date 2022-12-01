package Domain;

public class Player {
	
	private int xPlayerPosition;
	private int yPlayerPosition;
	
	public Player() {
		this.xPlayerPosition = 0;
		this.yPlayerPosition = 0;
	}
	
	public int getXPosition() {
		return xPlayerPosition;
	}
	
	public int getYPosition() {
		return yPlayerPosition;
	}
	
	public void setXPosition(int newXPlayerPosition) {
		xPlayerPosition = newXPlayerPosition;
	}
	
	public void setYPosition(int newYPlayerPosition) {
		yPlayerPosition = newYPlayerPosition;
	}	
}