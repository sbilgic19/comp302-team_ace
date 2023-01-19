package domain.powerUps;

import java.io.Serializable;

/*
 * Implementation of Strategy pattern
 */
public interface PowerUpBehavior extends Serializable {
	public void performBehavior();
}
