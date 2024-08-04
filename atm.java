import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class atm {
    private Map<String, Account> accounts;
    private Scanner scanner;

    public atm() {
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in);

        // Initialize some accounts for testing
        accounts.put("12345", new Account("12345", "1234", 1000.0));
        accounts.put("67890", new Account("67890", "5678", 500.0));
    }

    public void run() {
        System.out.println("Welcome to the ATM system!");
        System.out.println("Please enter your account number:");
        String accountNumber = scanner.nextLine();

        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Account not found!");
            return;
        }

        Account account = accounts.get(accountNumber);

        System.out.println("Please enter your PIN:");
        String pin = scanner.nextLine();

        if (!account.getPin().equals(pin)) {
            System.out.println("Invalid PIN!");
            return;
        }

        while (true) {
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw cash");
            System.out.println("3. Deposit money");
            System.out.println("4. Transfer funds");
            System.out.println("5. Exit");
            System.out.println("Please select an option:");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    checkBalance(account);
                    break;
                case 2:
                    withdrawCash(account);
                    break;
                case 3:
                    depositMoney(account);
                    break;
                case 4:
                    transferFunds(account);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM system!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void checkBalance(Account account) {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    private void withdrawCash(Account account) {
        System.out.println("Please enter the amount to withdraw:");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds!");
            return;
        }

        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdrawal successful!");
    }

    private void depositMoney(Account account) {
        System.out.println("Please enter the amount to deposit:");
        double amount = Double.parseDouble(scanner.nextLine());

        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successful!");
    }

    private void transferFunds(Account account) {
        System.out.println("Please enter the recipient's account number:");
        String recipientAccountNumber = scanner.nextLine();

        if (!accounts.containsKey(recipientAccountNumber)) {
            System.out.println("Recipient's account not found!");
            return;
        }

        Account recipientAccount = accounts.get(recipientAccountNumber);

        System.out.println("Please enter the amount to transfer:");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds!");
            return;
        }

        account.setBalance(account.getBalance() - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);
        System.out.println("Transfer successful!");
    }

    public static void main(String[] args) {
        atm atm = new atm();
        atm.run();
    }
}
