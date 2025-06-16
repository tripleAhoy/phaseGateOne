import random

class Account:
	def __init__(self, account_number, first_name, last_name, pin, balance = 0.0):
		self.account_number = account_number
		self.first_name = first_name
		self.last_name = last_name
		self.pin = pin
		self.balance = balance

class ATMMachine:
	def __init__(self):
		self.accounts = []
		self.current_user = None
    
	def generate_account_number(self):
		return f"BNK{random.randint(100000, 999999)}"
    
	def find_account(self, account_number):
		for account in self.accounts:
			if account.account_number == account_number:
				return account
		return None
    
	def show_main_menu(self):
		print("\nWelcome to Banke Bank ATM")
		print("1. Create Account")
		print("2. Login")
		print("3. Exit")
        
		try:
			choice = int(input("Enter option: "))
			if choice == 1:
				self.create_account()
			elif choice == 2:
				self.login()
			elif choice == 3:
				print("Thank you for using Banke Bank ATM")
				exit()
			else:
				print("Invalid option")
		except ValueError:
			print("Please enter a valid number")

	def show_transaction_menu(self):
		print(f"\nWelcome, {self.current_user.first_name}")
		print("1. Check Balance")
		print("2. Deposit")
		print("3. Withdraw")
		print("4. Transfer")
		print("5. Change PIN")
		print("6. Close Account")
		print("7. Logout")
        
		try:
			choice = int(input("Enter option: "))
			if choice == 1:
				self.check_balance()
			elif choice == 2:
				self.deposit()
			elif choice == 3:
				self.withdraw()
			elif choice == 4:
				self.transfer()
			elif choice == 5:
				self.change_pin()
			elif choice == 6:
				self.close_account()
			elif choice == 7:
				self.logout()
			else:
				print("Invalid option")
		except ValueError:
			print("Please enter a valid number")

	def create_account(self):
		print("\nAccount Creation")
		first_name = input("Enter first name: ").strip()
		last_name = input("Enter last name: ").strip()
	
		while True:
			pin = input("Create 4-digit PIN: ")
			if pin.isdigit() and len(pin) == 4:
				break
			print("PIN must be exactly 4 digits")
        
		account_number = self.generate_account_number()
		new_account = Account(account_number, first_name, last_name, pin)
		self.accounts.append(new_account)
        
		print("\nAccount created successfully!")
		print(f"Your account number is: {account_number}")
		print("Please login to access your account")
	
	def login(self):
		account_number = input("Enter account number: ").strip()
		pin_attempt = input("Enter PIN: ").strip()
	
		account = self.find_account(account_number)
		if account:
			if account.pin == pin_attempt:
				self.current_user = account
				print("\nLogin successful!")
			else:
				print("\nIncorrect PIN")
		else:
			print("\nAccount not found")

	def check_balance(self):
		print(f"\nYour balance is: #{self.current_user.balance:.2f}")

	def deposit(self):
		try:
			amount = float(input("Enter amount to deposit: #"))
			if amount <= 0:
				print("Amount must be positive")
				return
		
			self.current_user.balance += amount
			print(f"Deposit successful. New balance: ${self.current_user.balance:.2f}")
		except ValueError:
			print("Invalid amount. Please enter a number")
		
	def withdraw(self):
		try:
			amount = float(input("Enter amount to withdraw: $"))
			if amount <= 0:
				print("Amount must be positive")
			elif amount > self.current_user.balance:
				print("Insufficient funds")
			else:
				self.current_user.balance -= amount
				print(f"Withdrawal successful. New balance: ${self.current_user.balance:.2f}")
		except ValueError:
			print("Invalid amount. Please enter a number")

	def transfer(self):
		recipient_number = input("Enter recipient account number: ").strip()
		if recipient_number == self.current_user.account_number:
			print("Cannot transfer to yourself")
			return

		recipient = self.find_account(recipient_number)
		if not recipient:
			print("Recipient account not found")
			return

		try:
			amount = float(input("Enter amount to transfer: #"))
			if amount <= 0:
				print("Amount must be positive")
			elif amount > self.current_user.balance:
				print("Insufficient funds")
			else:
				self.current_user.balance -= amount
				recipient.balance += amount
				print(f"Transfer successful. New balance: #{self.current_user.balance:.2f}")
		except ValueError:
			print("Invalid amount. Please enter a number")

	def change_pin(self):
		current_pin = input("Enter current PIN: ").strip()
		if current_pin != self.current_user.pin:
			print("Incorrect PIN")
		return
		
		while True:
			new_pin = input("Enter new 4-digit PIN: ").strip()
			if new_pin.isdigit() and len(new_pin) == 4:
				self.current_user.pin = new_pin
				print("PIN changed successfully")
				break
			print("PIN must be exactly 4 digits")

	def close_account(self):
		confirmation = input("Are you sure you want to close your account? (yes/no): ").lower()
		if confirmation == "yes":
			self.accounts.remove(self.current_user)
			print("Account closed successfully")
			self.logout()
		else:
			print("Account closure cancelled")

	def logout(self):
		self.current_user = None
		print("Logged out successfully")
	
	def run(self):
		while True:
			if self.current_user is None:
				self.show_main_menu()
			else:
				self.show_transaction_menu()

if __name__ == "__main__":
	app = ATMMachine()
	app.run()