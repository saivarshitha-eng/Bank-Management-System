package bankmanagement;

public class InsufficientFunds extends Exception {
	private static final long serialVersionUID = 1L;

	    public InsufficientFunds(String message) {
	        super(message);
	    }
	}

