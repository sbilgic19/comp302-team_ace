package UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PausedGameScreen extends JDialog {
	

	private JButton saveGameButton;
	private JButton returnToGameButton;
	
	
	public PausedGameScreen() {
		
		
		setLayout(new FlowLayout());
		setResizable(false);
		this.requestFocus();
		this.setAutoRequestFocus(true);
		saveGameButton = new JButton("Save Game");
		saveGameButton.setFocusable(false);
		
		returnToGameButton = new JButton("Return Back To Game");
		returnToGameButton.setFocusable(false);
	
		//saveGameButton.setBounds(650, 325, 200, 50);
		//returnToGameButton.setBounds(650, 375, 200, 50);
		
		saveGameButton.setSize(100,100);
		returnToGameButton.setSize(100, 100);
		add(saveGameButton);
		add(returnToGameButton);
		
		
		
	}

	
//	

	public JButton getSaveGameButton() {
		return saveGameButton;
	}
	public JButton getReturnToGameButton() {
		return returnToGameButton;
	}
	
	
}
