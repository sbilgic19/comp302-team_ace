
import java.util.ArrayList;

import ApplicationLogic.KeyLogic;
import ApplicationLogic.TimeWastingAlienBehaviourStrategy;
import Controllers.TimeWastingAlienHandler;
import UI.GameController;
import UI.GameFrame;
import dataStructures.Location;
import domain.Key;
import domain.RoomObject;
import domain.aliens.TimeWastingAlien;

import org.junit.Assert;
import org.junit.Test;

public class TimeWastingAlienBehaviourTest {
	// Tests on the changeLocationOfTheKey method of the TimeWastingAlienBehaviourStrategy interface
	@Test 
	public void testBehaviourAGlassBox() {
		// Remaining Game Time < 30%
		// REQUIRES: Room objects, key and time wasting alien are created.
		
		ArrayList<RoomObject> objectList = new ArrayList<RoomObject>();
		objectList.add(new RoomObject(new Location(3, 18), 1));
		objectList.add(new RoomObject(new Location(3, 19), 1));
		objectList.add(new RoomObject(new Location(3, 20), 1));
		objectList.add(new RoomObject(new Location(3, 21), 1));
		objectList.add(new RoomObject(new Location(3, 22), 1));
		
		Location initialLocation = new Location(3, 18);
		Key key = new Key(initialLocation);
		GameController gameController = new GameController();
		TimeWastingAlienHandler alienHandler = new TimeWastingAlienHandler(gameController);
		TimeWastingAlien alien = alienHandler.getTimeWastingAlien();
		TimeWastingAlienBehaviourStrategy behaviourA = alien.getBehaviours()[0];
		behaviourA.setFieldInstances(objectList, key,alienHandler.getAlienLogic());
		
		// MODIFIES: isActive instance field of the time wasting alien and 
		// location instance field of the key
		
		alien.triggerAction(behaviourA);
		
		boolean test1 = key.getLocation().equals(initialLocation);
		Assert.assertEquals(test1, false);
		
		boolean test2 = alien.getIsActive();
		Assert.assertEquals(test2, false);
		
		// EFFECTS: After one calls on the changeLocationOfTheKey method of 
		// TimeWastingAlienBehaviourStrategyA by using triggerAction method of the alien, 
		// it is guaranteed that the isActive instance field of the alien becomes false and 
		// the location of the key changes. 
	}
	
	@Test
	public void testBehaviourBGlassBox() {
		// Remaining Game Time > 70%
		// REQUIRES: Room objects, key and time wasting alien are created.
		
		ArrayList<RoomObject> objectList = new ArrayList<RoomObject>();
		objectList.add(new RoomObject(new Location(3, 18), 1));
		objectList.add(new RoomObject(new Location(3, 19), 1));
		objectList.add(new RoomObject(new Location(3, 20), 1));
		objectList.add(new RoomObject(new Location(3, 21), 1));
		objectList.add(new RoomObject(new Location(3, 22), 1));
		  
		Location initialLocation = new Location(3, 18);
		Key key = new Key(initialLocation);
		

		GameController gameController = new GameController();
		TimeWastingAlienHandler alienHandler = new TimeWastingAlienHandler(gameController);
		TimeWastingAlien alien = alienHandler.getTimeWastingAlien();
		TimeWastingAlienBehaviourStrategy behaviourB = alien.getBehaviours()[1];
		behaviourB.setFieldInstances(objectList, key,alienHandler.getAlienLogic());
		
		// MODIFIES: location instance field of the key
		
		for (int ii = 0; ii < 3; ii++) {
			alien.triggerAction(behaviourB);
		}
		
		boolean test1 = key.getLocation().equals(initialLocation);
		Assert.assertEquals(test1, false);
		
		boolean test2 = alien.getIsActive();
		Assert.assertEquals(test2, true);
		
		// EFFECTS: After three calls on the changeLocationOfTheKey method of 
		// TimeWastingAlienBehaviourStrategyB by using triggerAction method of the alien, 
		// it it guaranteed that the isActive instance field of the alien is still true but 
		// the location of the key changes. 
	}
	
	@Test 
	public void testBehaviourCGlassBox() {
		// 70% >= Remaining Game Time >= 30%
		// REQUIRES: Room objects, key and time wasting alien are created.
		
		ArrayList<RoomObject> objectList = new ArrayList<RoomObject>();
		objectList.add(new RoomObject(new Location(3, 18), 1));
		objectList.add(new RoomObject(new Location(3, 19), 1));
		objectList.add(new RoomObject(new Location(3, 20), 1));
		objectList.add(new RoomObject(new Location(3, 21), 1));
		objectList.add(new RoomObject(new Location(3, 22), 1));
		  
		Location initialLocation = new Location(3, 18);
		Key key = new Key(initialLocation);
		
		GameController gameController = new GameController();
		TimeWastingAlienHandler alienHandler = new TimeWastingAlienHandler(gameController);
		TimeWastingAlien alien = alienHandler.getTimeWastingAlien();
		TimeWastingAlienBehaviourStrategy behaviourC = alien.getBehaviours()[2];
		behaviourC.setFieldInstances(objectList, key,alienHandler.getAlienLogic());
		
		// MODIFIES: isActive instance field of the time wasting alien
		
		for (int ii = 0; ii < 2; ii++) {
			alien.triggerAction(behaviourC);
		}
		
		boolean test1 = key.getLocation().equals(initialLocation);
		Assert.assertEquals(test1, true);
		
		boolean test2 = alien.getIsActive();
		Assert.assertEquals(test2, false);
		
		// EFFECTS: After two calls on the changeLocationOfTheKey method of 
		// TimeWastingAlienBehaviourStrategyC by using triggerAction method of the alien, 
		// it is guaranteed that the isActive instance field of the alien becomes false and 
		// the location of the key does not change. 
	}
	
}
