package bankmanagement;

import java.sql.Timestamp;

public class Transaction {
	private int tid;
	private String transaction_type;
	private int acno;
	private double amount;
	private Timestamp transaction_date;
	public Transaction() {
	}
	public Transaction(int tid, String transaction_type, int acno, double amount, Timestamp transaction_date) {
		this.tid= tid;
		this.transaction_type = transaction_type;
		this.acno = acno;
		this.amount = amount;
		this.transaction_date = transaction_date;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public int getAcno() {
		return acno;
	}
	public void setAcno(int acno) {
		this.acno = acno;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Timestamp transaction_date) {
		this.transaction_date = transaction_date;
	}
	
	
}
