package bankmanagement;

public class customer {
	private int acno;
	private String cname;
	private String email;
	private String accountype;
	private double bal;
	public customer() {

	}
	
	public customer(int acno, String cname, String email, String accountype, double bal) {
		super();
		this.acno = acno;
		this.cname = cname;
		this.email = email;
		this.accountype = accountype;
		this.bal = bal;
	}

	public int getAcno() {
		return acno;
	}
	public void setAcno(int acno) {
		this.acno = acno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccountype() {
		return accountype;
	}
	public void setAccountype(String accountype) {
		this.accountype = accountype;
	}
	public double getBal() {
		return bal;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}

	public void displayCustomer() {
	    System.out.println("Account Number: " + acno);
	    System.out.println("Customer Name: " + cname);
	    System.out.println("Email: " + email);
	    System.out.println("Account Type: " + accountype);
	    System.out.println("Balance: " + bal);
	}
}
	
	

	
	