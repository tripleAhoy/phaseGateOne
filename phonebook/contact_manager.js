class Contact {
	constructor(firstName, lastName, phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	getFullName() {
		return `${this.firstName} ${this.lastName}`;
	}

	toString() {
		return `Name: ${this.getFullName()}
	Phone: ${this.phoneNumber}`;
	}
}

class ContactManager {
	constructor() {
		this.contacts = [];
	}

  addContact(contact) {
    if (!this._phoneExists(contact.phoneNumber)) {
      this.contacts.push(contact);
      console.log("Contact added successfully!");
      return true;
    }
    console.log("Error: Phone number already exists!");
    return false;
  }

  removeContact(phoneNumber) {
    const index = this.contacts.findIndex(c => c.phoneNumber === phoneNumber);
    if (index !== -1) {
      this.contacts.splice(index, 1);
      console.log("Contact removed successfully!");
      return true;
    }
    console.log("Error: Contact not found!");
    return false;
  }

  findByPhone(phoneNumber) {
    return this.contacts.find(c => c.phoneNumber === phoneNumber);
  }

  findByFirstName(firstName) {
    return this.contacts.filter(c => 
      c.firstName.toLowerCase() === firstName.toLowerCase());
  }

  findByLastName(lastName) {
    return this.contacts.filter(c => 
      c.lastName.toLowerCase() === lastName.toLowerCase());
  }

  editContact(phoneNumber, updatedContact) {
    const index = this.contacts.findIndex(c => c.phoneNumber === phoneNumber);
    if (index !== -1) {
      if (updatedContact.phoneNumber !== phoneNumber && 
          this._phoneExists(updatedContact.phoneNumber)) {
        console.log("Error: New phone number already exists!");
        return false;
      }
      this.contacts[index] = updatedContact;
      console.log("Contact updated successfully!");
      return true;
    }
    console.log("Error: Contact not found!");
    return false;
  }

  displayAll() {
    if (this.contacts.length === 0) {
      console.log("No contacts available!");
      return;
    }
    console.log("\nAll Contacts:");
    console.log("-----------------------");
    this.contacts.forEach((contact, i) => {
      console.log(`${i+1}. ${contact.getFullName()} - ${contact.phoneNumber}`);
    });
    console.log("-----------------------");
  }

  _phoneExists(phoneNumber) {
    return this.contacts.some(c => c.phoneNumber === phoneNumber);
  }

  async run() {
    const readline = require('readline').createInterface({
      input: process.stdin,
      output: process.stdout
    });

    const question = (query) => new Promise(resolve => 
      readline.question(query, resolve));

    console.log("\n=== Contact Management System ===");

    while (true) {
      console.log("\nMain Menu:");
      console.log("1. Add Contact");
      console.log("2. Remove Contact");
      console.log("3. Search Contacts");
      console.log("4. Edit Contact");
      console.log("5. View All Contacts");
      console.log("6. Exit");

      const choice = await question("Enter your choice (1-6): ");

      switch (choice) {
        case '1':
          console.log("\nAdd New Contact");
          const firstName = await question("First Name: ");
          const lastName = await question("Last Name: ");
          const phoneNumber = await question("Phone Number: ");
          
          const newContact = new Contact(
            firstName.trim(), 
            lastName.trim(), 
            phoneNumber.trim(), 
          );
          this.addContact(newContact);
          break;

        case '2':
          console.log("\nRemove Contact");
          const phoneToRemove = await question("Enter phone number to remove: ");
          this.removeContact(phoneToRemove.trim());
          break;

        case '3':
          console.log("\nSearch Contacts");
          console.log("1. By Phone Number");
          console.log("2. By First Name");
          console.log("3. By Last Name");
          
          const searchChoice = await question("Choose search method (1-3): ");
          const searchTerm = await question("Enter search term: ");
          
          let results = [];
          switch (searchChoice) {
            case '1':
              const contact = this.findByPhone(searchTerm.trim());
              console.log("\nSearch Results:");
              console.log(contact ? contact.toString() : "No contact found!");
              break;
            case '2':
              results = this.findByFirstName(searchTerm.trim());
              this._displaySearchResults(results);
              break;
            case '3':
              results = this.findByLastName(searchTerm.trim());
              this._displaySearchResults(results);
              break;
            default:
              console.log("Invalid choice!");
          }
          break;

        case '4':
          console.log("\nEdit Contact");
          const oldPhone = await question("Enter phone number of contact to edit: ");
          const existing = this.findByPhone(oldPhone.trim());
          
          if (!existing) {
            console.log("Contact not found!");
            break;
          }
          
          console.log("\nCurrent contact details:");
          console.log(existing.toString());
          
          console.log("\nEnter new details (leave blank to keep current):");
          const newFirst = await question(`First Name [${existing.firstName}]: `) || existing.firstName;
          const newLast = await question(`Last Name [${existing.lastName}]: `) || existing.lastName;
          const newPhone = await question(`Phone [${existing.phoneNumber}]: `) || existing.phoneNumber;
          
          const updated = new Contact(
            newFirst.trim(),
            newLast.trim(),
            newPhone.trim()
          );
          this.editContact(oldPhone.trim(), updated);
          break;

        case '5':
          this.displayAll();
          break;

        case '6':
          console.log("\nThank you for using the Contact Management System. Goodbye!");
          readline.close();
          return;

        default:
          console.log("Invalid choice! Please enter a number between 1 and 6.");
      }
    }
  }

  _displaySearchResults(results) {
    if (results.length === 0) {
      console.log("No contacts found!");
      return;
    }
    console.log(`\nFound ${results.length} contact(s):`);
    console.log("-----------------------");
    results.forEach(contact => {
      console.log(contact.toString());
      console.log("-----------------------");
    });
  }
}


const manager = new ContactManager();
manager.run();