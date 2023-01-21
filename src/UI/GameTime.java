package UI;


import javax.swing.*;
import domain.aliens.TimeWastingAlien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTime {
	
	private TimeWastingAlien alien;
	
    private static GameTime gameTime;
    private int seconds = 0;
    private static JLabel timerAsSecond;
    private final Timer timer = createTimer();

    private GameTime() {
    
    }

    public static GameTime getInstance(){
        if(gameTime == null){
            gameTime = new GameTime();
            timerAsSecond = new JLabel();
        }

        return gameTime;
    }

    private Timer createTimer(){
        return new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seconds > 0 && !GameState.getInstance().isGameOver()) {
                    seconds--;
                    timerAsSecond.setText("      " + seconds);

                    
                } else {
                    GameState.getInstance().setPaused(true);
                    GameState.getInstance().setGameOver(true);
                    timer.stop();
                }
            }
        });
    }
    
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public JLabel getTimerAsSecond() {
        return timerAsSecond;
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean repOK() {
        return seconds >= 0;
    }


}