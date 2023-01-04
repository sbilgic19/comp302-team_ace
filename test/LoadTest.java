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
    public void testLoad() {
        // Set up the test scenario
        Player player = new Player(5);
        int time = 25;
        RoomObject roomObject = new RoomObject(new Location(10,10),1);
        ArrayList<RoomObject> objectList = new ArrayList<RoomObject>();
        objectList.add(roomObject);

        GameInfo.getInstance().setPlayer(player);
        GameInfo.getInstance().setTime(time);
        GameInfo.getInstance().setListOfObjects(objectList);

        Client client = new Client("MongoDB");
        // Save the game and then load
        client.saveGame("test1",GameInfo.getInstance());
        GameInfo gameInfo = client.loadGame("test1");
        // Check that the created game info same as the loaded one.
        Assert.assertEquals(GameInfo.getInstance().getPlayer().getLives(), gameInfo.getPlayer().getLives());
    }
}
