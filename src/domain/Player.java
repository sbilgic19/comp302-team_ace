package Domain;

public class Player extends User { // User'dan extend olmal bu adda baka bir class daha var o class' User'dan extend olacak ekilde d�itirebilirsiniz.

	private int xPlayerPosition;
	private int yPlayerPosition;

	public Player(String id, String password) {
		super(id, password);
		this.xPlayerPosition = 0;
		this.yPlayerPosition = 0;
		// TODO Auto-generated constructor stub
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
