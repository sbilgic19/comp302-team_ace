import UI.GameTime;
import dataStructures.Location;
import domain.aliens.TimeWastingAlien;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class GameTimeTest {
  private GameTime gameTime;
  private TimeWastingAlien alien;

  /**
   * Gets the GameTime and creates a timewasting alien for testing.
   */
  @Before
  public void setUp() {
    gameTime = GameTime.getInstance();
    alien = new TimeWastingAlien(new Location(0, 0));
  }

  @Test
  public void testSingleton() {
    // Ensure that there is only one instance of GameTime
    GameTime gt1 = GameTime.getInstance();
    GameTime gt2 = GameTime.getInstance();
    assertEquals(gt1, gt2);
  }

  @Test
  public void testGetInstance() {
    assertNotNull(gameTime);
    assertEquals(gameTime, GameTime.getInstance());
  }

  @Test
  public void testGetSeconds() {
    gameTime.setSeconds(10);
    assertEquals(10, gameTime.getSeconds());
  }

  @Test
  public void testSetSeconds() {
    gameTime.setSeconds(5);
    assertEquals(5, gameTime.getSeconds());
  }

  @Test
  public void testGetTimerAsSecond() {
    assertNotNull(gameTime.getTimerAsSecond());
  }

  @Test
  public void testGetTimer() {
    assertNotNull(gameTime.getTimer());
  }



  @Test
  public void testRepOK() {
    gameTime.setSeconds(10);
    assertTrue(gameTime.repOK());
    gameTime.setSeconds(-1);
    assertFalse(gameTime.repOK());
  }
}