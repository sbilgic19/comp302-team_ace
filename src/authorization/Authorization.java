package authorization;

import java.util.ArrayList;


import Domain.User;

public class Authorization {
	
	private static ArrayList<User> recordedUsers = new ArrayList<User>(); // Temporary solution until database is constructed. It can be changed with text file. 
	private static User activeUser;
	private static Boolean isLoggedIn;
	private static String loginMessage;
	private static String registerMessage;
	


	public static void register(User user) { // methods can be protected except getters
		recordedUsers.add(user);
	}
	
	public static void login(User user) { 
		isLoggedIn = true;
		activeUser = user;
	}


	public static User getActiveUser() {
		return activeUser;
	}
	
	public static Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public static ArrayList<User> getRecordedUsers() {
		return recordedUsers;
	}

	public static String getLoginMessage() {
		return loginMessage;
	}

	public static String getRegisterMessage() {
		return registerMessage;
	}
	
	public static void addUserToRecord(User user){
		recordedUsers.add(user);
	}

	public static void setLoginMessage(String loginMessage) {
		Authorization.loginMessage = loginMessage;
	}

	public static void setRegisterMessage(String registerMessage) {
		Authorization.registerMessage = registerMessage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
