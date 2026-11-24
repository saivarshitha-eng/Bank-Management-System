package bankmanagement;

public class InvalidTransaction  extends Exception {
	private static final long serialVersionUID = 1L;
	    public InvalidTransaction(String message) {
	        super(message);
	    }
	}

