package authorization;

//import java.util.ArrayList;
import java.util.regex.*;

import domain.User;

public class AuthorizationHandler {
	
	
	private static String usernameValidationMasage = "WARNING: Username should contains alphanumeric characters, underscore and dot.\r\n"
			+ "Underscore and dot can't be at the end or start of a username (e.g _username / username_ / .username / username.).\r\n"
			+ "Underscore and dot can't be next to each other (e.g user_.name).\r\n"
			+ "Underscore or dot can't be used multiple times in a row (e.g user__name / user..name).\r\n"
			+ "Number of characters must be between 4 to 12.";
	
	
	private static String passwordValidationMasage = "WARNING: Password should include minimum 8 and maximum 15 characters, "
			+ "at least one uppercase letter, one lowercase letter, one number and one special character(@,$,!,%,*,?,&).";
	
	
	
	public static boolean usernameValidation(String username) {
		
		if (Pattern.matches("^(?=[a-zA-Z0-9._]{4,12}$)(?!.*[_.]{2})[^_.].*[^_.]$", username)) {
			return true;
		}
		
		
		return false;
	}
	
	public static boolean passwordValidation(String password) {
		
		if (Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", password)) { 
			return true;
		}
		
		
		return false;
	}
	
	
	
	
	
	public static User searchUsername(String username) {
		for (User u : Authorization.getRecordedUsers()) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		
		return new User(null,null); // better idea can be found ###
		
	}
	
	
	
	public static String validateRegister(String username, String password) { // one of the validateRegister can be deleted when other things has fixed. 
		String message;
		if(usernameValidation(username)) {
			if(passwordValidation(password)) {
				if (! (searchUsername(username) == null)) {
				User u = new User(username,password);
				Authorization.register(u);
				message = "Your account successfully registered.";
				Authorization.setRegisterMessage(message);
				return message;
				}
				else {
					message = "Username already exist.";
					Authorization.setRegisterMessage(message);
					return message;
				}
			}
			else {
				message = passwordValidationMasage;
				Authorization.setRegisterMessage(message);
				return message;
			}
		}
		message = usernameValidationMasage;
		Authorization.setRegisterMessage(message);
		return message;
		

	}
	
	
	public static String validateRegister(String username, String password, String mail) {
		String message;
		if(usernameValidation(username)) {
			if(passwordValidation(password)) {
				if (searchUsername(username).equals(null)) {
					message = "Your account successfully registered.";
					User u = new User(username,password,mail);
					Authorization.register(u);
					Authorization.setRegisterMessage(message);
					return message;
				}
				else {
					message = "Username already exist.";
					Authorization.setRegisterMessage(message);
					return message;
				}
			}
			else {
				message = passwordValidationMasage;
				Authorization.setRegisterMessage(message);
				return message;
			}
		}
		message = usernameValidationMasage;
		Authorization.setRegisterMessage(message);
		return message;
		

	}
	
	
	
	public static String validateLogin(String username, String password) {
		String message;
		User u = searchUsername(username);
		if (!(u == null)) {
			if(u.getPassword().equals(password)) {
				message = "Succcessfully logged in.";
				Authorization.login(u);
				Authorization.setRegisterMessage(message);
				return message;
			}
			message = "There is no such a username:" + username;
			Authorization.setRegisterMessage(message);
			return message;
		}
		message = "Username or password invalid. Please try again.";
		Authorization.setRegisterMessage(message);
		return message;
	}
	
	public static void activateAdminAccount() { // 
		User admin = new User("admin123","Admin123@","admin@admin.com");
		Authorization.register(admin);
	}
	
	
	
	
	
	
	
	
	
	
	
	


}
