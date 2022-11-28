package authorization;

//import java.util.ArrayList;
import java.util.regex.*;
import change.User;

public class AuthorizationController {
	
	
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
		for (User u : Authorization.recordedUsers) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		
		return new User(null,null);
		
	}
	
	
	
	public static String validateRegister(String username, String password) {
		if(usernameValidation(username)) {
			if(passwordValidation(password)) {
				if (! (searchUsername(username) == null)) {
				User u = new User(username,password);
				Authorization.register(u);
				return "Your account successfully registered.";
				}
				else {
					return "Username already exist";
				}
			}
			else {
				return passwordValidationMasage;
			}
		}
		return usernameValidationMasage;
		

	}
	
	
	public static String validateRegister(String username, String password, String mail) {
		if(usernameValidation(username)) {
			if(passwordValidation(password)) {
				if (searchUsername(username).equals(null)) {
					User u = new User(username,password,mail);
					Authorization.register(u);
					return "Your account successfully registered.";
				}
				else {
					return "Username already exist.";
				}
			}
			else {
				return passwordValidationMasage;
			}
		}
		return usernameValidationMasage;
		

	}
	
	
	
	public static String validateLogin(String username, String password) {
		User u = searchUsername(username);
		if (!(u == null)) {
			if(u.getPassword().equals(password)) {
				Authorization.login(u);
				return "Succcessfully logged in.";
			}
			return "There is no such a username:" + username;
		}
		return "Username or password invalid. Please try again.";
	}
	
	
	
	
	
	
	
	


}
