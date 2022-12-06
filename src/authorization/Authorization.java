package authorization;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import domain.User;

public class Authorization {
	
	private ArrayList<User> recordedUsers = new ArrayList<User>(); // Temporary solution until database is constructed. It can be changed with text file. 
	private User activeUser;
	private Boolean isLoggedIn;
	private String loginMessage;
	private String registerMessage;
	
	public Authorization() {
		recordedUsers.add(new User("nsavran", "123456"));
	}

	public boolean loginAuthorization(String username, String password) {
		for(User user: this.recordedUsers){
			if (username.compareTo(user.getUsername()) == 0 &&
					password.compareTo(user.getPassword()) == 0) {
				return true;
			}
			//	else {gameFrame.giveAnErrorPopUpInTheScreen();	}
		}
		return false;
	}
	
	public boolean signupAuthorization(String username, String password, String checkPassword) {
		if(password.equals(checkPassword) && username.length() > 8 && password.length() > 8) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public void register(User user) { // methods can be protected except getters
		recordedUsers.add(user);
	}
	
	public void login(User user) { 
		isLoggedIn = true;
		activeUser = user;
	}


	public User getActiveUser() {
		return activeUser;
	}
	
	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public ArrayList<User> getRecordedUsers() {
		return recordedUsers;
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	public String getRegisterMessage() {
		return registerMessage;
	}

	public void addUserToRecord(User user){
		recordedUsers.add(user);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
