package bankmanagement;


    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;

    public class BankService {

        
        public void createAccount(customer customer) throws SQLException {

            String sql = "INSERT INTO customer "
                    + "(acno, cname, email, accountype, bal) "
                    + "VALUES (?, ?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, customer.getAcno());
                ps.setString(2, customer.getCname());
                ps.setString(3, customer.getEmail());
                ps.setString(4, customer.getAccountype());
                ps.setDouble(5, customer.getBal());

                ps.executeUpdate();

                System.out.println("Account created successfully!");
            }
        }
        
        public List<customer> viewAccounts() throws SQLException {

            List<customer> customerList = new ArrayList<>();

            String sql = "SELECT * FROM customer";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    customer cust = new customer(
                            rs.getInt("acno"),
                            rs.getString("cname"),
                            rs.getString("email"),
                            rs.getString("accountype"),
                            rs.getDouble("bal")
                    );

                    customerList.add(cust);
                }
            }

            return customerList;
        }

public customer searchAccount(int acno)
        throws SQLException, InvalidAccount {

    String sql = "SELECT acno, cname, bal FROM customer WHERE acno = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, acno);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return new customer(
                        rs.getInt("acno"),
                        rs.getString("cname"),
                        null,
                        null,
                        rs.getDouble("bal")
                );
            } else {
                throw new InvalidAccount("Account number does not exist");
            }
        }
    }
}
  
public void performTransaction(int acno, char type, double amt)
        throws SQLException, InvalidWithdraw, InvalidDeposit,
        InvalidTransaction, InsufficientFunds,InvalidAccount {

    String sql = "SELECT bal FROM customer WHERE acno = ?";

    try (Connection con = DBConnection.getConnection()) {

        con.setAutoCommit(false);

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, acno);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    throw new InvalidAccount("Account number does not exist");
                }

                double bal = rs.getDouble("bal");

                String updateSql;

                if (type == 'w' || type == 'W') {

                    if (amt < 100) {
                        throw new InvalidWithdraw(
                            "Withdrawal amount must be at least 100");
                    }

                    if (amt > bal) {
                        throw new InsufficientFunds("Insufficient balance");
                    }

                    updateSql =
                        "UPDATE customer SET bal = bal - ? WHERE acno = ?";

                    System.out.println("Withdrawal successful");
                    System.out.println("Withdrawn amount: " + amt);
                    System.out.println("Previous balance: " + bal);
                    System.out.println("New balance: " + (bal - amt));

                } else if (type == 'd' || type == 'D') {

                    if (amt > 50000) {
                        throw new InvalidDeposit(
                            "Deposit amount cannot exceed 50000");
                    }

                    if (amt <= 0) {
                        throw new InvalidDeposit(
                            "Deposit amount must be greater than zero");
                    }

                    updateSql =
                        "UPDATE customer SET bal = bal + ? WHERE acno = ?";

                    System.out.println("Deposit successful");
                    System.out.println("Deposited amount: " + amt);
                    System.out.println("Previous balance: " + bal);
                    System.out.println("New balance: " + (bal + amt));

                } else {
                    throw new InvalidTransaction(
                        "Invalid transaction type. Enter d or w");
                }

                try (PreparedStatement updatePs =
                         con.prepareStatement(updateSql)) {

                    updatePs.setDouble(1, amt);
                    updatePs.setInt(2, acno);
                    updatePs.executeUpdate();
                    String transactionType = (type == 'w' || type == 'W')
                            ? "WITHDRAW" : "DEPOSIT";

                    Transaction transaction = new Transaction();
                    transaction.setTransaction_type(transactionType);
                    transaction.setAcno(acno);
                    transaction.setAmount(amt);

                    saveTransaction(con, transaction);
                }
            }

            con.commit();
        } catch (SQLException | InvalidWithdraw | InvalidDeposit |
                InvalidTransaction | InsufficientFunds | InvalidAccount e) {

           con.rollback();

           if (e instanceof SQLException) {
               throw (SQLException) e;
           } else if (e instanceof InvalidWithdraw) {
               throw (InvalidWithdraw) e;
           } else if (e instanceof InvalidDeposit) {
               throw (InvalidDeposit) e;
           } else if (e instanceof InvalidTransaction) {
               throw (InvalidTransaction) e;
           } else if (e instanceof InsufficientFunds) {
               throw (InsufficientFunds) e;
           } else if (e instanceof InvalidAccount) {
               throw (InvalidAccount) e;
           }
        }
    }
}

        
       
        
        
                              
           
       
        public void deleteAccount(int acno) throws SQLException {

            String sql = "DELETE FROM customer WHERE acno = ?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, acno);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Account deleted successfully!");
                } else {
                    System.out.println("Invalid account number");
                }
            }
        }
        public void updateAccount(customer customer) throws SQLException {

            String sql = "UPDATE customer SET cname = ?, email = ?, "
                    + "accountype = ?, bal = ? WHERE acno = ?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, customer.getCname());
                ps.setString(2, customer.getEmail());
                ps.setString(3, customer.getAccountype());
                ps.setDouble(4, customer.getBal());
                ps.setInt(5, customer.getAcno());

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Account updated successfully!");
                } else {
                    System.out.println("Account number does not exist");
                }
            }
        }
        public void saveTransaction(Connection con, Transaction transaction)
                throws SQLException {

            String sql = "INSERT INTO transaction "
                    + "(transaction_type, acno, amount) VALUES (?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, transaction.getTransaction_type());
                ps.setInt(2, transaction.getAcno());
                ps.setDouble(3, transaction.getAmount());

                ps.executeUpdate();

                System.out.println("Transaction history saved!");
            }
        }
   
    }       