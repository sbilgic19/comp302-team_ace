package domain;

public class User {
	private Player player;
	private String username;
	private String password;
	private String mail;
	private String checkPassword = null;
	int maxLevelReached;

	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.mail = "<default_mail>@nomail.com";
		this.maxLevelReached = 1;
	}
	
	
	public User(String username, String password, String checkPassword) { // the most common constructor
		super();
		this.username = username;
		this.password = password;
		this.checkPassword = checkPassword;
		this.mail = "<default_mail>@nomail.com";
		this.maxLevelReached = 1;
	}
	
	public User(String username, String password, String mail, int maxLevelReached) {
		super();
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.maxLevelReached = maxLevelReached;
		
	}
	
	
	public void createNewPlayer() {
		Player player1 = new Player();
		this.player = player1;
	}
	
	public void createNewPlayer(int x , int y) { // with Location
		Player player1 = new Player(x,y);
		this.player = player1;
	}
	

	
	public void updateUserInfo(String newUsername, String newPassword, String newMail) {
		this.username = newUsername;
		this.password = newPassword;
		this.mail = newMail;
	}
	
	public void levelPassed() { // when level has completed call it.
		this.maxLevelReached ++;
	}
	
	
	public Player getPlayer() {
		return this.player;
	}


	public String getUsername() {
		return username;
	}


	public void changeUsername(String username) {
		this.username = username;
	}

	public String getCheckPassword() {
		return checkPassword;
	}
	
	public String getPassword() {
		return password;
	}


	public void changePassword(String password) {
		this.password = password;
	}


	public String getName() {
		return mail;
	}


	public void changeMail(String mail) {
		this.mail = mail;
	}


	public int getMaxLevelReached() {
		return maxLevelReached;
	}


	public void setMaxLevelReached(int maxLevelReached) {
		this.maxLevelReached = maxLevelReached;
	}
	
	
	
	
	
	

}
