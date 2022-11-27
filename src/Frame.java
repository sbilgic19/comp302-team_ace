import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Frame extends JFrame {
	
	private int xPlayerPosition;
	private int yPlayerPosition;
	
	private Icon playerIcon;
	private static ImageIcon gameIcon;
	
	private JLabel[][] gameMap;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	
	private long lastSavedTime;
	private long currentTime;
	
	private String username;
	private String password;
	
	public Frame() {
		
		super("Escape From Koç");
		setLayout(new FlowLayout());
		
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		
		loginButton = new JButton("Login");
		loginButton.setFocusable(false);
		loginButton.setBackground(Color.GRAY);
		loginButton.setOpaque(false);
		add(loginButton);
		
		ButtonHandler buttonHandler = new ButtonHandler();
		loginButton.addActionListener(buttonHandler);
		
		generateGameIcon();
	}
	
	private void updateFrame() {
		remove(usernameLabel);
		remove(usernameField);
		remove(passwordLabel);
		remove(passwordField);
		remove(loginButton);
		requestFocus();
		currentTime = System.currentTimeMillis();
		setLayout(new GridLayout(10, 15, 0, 0));
		gameMap = new JLabel[10][15];
		
		for (int ii = 0; ii < 10; ii++) {
			for (int jj = 0; jj < 15; jj++) {
				gameMap[ii][jj] = new JLabel();
				add(gameMap[ii][jj]);
			}	
		}
		KeyHandler keyHandler = new KeyHandler();
		addKeyListener(keyHandler);
		
		playerIcon = generateIcon(generateURL("playerIcon.png"), 40, 40);
		gameMap[0][0].setIcon(playerIcon);
	}
	
	private class KeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent event) {
			currentTime = System.currentTimeMillis();
			if (currentTime - lastSavedTime > 40) {
				if (event.getKeyCode() == KeyEvent.VK_UP) {
					updatePlayerPosition(xPlayerPosition - 1, yPlayerPosition);
				}
				else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
					updatePlayerPosition(xPlayerPosition + 1, yPlayerPosition);
				}
				else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
					updatePlayerPosition(xPlayerPosition, yPlayerPosition - 1);
				}
				else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
					updatePlayerPosition(xPlayerPosition, yPlayerPosition + 1);
				}
				lastSavedTime = currentTime;
			}
		}
	}
	
	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (event.getActionCommand().compareTo("Login") == 0) {
				username = usernameField.getText();
				password = new String(passwordField.getPassword());
				if (username.compareTo("akural19") == 0 &&
					password.compareTo("test123") == 0) {
					updateFrame();
				}
			}	
		}
	}
	
	private void generateGameIcon() {
		gameIcon = generateIcon(generateURL("gameImage.jpg"), 0, 0);
	}
	
	public static Image getGameImage() {
		return gameIcon.getImage();
	}
	
	private ImageIcon generateIcon(URL url, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(url);
		Image image = imageIcon.getImage();
		if (width != 0 && height != 0) {
			image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		}
		return new ImageIcon(image);
	}
	
	private URL generateURL(String filename) {
		return getClass().getResource(filename);
	}
	
	private void updatePlayerPosition(int newXPlayerPosition, int newYPlayerPosition) {
		if (newXPlayerPosition >= 0 && newXPlayerPosition < 10 && newYPlayerPosition >= 0 
				&& newYPlayerPosition < 15) {  
			gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
			xPlayerPosition = newXPlayerPosition;
			yPlayerPosition = newYPlayerPosition;
			gameMap[xPlayerPosition][yPlayerPosition].setIcon(playerIcon);
		}
	}
}