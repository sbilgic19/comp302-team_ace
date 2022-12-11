package UI;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTime {
    private static GameTime gameTime;
    private int seconds;
    private static JLabel timerAsSecond;
    private Timer timer = createTimer();

    public GameTime() {
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
                if (seconds > 0) {
                    seconds--;
                    timerAsSecond.setText("" + seconds);
                }else {
                    GameState.getInstance().setPaused(true);
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

    public static JLabel getTimerAsSecond() {
        return timerAsSecond;
    }

    public Timer getTimer() {
        return timer;
    }

}
