
package bankmanagement;

import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;

public class BankMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Login login = new Login();

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (login.validateLogin(username, password)) {

            System.out.println("Login Successful!");

            BankService service = new BankService();
            

            while (true) {

                System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
                System.out.println("1. Create Account");
                System.out.println("2. View Accounts");
                System.out.println("3. Search Account");
                System.out.println("4. Deposit");
                System.out.println("5. Withdraw");
                System.out.println("6. Delete Account");
                System.out.println("7. Exit");

                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                try {

                    switch (choice) {

                    case 1:
                        System.out.print("Enter Account Number: ");
                        int acno = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Customer Name: ");
                        String cname = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter Account Type: ");
                        String accountype = sc.nextLine();

                        System.out.print("Enter Initial Balance: ");
                        double bal = Double.parseDouble(sc.nextLine());
                        customer c = new customer();

                        c.setAcno(acno);
                        c.setCname(cname);
                        c.setEmail(email);
                        c.setAccountype(accountype);
                        c.setBal(bal);

                        service.createAccount(c);
                        
                        break;

                    case 2:
                        List<customer> customerList =
                                service.viewAccounts();

                        for (customer cust : customerList) {
                            System.out.println("Account Number: "
                                    + cust.getAcno());
                            System.out.println("Customer Name: "
                                    + cust.getCname());
                            System.out.println("Email: "
                                    + cust.getEmail());
                            System.out.println("Account Type: "
                                    + cust.getAccountype());
                            System.out.println("Balance: "
                                    + cust.getBal());
                            System.out.println("----------------------");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        int searchNo = Integer.parseInt(sc.nextLine());

                        customer result = service.searchAccount(searchNo);

                        System.out.println("Account Number: "
                                + result.getAcno());
                        System.out.println("Customer Name: "
                                + result.getCname());
                        System.out.println("Balance: "
                                + result.getBal());
                        break;

                    case 4:
                        System.out.print("Enter Account Number: ");
                        int depositNo = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Deposit Amount: ");
                        double depositAmt =
                                Double.parseDouble(sc.nextLine());

                        service.performTransaction(
                                depositNo, 'd', depositAmt);
                        break;

                    case 5:
                        System.out.print("Enter Account Number: ");
                        int withdrawNo = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Withdrawal Amount: ");
                        double withdrawAmt =
                                Double.parseDouble(sc.nextLine());

                        service.performTransaction(
                                withdrawNo, 'w', withdrawAmt);
                        break;

                    case 6:
                        System.out.print("Enter Account Number: ");
                        int deleteNo = Integer.parseInt(sc.nextLine());

                        service.deleteAccount(deleteNo);
                       
                        break;

                    case 7:
                        System.out.println("Thank you!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Try again.");
                    }

                } catch (SQLException | InvalidAccount |
                         InvalidWithdraw | InvalidDeposit |
                         InvalidTransaction | InsufficientFunds e) {

                    System.out.println("Error: " + e.getMessage());
                }
            }

        } else {
            System.out.println("Invalid Username or Password!");
        }

        sc.close();
    }
}
