import Database.Client;
import UI.GameTime;
import dataStructures.Location;
import domain.GameInfo;
import domain.Player;
import domain.RoomObject;
import domain.powerUps.ExtraTimePowerUp;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class LoadTest {
    @Test
    public void testLoad1() {
        // Set up the test scenario
        Player player = new Player(5);
        int time = 16;
        RoomObject roomObject = new RoomObject(new Location(10,10),1);
        ArrayList<RoomObject> objectList = new ArrayList<RoomObject>();
        objectList.add(roomObject);

        GameInfo.getInstance().setPlayer(player);
        GameInfo.getInstance().setTime(time);
        GameInfo.getInstance().setListOfObjects(objectList);

        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("testLoad1",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("testLoad1");
        // Check that the created game info same as the loaded one.

        Assert.assertEquals(GameInfo.getInstance().getTime(), gameInfo.getTime());
        Assert.assertEquals(GameInfo.getInstance().getPlayer().getLives(), gameInfo.getPlayer().getLives());
        Assert.assertTrue(GameInfo.getInstance().getListOfObjects().equals(gameInfo.getListOfObjects()));

    }
    @Test
    public void testLoadNullPlayer() {
        // Set up the test scenario
        Player player = null;
        int time = 16;
        RoomObject roomObject = new RoomObject(new Location(10,10),1);
        ArrayList<RoomObject> objectList = new ArrayList<RoomObject>();
        objectList.add(roomObject);

        GameInfo.getInstance().setPlayer(player);
        GameInfo.getInstance().setTime(time);
        GameInfo.getInstance().setListOfObjects(objectList);

        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("testLoadNullPlayer",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("testLoadNullPlayer");
        // Check that the created game info same as the loaded one.

        Assert.assertEquals(GameInfo.getInstance().getTime(), gameInfo.getTime());
        Assert.assertTrue(GameInfo.getInstance().getListOfObjects().equals(gameInfo.getListOfObjects()));

    }
    @Test
    public void testLoadNullTime() {
        // Set up the test scenario
        Player player = new Player(5);
        RoomObject roomObject = new RoomObject(new Location(10,10),1);
        ArrayList<RoomObject> objectList = new ArrayList<RoomObject>();
        objectList.add(roomObject);

        GameInfo.getInstance().setPlayer(player);
        GameInfo.getInstance().setListOfObjects(objectList);

        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("testLoadNullTime",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("testLoadNullTime");
        // Check that the created game info same as the loaded one.

        Assert.assertEquals(GameInfo.getInstance().getPlayer().getLives(), gameInfo.getPlayer().getLives());
        Assert.assertTrue(GameInfo.getInstance().getListOfObjects().equals(gameInfo.getListOfObjects()));

    }

    @Test
    public void testLoadNullRoomObjectList() {
        // Set up the test scenario
        Player player = new Player(5);
        int time = 25;


        GameInfo.getInstance().setPlayer(player);
        GameInfo.getInstance().setTime(time);

        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("testLoadNullRoomObjectList",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("testLoadNullRoomObjectList");
        // Check that the created game info same as the loaded one.

        Assert.assertEquals(GameInfo.getInstance().getPlayer().getLives(), gameInfo.getPlayer().getLives());
        Assert.assertEquals(GameInfo.getInstance().getTime(), gameInfo.getTime());

    }

    @Test
    public void testLoadEmptyRoomObjectList() {
        // Set up the test scenario
        Player player = new Player(5);
        int time = 16;
        ArrayList<RoomObject> objectList = new ArrayList<RoomObject>();

        GameInfo.getInstance().setPlayer(player);
        GameInfo.getInstance().setTime(time);
        GameInfo.getInstance().setListOfObjects(objectList);

        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("testLoadEmptyRoomObjectList",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("testLoadEmptyRoomObjectList");
        // Check that the created game info same as the loaded one.

        Assert.assertEquals(GameInfo.getInstance().getTime(), gameInfo.getTime());
        Assert.assertEquals(GameInfo.getInstance().getPlayer().getLives(), gameInfo.getPlayer().getLives());
        Assert.assertTrue(GameInfo.getInstance().getListOfObjects().equals(gameInfo.getListOfObjects()));

    }


}
