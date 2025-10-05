import java.util.Scanner;

/**
 * Program Title: Bank Account Management System (OOP)
 * Author: [Sreenivasulu-03]
 * Date: 2025-10-05
 *
 * Description: Demonstrates core OOP concepts (encapsulation, methods, constructor)
 * by simulating a basic bank account with deposit, withdrawal, and balance checking
 * functionality using a simple menu-driven interface.
 * Time Complexity: O(1) for all operations.
 * Space Complexity: O(1) for storing account details.
 */
class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    private double balance;

    /**
     * Constructor to initialize account details.
     * @param accountNumber The unique account number.
     * @param accountHolderName The name of the account holder.
     * @param initialBalance The starting balance.
     */
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    /**
     * Deposits the specified amount into the account.
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: ₹" + amount);
        } 
        else {
            System.out.println("Deposit amount must be positive!");
        }
    }

    /**
     * Withdraws the specified amount from the account.
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: ₹" + amount);
        } 
        else if (amount > balance) {
            System.out.println("Insufficient balance! Current balance: ₹" + balance);
        } 
        else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    /**
     * Prints the current balance to the console.
     */
    public void checkBalance() {
        System.out.println("Current balance: ₹" + balance);
    }

    /**
     * Prints all account details to the console.
     */
    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
        System.out.println("-----------------------");
    }
}

public class BankAccountManagement {

    public static void main(String[] args) {
        // Use try-with-resources to ensure the Scanner is safely closed.
        try (Scanner sc = new Scanner(System.in)) {
            
            // Take initial account details from user
            System.out.print("Enter Account Holder Name: ");
            String name = sc.nextLine();
            
            System.out.print("Enter Account Number: ");
            String number = sc.nextLine();
            
            System.out.print("Enter Initial Balance: ₹");
            // Check for valid number input
            while (!sc.hasNextDouble()) {
                System.out.print("Invalid input. Enter a numeric balance: ₹");
                sc.next();
            }
            double balance = sc.nextDouble();

            BankAccount account = new BankAccount(number, name, balance);
            int choice = 0;

            // Menu-driven system
            do {
                System.out.println("\n===== Bank Menu =====");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Display Account Details");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.next(); // Consume the invalid token
                    continue; // Skip the rest of the loop and show menu again
                }
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ₹");
                        if (sc.hasNextDouble()) {
                            double depositAmount = sc.nextDouble();
                            account.deposit(depositAmount);
                        } else {
                            System.out.println("Invalid input. Deposit failed.");
                            sc.next(); // Consume invalid input
                        }
                        break;
                        
                    case 2:
                        System.out.print("Enter amount to withdraw: ₹");
                        if (sc.hasNextDouble()) {
                            double withdrawAmount = sc.nextDouble();
                            account.withdraw(withdrawAmount);
                        } else {
                            System.out.println("Invalid input. Withdrawal failed.");
                            sc.next(); // Consume invalid input
                        }
                        break;
                        
                    case 3:
                        account.checkBalance();
                        break;
                        
                    case 4:
                        account.displayAccountDetails();
                        break;
                        
                    case 5:
                        System.out.println("Thank you for using the Bank Account Management System! Exiting...");
                        break;
                        
                    default:
                        System.out.println("Invalid choice! Please try again (1-5).");
                }
            } while (choice != 5);

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
