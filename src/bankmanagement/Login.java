package bankmanagement;

public class Login {
	public boolean validateLogin(String username, String password) {

	        if (username.equals("admin") && password.equals("admin123")) {
	            return true;
	        } else {
	            return false;
	        }
	}
}
	    
	

