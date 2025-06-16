const readline = require('readline');

class Account {
	constructor(accountNumber, firstName, lastName, pin, balance = 0) {
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pin = pin;
		this.balance = balance;
	}
}

class ATMMachine {
	constructor() {
		this.accounts = [];
		this.currentUser = null;
		this.rl = readline.createInterface({
			input: process.stdin,
			output: process.stdout
		});
	}

	generateAccountNumber() {
		return 'BNK' + Math.floor(100000 + Math.random() * 900000);
	}


	findAccount(accountNumber) {
		return this.accounts.find(account => account.accountNumber === accountNumber);
	}

	async getUserInput(prompt) {
		return new Promise(resolve => {
			this.rl.question(prompt, resolve);
		});
	}

	async showMainMenu() {
		console.log('\nWelcome to Banke Bank ATM');
		console.log('1. Create Account');
		console.log('2. Login');
		console.log('3. Exit');
	
		const choice = await this.getUserInput('Enter option: ');
		switch(choice) {
		case '1':
			await this.createAccount();
		break;
		case '2':
			await this.login();
		break;
		case '3':
			console.log('Thank you for using Banke Bank ATM');
			this.rl.close();
			process.exit();
		default:
			console.log('Invalid option');
			await this.showMainMenu();
		}
	}

	async showTransactionMenu() {
		console.log(`\nWelcome, ${this.currentUser.firstName}`);
		console.log('1. Check Balance');
		console.log('2. Deposit');
		console.log('3. Withdraw');
		console.log('4. Transfer');
		console.log('5. Change PIN');
		console.log('6. Close Account');
		console.log('7. Logout');

		const choice = await this.getUserInput('Enter option: ');

		switch(choice) {
			case '1':
				await this.checkBalance(); break;
			case '2':
				await this.deposit(); break;
			case '3':
				await this.withdraw(); break;
			case '4':
				await this.transfer(); break;
			case '5':
				await this.changePin(); break;
			case '6':
				await this.closeAccount(); break;
			case '7':
				await this.logout(); return;
			default:
				console.log('Invalid option');
			}
		await this.showTransactionMenu();
	}

	async createAccount() {
		console.log('\nAccount Creation');
		const firstName = await this.getUserInput('Enter first name: ');
		const lastName = await this.getUserInput('Enter last name: ');
    
		let pin;
		do {
			pin = await this.getUserInput('Create 4-digit PIN: ');
			if (!/^\d{4}$/.test(pin)) {
				console.log('PIN must be exactly 4 digits');
			}
		} while (!/^\d{4}$/.test(pin));
    
		const accountNumber = this.generateAccountNumber();
		const newAccount = new Account(accountNumber, firstName, lastName, pin);
		this.accounts.push(newAccount);
		
		console.log('\nAccount created successfully!');
		console.log(`Your account number is: ${accountNumber}`);
	}

	async login() {
		const accountNumber = await this.getUserInput('Enter account number: ');
		const pinAttempt = await this.getUserInput('Enter PIN: ');
	
		const account = this.findAccount(accountNumber);
		if (account) {
			if (account.pin === pinAttempt) {
	 			this.currentUser = account;
	 			console.log('\nLogin successful!');
	  			await this.showTransactionMenu();
			} else {
	 			console.log('\nIncorrect PIN');
			}
		} else {
			console.log('\nAccount not found');
		}
	}

	async checkBalance() {
		console.log(`\nYour balance is: $${this.currentUser.balance.toFixed(2)}`);
	}

	async deposit() {
		const amountInput = await this.getUserInput('Enter amount to deposit: $');
		const amount = parseFloat(amountInput);
		if (isNaN(amount) || amount <= 0) {
			console.log('Amount must be positive');
			return;
		}
    
		this.currentUser.balance += amount;
		console.log(`Deposit successful. New balance: $${this.currentUser.balance.toFixed(2)}`);
	}

	async withdraw() {
		const amountInput = await this.getUserInput('Enter amount to withdraw: $');
		const amount = parseFloat(amountInput);
		if (isNaN(amount) || amount <= 0) {
			console.log('Amount must be positive');
		} else if (amount > this.currentUser.balance) {
			console.log('Insufficient funds');
		} else {
			this.currentUser.balance -= amount;
			console.log(`Withdrawal successful. New balance: $${this.currentUser.balance.toFixed(2)}`);
		}
	}

	async transfer() {
		const recipientNumber = await this.getUserInput('Enter recipient account number: ');
		if (recipientNumber === this.currentUser.accountNumber) {
			console.log('Cannot transfer to yourself');
			return;
		}
		
		const recipient = this.findAccount(recipientNumber);
		if (!recipient) {
			console.log('Recipient account not found');
			return;
		}
    
		const amountInput = await this.getUserInput('Enter amount to transfer: $');
		const amount = parseFloat(amountInput);
		if (isNaN(amount) || amount <= 0) {
			console.log('Amount must be positive');
		} else if (amount > this.currentUser.balance) {
			console.log('Insufficient funds');
		} else {
			this.currentUser.balance -= amount;
			recipient.balance += amount;
			console.log(`Transfer successful. New balance: $${this.currentUser.balance.toFixed(2)}`);
		}
	}

	async changePin() {
		const currentPin = await this.getUserInput('Enter current PIN: ');
		if (currentPin !== this.currentUser.pin) {
			console.log('Incorrect PIN');
			return;
		}
    
		let newPin;
		do {
			newPin = await this.getUserInput('Enter new 4-digit PIN: ');
			if (!/^\d{4}$/.test(newPin)) {
				console.log('PIN must be exactly 4 digits');
			}
		} while (!/^\d{4}$/.test(newPin));
	
		this.currentUser.pin = newPin;
		console.log('PIN changed successfully');
	}

	async closeAccount() {
		const confirmation = (await this.getUserInput('Are you sure you want to close your account? (yes/no): ')).toLowerCase();
		if (confirmation === 'yes') {
			this.accounts = this.accounts.filter(acc => acc !== this.currentUser);
			console.log('Account closed successfully');
			await this.logout();
		} else {
			console.log('Account closure cancelled');
		}
	}

	async logout() {
		this.currentUser = null;
		console.log('Logged out successfully');
	}

	async run() {
		while (true) {
			if (this.currentUser === null) {
				await this.showMainMenu();
			} else {
				await this.showTransactionMenu();
			}
		}
	}
}

(async () => {
  const atm = new ATMMachine();
  await atm.run();
})();