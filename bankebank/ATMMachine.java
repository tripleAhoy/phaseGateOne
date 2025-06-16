import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMMachine {

	private static class Account {
		private final String accountNumber;
		private final String firstName;
		private final String lastName;
		private String pin;
		private double balance;

		public Account(String accountNumber, String firstName, String lastName, String pin, double balance) {
			this.accountNumber = accountNumber;
			this.firstName = firstName;
			this.lastName = lastName;
			this.pin = pin;
			this.balance = balance;
		}
	}

	private final ArrayList<Account> accounts = new ArrayList<>();
	private Account currentUser = null;
	private final Scanner input = new Scanner(System.in);
	private final Random random = new Random();

	public static void main(String[] args) {
		ATMMachine app = new ATMMachine();
		app.run();
	}

	public void run() {
		while(true) {
			if (currentUser == null) {
				showMainMenu();
			}else {
				showTransactionMenu();
			}
		}
	}

	public void showMainMenu() {
		System.out.print("""
		\nWelcome to Banke Bank ATM
		1. Create Account
		2. Login
		3. Exit

		""");
		int choice = getIntInput("Enter option: ");
		switch(choice) {
			case 1 -> createAccount();
			case 2 -> login();
			case 3 -> {
				System.out.print("Thank you for using Banke Bank ATM");
				System.exit(0);
			}
			default -> System.out.print("Invalid option. Try again.");
		}
	}

	public void showTransactionMenu() {
		System.out.printf("\nWelcome, %s%n", currentUser.firstName);		
		System.out.print("""
		1. Check Balance
		2. Deposit
		3. Withdraw
		4. Transfer
		5. Change PIN
		6. Close Account
		7. Logout		
		""");
		int choice = getIntInput("Enter option: ");
		switch (choice) {
			case 1 -> checkBalance();
			case 2 -> deposit();
			case 3 -> withdraw();
			case 4 -> transfer();
			case 5 -> changePin();
			case 6 -> closeAccount();
			case 7 -> logout();
			default -> System.out.print("Invalid option. Try again.");
		}
	}

	public void createAccount() {
		System.out.println("\nAccount Creation");

		String firstName = getStringInput("Enter first name: ");
		while (!firstName.matches("[a-zA-Z]+")) {
			print("Invalid name. Only letters are allowed.");
			firstName = getStringInput("Enter first name: ");
		}
		String lastName = getStringInput("Enter last name: ");
		while (!lastName.matches("[a-zA-Z]+")) {
			print("Invalid name. Only letters are allowed.");
			lastName = getStringInput("Enter last name: ");
		}
		String pin;
		do {
			pin = getStringInput("Create 4-digit PIN: ");
			if (!pin.matches("\\d{4}")) {
				System.out.print("PIN must be exactly 4 digits");
			}
		} while (!pin.matches("\\d{4}"));
        

		String accountNumber = generateAccountNumber();
		 Account newAccount = new Account(accountNumber, firstName, lastName, pin, 0.0);
		accounts.add(newAccount);

		System.out.print("Account created successfully!");
		System.out.print("Your account number is: " + accountNumber);
		
	}

	public void login() {
		String accountNumber = getStringInput("Enter accountNumber: ");
		String pinAttempt = getStringInput("Enter PIN: ");

		for(Account account : accounts) {
			if(account.accountNumber.equals(accountNumber)) {
				if(account.pin.equals(pinAttempt)) {
					currentUser = account;
					print("Login successful!");
					return;
				}
				else {
					print("Incorrect PIN");
					return;
				}
			}
		}
		System.out.print("Account not found");
	}

	public void checkBalance() {
		System.out.printf("Your balance is #%.2f", currentUser.balance);
	}

	public void deposit() {
		double amount = getPositiveAmount("Enter amount to deposit: ");
		currentUser.balance += amount;
		System.out.printf("Deposit successful. New balance: #%.2f", currentUser.balance);
	}

	public void withdraw() {
		double amount = getPositiveAmount("Enter amount to withdraw: ");

		while(amount > currentUser.balance) {
			System.out.print("Insufficient funds");
			amount = getDoubleInput("Enter amount to withdraw: ");
		}
		currentUser.balance -= amount;
		System.out.printf("Withdrawal successful. New balance: #%.2f%n", currentUser.balance);
	}

	public void transfer() {
		String recipientNumber = getStringInput("Enter recipient account number: ");
		Account recipient = findAccount(recipientNumber);

		if(recipient == null) {
			System.out.print("Recipient account not found");
			return;
		}
		if(recipient == currentUser) {
			System.out.print("Cannot transfer to yourself");
			return;
		}

		double amount = getPositiveAmount("Enter amount to transfer: ");
		while(amount > currentUser.balance) {
			System.out.print("Insuffient funds");
			amount = getPositiveAmount("Enter amount to transfer: ");
		}

		currentUser.balance -= amount;
		recipient.balance += amount;
		System.out.printf("Transfer successful. New balance: #%.2f%n", currentUser.balance);
	}

	public void changePin() {
		String currentPin = getStringInput("Enter current PIN: ");
		if(!currentPin.equals(currentUser.pin)) {
			System.out.print("Incorrect PIN");
			return;
		}

		String newPin;
		do{
			newPin = getStringInput("Enter new 4-digit PIN: ");
			if(newPin != ("\\d{4}") ) {
				System.out.print("PIN must be exactly 4 digits");
			}
		}while (!newPin.matches("\\d{4}"));

		currentUser.pin = newPin;
		System.out.print("PIN changed successfully");
	}

	public void closeAccount() {
		String confirmation = getStringInput("Are you sure you want to close your account? (yes/no): ");
		if(confirmation.equalsIgnoreCase("yes")) {
			accounts.remove(currentUser);
			System.out.print("Account closed successfully");
			logout();
		} else {
			System.out.print("Account closure cancelled");
		}
	}
	
	public void logout() {
		currentUser = null;
		print("Logged out successfully");
	}

	public String generateAccountNumber() {
		return String.format("%07d", random.nextInt(9000000) + 1000000);
	}

	public Account findAccount(String accountNumber) {
		for(Account account : accounts) {
			if(account.accountNumber.equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}

	public double getPositiveAmount(String prompt) {
		double amount;
		do {
			amount = getDoubleInput(prompt);
			if (amount <= 0) {
				print("Amount must be positive");
			}
		} while (amount <= 0);
		return amount;
	}


	public int getIntInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				return Integer.parseInt(input.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
			}
		}
	}

	public double getDoubleInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				return Double.parseDouble(input.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter a valid number.");
			}
		}
	}

	public String getStringInput(String prompt) {
		System.out.print(prompt);
		return input.nextLine().trim();
	}

	public void print(String message) {
		System.out.println(message);
	}

}