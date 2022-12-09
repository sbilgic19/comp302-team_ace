package UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.*;

public class GameFrame extends JFrame {
	private int numRow = 30;
	private int numCol = 45;
	private GamePanel gamePanel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton pauseButton;
	private JButton resumeButton;
	private IconFactory iconFactory;
	private ImageIcon gameIcon;
	private Image gameImage;
	private LoginAuthorizationHandler buttonHandler;
	private KeyHandler keyHandler;
	
	private MainScreenPanel mainScreen;
	private MainScreenPanelButtonsHandler mainButtonHandler;

	private SignUpPanel signupPanel;
	private SignUpButtonHandler signupHandler;

	private Timer timer;
	private JLabel timerAsSecond;
	private int second;


	public GameFrame() {
		
		super("Escape From Koç");
		
		iconFactory = IconFactory.getInstance();
		gameIcon = iconFactory.generateIcon("../assets/gameImage.jpg", 0, 0);
		gameImage = gameIcon.getImage();
		setIconImage(gameImage);
		
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		
		loginButton = new JButton("Login");
		loginButton.setFocusable(false);
		loginButton.setBackground(Color.GRAY);
		loginButton.setOpaque(false);

	}
	
	public void switchLoginView() {
		mainScreen.setVisible(false);
		mainScreen.setEnabled(false);
		remove(mainScreen);
		
		requestFocus();
		setLayout(new FlowLayout());
		
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(loginButton);
		
		loginButton.addActionListener(buttonHandler);
	}
	public void switchSignUpView() {
		mainScreen.setVisible(false);
		mainScreen.setEnabled(false);
		remove(mainScreen);
		
		signupPanel = new SignUpPanel();
		signupHandler = new SignUpButtonHandler(this);
		add(signupPanel);
		signupPanel.getSignupButton().addActionListener(signupHandler);
		signupPanel.setVisible(true);
		
		requestFocus();
		
	}
	
		
	public void showMainView() {
		mainScreen = new MainScreenPanel();
		add(mainScreen);
		mainButtonHandler = new MainScreenPanelButtonsHandler(this);
		mainScreen.setVisible(true);
		mainScreen.getLoginButton().addActionListener(mainButtonHandler);
		mainScreen.getSigninButton().addActionListener(mainButtonHandler);
	}
	
	public void setButtonHandler(LoginAuthorizationHandler buttonHandler) {
		this.buttonHandler = buttonHandler;
	}

	public void setKeyHandler(KeyHandler keyHandler) {
		this.keyHandler = keyHandler;
	}

	// Sign Up Check i için
	public String getSignupUsername() {
		return signupPanel.getSignupUsername();
	}
	
	public String getSignupPassword() {
		return signupPanel.getSignupPassword();
	}
	
	public String getSignupCheckPassword() {
		return signupPanel.getSignupCheckPassword();
	}
	//
	
	public String getUsernameMessage() {
		return usernameField.getText();
	}
	
	public String getPasswordMessage() {
		return new String(passwordField.getPassword());
	}

	public int getNumRow(){return numRow;}

	public int getNumCol(){return numCol;}

	public void switchGameView() {
		remove(usernameLabel);
		remove(usernameField);
		remove(passwordLabel);
		remove(passwordField);
		remove(loginButton);

		setLayout(new BorderLayout());

		pauseButton = new JButton("II");
		pauseButton.setFocusable(false);
		pauseButton.setSize(50,50);

		resumeButton = new JButton(">");
		resumeButton.setFocusable(false);
		resumeButton.setVisible(false);
		resumeButton.setSize(50,50);


		second = 25;
		timer = createTimer();
		timer.start();

		pauseButton.addActionListener(e -> {
			pauseButton.setVisible(false);
			GameState.getInstance().setPaused(true);
			timer.stop();
			resumeButton.setVisible(true);
		});

		resumeButton.addActionListener(e -> {
			resumeButton.setVisible(false);
			GameState.getInstance().setPaused(false);
			timer.start();
			pauseButton.setVisible(true);
		});


		this.add(pauseButton,BorderLayout.NORTH);
		this.add(resumeButton,BorderLayout.EAST);
		gamePanel = new GamePanel();
		gamePanel.setSize(1500,850);
		gamePanel.setVisible(true);
		this.add(gamePanel,BorderLayout.CENTER);

		gamePanel.setGameMap();
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		gamePanel.addKeyListener(keyHandler);



		timerAsSecond = new JLabel();
		timerAsSecond.setSize(50,50);

		add(timerAsSecond,BorderLayout.SOUTH);

	}

	private Timer createTimer(){
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				timerAsSecond.setText(""+ second);
			}
		});
		return timer;
	}
	public void showPopUpOnScreen(String message, String popUpType, int MessageType) {
		JOptionPane.showMessageDialog(new JFrame(), message, popUpType,
				MessageType);
	}
}