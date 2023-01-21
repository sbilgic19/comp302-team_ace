import Database.Client;
import dataStructures.Location;
import domain.GameInfo;
import domain.Player;
import domain.RoomObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

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
        GameInfo.getInstance().addObjectList(objectList);

        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("testLoad1",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("testLoad1");
        // Check that the created game info same as the loaded one.

        Assert.assertEquals(GameInfo.getInstance().getTime(), gameInfo.getTime());
        Assert.assertEquals(GameInfo.getInstance().getPlayer().getLives(), gameInfo.getPlayer().getLives());
        Assert.assertEquals(GameInfo.getInstance().getCurrentObjects(), gameInfo.getCurrentObjects());

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
        GameInfo.getInstance().addObjectList(objectList);

        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("testLoadNullPlayer",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("testLoadNullPlayer");
        // Check that the created game info same as the loaded one.

        Assert.assertEquals(GameInfo.getInstance().getTime(), gameInfo.getTime());
        Assert.assertEquals(GameInfo.getInstance().getCurrentObjects(), gameInfo.getCurrentObjects());

    }
    @Test
    public void testLoadNullTime() {
        // Set up the test scenario
        Player player = new Player(5);
        RoomObject roomObject = new RoomObject(new Location(10,10),1);
        ArrayList<RoomObject> objectList = new ArrayList<RoomObject>();
        objectList.add(roomObject);

        GameInfo.getInstance().setPlayer(player);
        GameInfo.getInstance().addObjectList(objectList);

        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("testLoadNullTime",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("testLoadNullTime");
        // Check that the created game info same as the loaded one.

        Assert.assertEquals(GameInfo.getInstance().getPlayer().getLives(), gameInfo.getPlayer().getLives());
        Assert.assertEquals(GameInfo.getInstance().getCurrentObjects(), gameInfo.getCurrentObjects());

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
        GameInfo.getInstance().addObjectList(objectList);

        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("testLoadEmptyRoomObjectList",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("testLoadEmptyRoomObjectList");
        // Check that the created game info same as the loaded one.

        Assert.assertEquals(GameInfo.getInstance().getTime(), gameInfo.getTime());
        Assert.assertEquals(GameInfo.getInstance().getPlayer().getLives(), gameInfo.getPlayer().getLives());
        Assert.assertEquals(GameInfo.getInstance().getCurrentObjects(), gameInfo.getCurrentObjects());

    }

    @Test
    public void testLoadBlackBox() {
        // Set up the test scenario
        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("testLoadBlackBox",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("testLoadBlackBox");
        // Check that the created game info same as the loaded one.
        byte[] originalGameInfo = null;
        byte[] retrievedGameInfo = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(GameInfo.getInstance());
            originalGameInfo = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(gameInfo);
            retrievedGameInfo = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.equals(originalGameInfo, retrievedGameInfo));
        Assert.assertArrayEquals(originalGameInfo, retrievedGameInfo);
    }


}
