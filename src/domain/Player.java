package domain;

public class Player extends User { // User'dan extend olmal bu adda baka bir class daha var o class' User'dan extend olacak ekilde d�itirebilirsiniz.

	private int xPlayerPosition;
	private int yPlayerPosition;
	private int lives;

	public Player(String id, String password) {
		super(id, password);
		this.xPlayerPosition = 0;
		this.yPlayerPosition = 0;
		this.lives = 3;
		}

	public int getXPosition() {
		return xPlayerPosition;
	}

	public int getYPosition() {
		return yPlayerPosition;
	}
	
	/**
	 * Gets the lives of the Player.
	 * 
	 * @return lives of the Player.
	 */
	public int getLives() 
	{
		return lives;
	}
	
	public void setXPosition(int newXPlayerPosition) {
		xPlayerPosition = newXPlayerPosition;
	}

	public void setYPosition(int newYPlayerPosition) {
		yPlayerPosition = newYPlayerPosition;
	}

	/**
	 * Sets the lives of the Player.
	 * 
	 * @param lives integer to be set.
	 */
	public void setLives(int lives) 
	{
		this.lives = lives;
	}
	
	/**
	 * Decrease the number of lives of the Player by 1.
	 */
	public void decreaseLives()
	{
		if (lives > 0)
		{
			this.lives -= 1;
		}
	}
	
	/**
	 * Increase the number of lives of th ePLayer by 1.
	 */
	public void increaseLives()
	{
		this.lives += 1;
	}
}
