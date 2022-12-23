package ApplicationLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import domain.User;

public class AuthorizationLogic {
	
	private ArrayList<User> recordedUsers = new ArrayList<User>(); // Temporary solution until database is constructed. It can be changed with text file. 
	private User activeUser;
	private Boolean isLoggedIn;
	private String loginMessage;
	private String registerMessage;
	private FileWriter fwriter;
	private FileReader freader;
	
	
	public AuthorizationLogic() {
		recordedUsers.add(new User("nsavran", "123456"));
		try {
			//this.fwriter = new FileWriter("signedUser.txt", true);
			this.freader = new FileReader("signedUser.txt");
			BufferedReader bufferedReader = new BufferedReader(freader);
			 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) {
                String[] userInfo = line.split(" ", 2);
                User newUser = new User(userInfo[0], userInfo[1]);
                recordedUsers.add(newUser);
            }
            freader.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
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
		for(User user: this.recordedUsers) {
			if(username.toLowerCase().compareTo(user.getUsername().toLowerCase()) == 0) {
				return false;
			}
		}
		if(password.equals(checkPassword) && username.length() > 6 && password.length() > 6) {
			User newUser = new User(username, password);
			recordedUsers.add(newUser);
			try {
				this.fwriter = new FileWriter("signedUser.txt", true);
				this.fwriter.write(username + " " + password);
				this.fwriter.write("\r\n");
				this.fwriter.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			
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
