package ApplicationLogic;

import UI.GameFrame;
import domain.Player;

public class PlayerLivesLogic 
{

	private GameFrame gameFrame;
	
	public PlayerLivesLogic(GameFrame gameFrame) 
	{
		this.gameFrame = gameFrame;
	}
	
	public void updatePlayerLives(Player player, boolean isIncreased) {
			
			if(isIncreased)
			{
				player.increaseLives();
			} else
			{
				player.decreaseLives();
			}
			
			gameFrame.updatePlayerLivesView(player.getLives());
		}
}
