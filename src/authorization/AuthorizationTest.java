package authorization;

public class AuthorizationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//AuthorizationController ac = new AuthorizationController();
		

		Boolean uv_1 = AuthorizationHandler.usernameValidation("admin123");
		System.out.println(uv_1);
		Boolean uv_2 = AuthorizationHandler.usernameValidation("Username21");
		System.out.println(uv_2);
		Boolean pv_1 = AuthorizationHandler.passwordValidation("Admin123@");
		System.out.println(pv_1);
		String username = "Username21";
		String password = "Admin123@";
		
		
		
		
		System.out.println(AuthorizationHandler.validateRegister(username, password));
		System.out.println(AuthorizationHandler.validateLogin(username, password));
		
		
		System.out.println(Authorization.getIsLoggedIn());
		System.out.println(Authorization.getActiveUser().getUsername());

	}

}
