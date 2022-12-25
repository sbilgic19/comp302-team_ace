package UI;

import Database.Client;
import domain.GameInfo;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class LoadOrNewGameSelectionScreen extends JPanel {
	

	private JButton newGameButton;
	private JButton loadGameButton;
	private Image img;
	
	public LoadOrNewGameSelectionScreen() {

		setLayout(null);
		img = Toolkit.getDefaultToolkit().getImage("../assets/kocback.jpeg").getScaledInstance(1500, 750, Image.SCALE_SMOOTH);

		newGameButton = new JButton("New Game");
		newGameButton.setFocusable(false);
		
		loadGameButton = new JButton("Load Game");
		loadGameButton.setFocusable(false);
	
		newGameButton.setBounds(650, 325, 200, 50);
		loadGameButton.setBounds(650, 375, 200, 50);


		
		add(newGameButton);
		add(loadGameButton);
		
	
		
	}
	@Override
	public void paintComponent(Graphics g)
    {
        // Draws the img to the BackgroundPanel.
		super.paintComponent(g);
        g.drawImage(img, 0, 0,1500,750, this);
    }
//	

	public JButton getNewGameButton() {
		return newGameButton;
	}
	public JButton getLoadGameButton() {
		return loadGameButton;
	}
	
	
}
