import static org.junit.Assert.assertThrows;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import org.junit.Test;

import ApplicationLogic.PlayerMoveLogic;
import Controllers.KeyHandler;
import Controllers.LoginAuthorizationHandler;
import Controllers.PlayerHandler;
import Controllers.RoomKeyHandler;
import UI.BuildMode;
import UI.BuildPanel;
import UI.GameController;
import UI.GameFrame;
import UI.GamePanel;
import UI.IconFactory;
import dataStructures.Location;
import domain.Player;
import org.junit.Assert;
import org.junit.Test;


public class UpdatePlayerPositionTest {
	
	/* what to test ( testing PlayerMoveLogic.updatePlayerPosition() )
	 * start with BB then continue with GB 
	 * note that some of GB tests will be already covered by BB
	 * 
	 * NullPointerException
	 * true/false cases, corner cases
	 */
	
	GameFrame gameFrame;
	PlayerMoveLogic playerMoveLogic;
	GameController gameController;
	
	
	public GameFrame init(Player player) {
		// intilize gameFrame
		IconFactory iconFactory = new IconFactory();
		
		GameFrame gameFrame = new GameFrame(gameController);
		gameController.switchBuildView();
		BuildPanel buildPanel = new BuildPanel(gameFrame);
		BuildMode buildMode = new BuildMode(gameFrame,buildPanel);
		buildMode.setDoorLocation(new Location(5,5));
		buildMode.getBuildModeMap()[5][5].setIcon(iconFactory.generateIcon("../assets/timeWastingAlienIcon.png", 50, 50));//door
		buildMode.getBuildModeMap()[0][1].setIcon(iconFactory.generateIcon("../assets/timeWastingAlienIcon.png", 50, 50));//object1
		buildMode.getBuildModeMap()[6][6].setIcon(iconFactory.generateIcon("../assets/timeWastingAlienIcon.png", 50, 50));//object2
		buildMode.getBuildModeMap()[7][7].setIcon(iconFactory.generateIcon("../assets/timeWastingAlienIcon.png", 50, 50));//object3
		gameFrame.setBuildMode(buildMode);

		RoomKeyHandler roomKeyHandler = new RoomKeyHandler(gameFrame, player);
		LoginAuthorizationHandler buttonHandler = new LoginAuthorizationHandler(gameFrame);
		PlayerHandler playerHandler = new PlayerHandler(player, gameFrame);
		KeyHandler keyHandler = new KeyHandler(playerHandler);
		
		gameFrame.setButtonHandler(buttonHandler);
		gameFrame.setKeyHandler(keyHandler);
		gameFrame.setRoomKeyHandler(roomKeyHandler);
		
		
		GamePanel gamePanel = new GamePanel(gameFrame);
		gamePanel.setGameMap(buildMode.getBuildModeMap());
		
		return gameFrame;
	}

	

	

	@Test
	public void expectedNullPointerException() {

		Player player = null;
		GameFrame gameFrame = init(player);
		PlayerMoveLogic playerMoveLogic = new PlayerMoveLogic(gameFrame);
		
		
		assertThrows(NullPointerException.class, ()-> {
			playerMoveLogic.updatePlayerPosition(player, 1, 0);  // BB throws exception
		});
		
		Player player2 = new Player();
		GameFrame gameFrame2 = init(player2);
		PlayerMoveLogic playerMoveLogic2 = new PlayerMoveLogic(gameFrame2);
		
		assertThrows(NullPointerException.class, ()-> {
			playerMoveLogic2.updatePlayerPosition(player2, 1, 0); // BB throws exception
		});
		

	}
	
