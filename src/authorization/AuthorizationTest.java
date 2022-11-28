package authorization;

public class AuthorizationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//AuthorizationController ac = new AuthorizationController();
		

		Boolean uv_1 = AuthorizationController.usernameValidation("admin123");
		System.out.println(uv_1);
		Boolean uv_2 = AuthorizationController.usernameValidation("Username21");
		System.out.println(uv_2);
		Boolean pv_1 = AuthorizationController.passwordValidation("Admin123@");
		System.out.println(pv_1);
		String username = "Username21";
		String password = "Admin123@";
		
		
		
		
		System.out.println(AuthorizationController.validateRegister(username, password));
		System.out.println(AuthorizationController.validateLogin(username, password));
		
		
		System.out.println(Authorization.isLoggedIn);
		System.out.println(Authorization.activeUser.getUsername());

	}

}
