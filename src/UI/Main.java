package UI;
import javax.swing.*;


import Controllers.*;
import domain.GameInfo;
import domain.Player;
import domain.powerUps.PowerUpFactory;


public class Main {
	
	private static int frameWidth = 1500;
	private static int frameHeight = 750;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

	private static void createAndShowGUI(){


		GameFrame gameFrame = new GameFrame();

		gameFrame.showMainView();

		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(frameWidth, frameHeight);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
	}

}