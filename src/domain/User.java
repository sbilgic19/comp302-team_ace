package Domain;

public class User {
	private String username;
	private String password;
	private String mail;
	int maxLevelReached;

	
	public User(String id, String password) {
		super();
		this.username = id;
		this.password = password;
		this.mail = "<default_mail>@nomail.com";
		this.maxLevelReached = 1;
	}
	
	
	public User(String id, String password, String mail) { // the most common constructor
		super();
		this.username = id;
		this.password = password;
		this.mail = mail;
		this.maxLevelReached = 1;
	}
	
	public User(String id, String password, String mail, int maxLevelReached) {
		super();
		this.username = id;
		this.password = password;
		this.mail = mail;
		this.maxLevelReached = maxLevelReached;
		
	}
	
	public void updateUserInfo(String newId, String newPassword, String newMail) {
		this.username = newId;
		this.password = newPassword;
		this.mail = newMail;
	}
	
	public void levelPassed() { // when level has completed call it.
		this.maxLevelReached ++;
	}


	public String getUsername() {
		return username;
	}


	public void changeUsername(String id) {
		this.username = id;
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
