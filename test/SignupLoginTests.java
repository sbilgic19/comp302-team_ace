import org.junit.Assert;
import org.junit.Test;

import ApplicationLogic.AuthorizationLogic;
import UI.SignUpPanel;
import domain.User;

public class SignupLoginTests {

	@Test
	public void signupcheckIfPasswordsAreNotSameGivesAnError() {
		
		User user = new User("serkanTest", "pass1", "checkPass2");
		AuthorizationLogic al = new AuthorizationLogic();
		
		boolean returnVal = al.signupAuthorization(user.getUsername(), user.getPassword(), user.getCheckPassword());

    Assert.assertFalse(returnVal);
		
	}
	
	@Test
	public void signupcheckUsernameAndPasswordHasEnoughLength() {
		User user = new User("berk", "serkan","serkan");
		
		AuthorizationLogic al = new AuthorizationLogic();
		boolean returnVal1 = al.signupAuthorization(user.getUsername(), user.getPassword(), user.getCheckPassword());
		
		
		User user2 = new User("serkan", "berk", "berk");
		boolean returnVal2 = al.signupAuthorization(user2.getUsername(), user2.getPassword(), user2.getCheckPassword());
		
		
		boolean combinedVal = returnVal1 && returnVal2;

    Assert.assertTrue(!combinedVal);
		
	}
	@Test
	public void signupCheckAttemptsWithSameUsername() {
		
		AuthorizationLogic al = new AuthorizationLogic();
		User user1 = new User("testUser", "test123@", "test123@");
		
		al.getRecordedUsers().add(user1);
		
		User user2 = new User("testUser","123@test","123@test");
		
		boolean returnVal = al.signupAuthorization(user2.getUsername(), user2.getPassword(), user2.getCheckPassword());

    Assert.assertFalse(returnVal);
		
	}
	
	@Test
	public void loginExistingUserChecker() {
		AuthorizationLogic al = new AuthorizationLogic();
		
		User user = new User("testUser", "test123@", "test123@");
		al.getRecordedUsers().add(user);
		
		
		boolean returnVal = al.loginAuthorization("testUser", "test123@");

    Assert.assertTrue(returnVal);
	}
	
	@Test
	public void loginNonExistingUserChecker() {
		AuthorizationLogic al = new AuthorizationLogic();
		
		User nonExistingAccount = new User("testUser", "test123@", "test123@");
		
		boolean returnVal = al.loginAuthorization(nonExistingAccount.getUsername(), nonExistingAccount.getPassword());

    Assert.assertFalse(returnVal);
	}
	
}
