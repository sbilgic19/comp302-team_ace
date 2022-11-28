package authorization;

import change.User;
import java.util.ArrayList;

public class Authorization {
	
	static ArrayList<User> recordedUsers = new ArrayList<User>();
	static User activeUser;
	static Boolean isLoggedIn;
	static User admin = new User("admin123","Admin123@","admin@admin.com");
	
	
	
	
	
	public static void register(User user) {
		recordedUsers.add(user);
	}
	
	public static void login(User user) {
		isLoggedIn = true;
		activeUser = user;
	}

	public static ArrayList<User> getRecorded_users() {
		return recordedUsers;
	}

	public static User getActiveUser() {
		return activeUser;
	}
	
	public static Boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