	@Test
	public void LocationmMustBeUpdated() {
	

		int positionX = 3;
		int positionY = 4;
		Player player = new Player();
		
		GameFrame gameFrame = init(player);
		PlayerMoveLogic playerMoveLogic = new PlayerMoveLogic(gameFrame);
		
		
		
		//moves through empty place
		playerMoveLogic.updatePlayerPosition(player, 0, 1);
		//System.out.println("X1: "+player.getLocation().getLocationX()+" Y1: "+player.getLocation().getLocationY());
		Assert.assertEquals(player.getLocation().getLocationX(), positionX); // GB x coordinates should not be changed
		Assert.assertEquals(player.getLocation().getLocationY(), positionY+1); // GB y coordinates should be increased
		
		//moves through empty place
		playerMoveLogic.updatePlayerPosition(player, 1, 0);
		//System.out.println("X1: "+player.getLocation().getLocationX()+" Y1: "+player.getLocation().getLocationY());
		Assert.assertEquals(player.getLocation().getLocationX(), positionX+1); // GB x coordinates should not be changed
		Assert.assertEquals(player.getLocation().getLocationY(), positionY+1); // GB y coordinates should be increased
	
	
			
	}
	
	
	

	@Test
	public void LocationStaySame() {
		

		
		
		
		//try to go outside of the map
		
		int positionX2 = 0;
		int positionY2 = 0;
		
		Player player2 = new Player();
		GameFrame gameFrame = init(player2);
		PlayerMoveLogic playerMoveLogic = new PlayerMoveLogic(gameFrame);

		
		playerMoveLogic.updatePlayerPosition(player2, 0, -1);
		//System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
		Assert.assertEquals(player2.getLocation().getLocationX(), positionX2); // GB Corner Case
		Assert.assertEquals(player2.getLocation().getLocationY(), positionY2); // GB Corner Case
		
		playerMoveLogic.updatePlayerPosition(player2, -1, 0);
		//System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
		Assert.assertEquals(player2.getLocation().getLocationX(), positionX2); // GB Corner Case
		Assert.assertEquals(player2.getLocation().getLocationY(), positionY2); // GB Corner Case
		
		
		// Tries to moves through an object
		
		
		playerMoveLogic.updatePlayerPosition(player2, 0, 1);
		//System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
		Assert.assertEquals(player2.getLocation().getLocationX(), positionX2); // GB x coordinates should not be changed
		Assert.assertEquals(player2.getLocation().getLocationY(), positionY2); // GB y coordinates should not be changed
		
	

		
		// Tries to moves more than one step
		
		int positionX = 3;
		int positionY = 4;
		Player player = new Player();
		
		
		playerMoveLogic.updatePlayerPosition(player, 2, 0);
		//System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
		Assert.assertEquals(player.getLocation().getLocationX(), positionX); //GB x coordinates should not be changed
		Assert.assertEquals(player.getLocation().getLocationY(), positionY); //GB y coordinates should not be changed
		
		playerMoveLogic.updatePlayerPosition(player, 0, 2);
		//System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
		Assert.assertEquals(player.getLocation().getLocationX(), positionX); //GB x coordinates should not be changed
		Assert.assertEquals(player.getLocation().getLocationY(), positionY); //GB y coordinates should not be changed
		
		
		playerMoveLogic.updatePlayerPosition(player, 3, 4);
		//System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
		Assert.assertEquals(player.getLocation().getLocationX(), positionX); // GB x coordinates should not be changed
		Assert.assertEquals(player.getLocation().getLocationY(), positionY); // GB y coordinates should not be changed
		
		playerMoveLogic.updatePlayerPosition(player, 1, 1);
		//System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
		Assert.assertEquals(player.getLocation().getLocationX(), positionX); // GB x coordinates should not be changed
		Assert.assertEquals(player.getLocation().getLocationY(), positionY); // GB y coordinates should not be changed
		
		playerMoveLogic.updatePlayerPosition(player, -1, -1);
		//System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
		Assert.assertEquals(player.getLocation().getLocationX(), positionX); // GB x coordinates should not be changed
		Assert.assertEquals(player.getLocation().getLocationY(), positionY); // GB y coordinates should not be changed
		
		playerMoveLogic.updatePlayerPosition(player, 0, -2);
		//System.out.println("X: "+player.getLocation().getLocationX()+" Y: "+player.getLocation().getLocationY());
		Assert.assertEquals(player.getLocation().getLocationX(), positionX); // GB x coordinates should not be changed
		Assert.assertEquals(player.getLocation().getLocationY(), positionY); // GB y coordinates should not be changed
		
		


		
		
	}

}
