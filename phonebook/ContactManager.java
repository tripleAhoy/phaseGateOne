import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact {
	String firstName;
	String lastName;
	String phoneNumber;
    
	public Contact(String firstName, String lastName, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
    
	public String getFullName() {
		return firstName + " " + lastName;
	}
}

public class ContactManager {
	List<Contact> contacts = new ArrayList<>();
	public void addContact(Contact newContact) {
		if (findByPhoneNumber(newContact.phoneNumber) == null) {
			contacts.add(newContact);
			System.out.println("Contact added successfully!");
		} else {
			System.out.println("Contact with this phone number already exists!");
		}
	}

	public void removeContact(String phoneNumber) {
		Contact toRemove = findByPhoneNumber(phoneNumber);
		if (toRemove != null) {
			contacts.remove(toRemove);
			System.out.println("Contact removed successfully!");
		} else {
			System.out.println("Contact not found!");
		}
	}

	public Contact findByPhoneNumber(String phoneNumber) {
		for (Contact contact : contacts) {
			if (contact.phoneNumber.equals(phoneNumber)) {
				return contact;
			}
		}
		return null;
	}

	public List<Contact> findByFirstName(String firstName) {
		List<Contact> results = new ArrayList<>();
		for (Contact contact : contacts) {
			if (contact.firstName.equalsIgnoreCase(firstName)) {
				results.add(contact);
			}
		}
		return results;
	}

	public List<Contact> findByLastName(String lastName) {
		List<Contact> results = new ArrayList<>();
		for (Contact contact : contacts) {
			if (contact.lastName.equalsIgnoreCase(lastName)) {
				results.add(contact);
			}
		}
		return results;
	}

	public void editContact(String phoneNumber, String newFirstName, String newLastName, String newPhoneNumber) {
		Contact toEdit = findByPhoneNumber(phoneNumber);
		if (toEdit != null) {
			toEdit.firstName = newFirstName;
			toEdit.lastName = newLastName;

			if (!phoneNumber.equals(newPhoneNumber)) {
				if (findByPhoneNumber(newPhoneNumber) == null) {
					toEdit.phoneNumber = newPhoneNumber;
				} else {
					System.out.println("New phone number already in use!");
				}
			}
			System.out.println("Contact updated successfully!");
			} else {
				System.out.println("Contact not found!");
		}
	}

	public void displayAllContacts() {
		if (contacts.isEmpty()) {
			System.out.println("No contacts available!");
			return;
		}

		System.out.println("All Contacts:");
		for (Contact contact : contacts) {
			System.out.println(contact.getFullName() + " - " + contact.phoneNumber);
		}
	}

	public static void main(String[] args) {
		ContactManager manager = new ContactManager();
		Scanner scanner = new Scanner(System.in);
 
		while (true) {
			System.out.println("\nContact Management System");
			System.out.println("""
			1. Add Contact
			2. Remove Contact
			3. Find by Phone Number
			4. Find by First Name
			5. Find by Last Name
			6. Edit Contact
			7. View All Contacts
			8. Exit
			""");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				System.out.print("Enter First Name: ");
				String firstName = scanner.nextLine();
				while(true) {
					if(firstName.trim().isEmpty()) {
						System.out.println("Name cannot be empty!");
					} 
					else if(!firstName.matches("[a-zA-Z]+")) {
						System.out.println("Name must contain only letters!");
					}
					else {
				break;
				}
					System.out.print("Enter First Name: ");
					firstName = scanner.nextLine();
				}
				System.out.print("Enter Last Name: ");
				String lastName = scanner.nextLine();
				while(true) {
					if(lastName.trim().isEmpty()) {
						System.out.println("Name cannot be empty!");
					} 
					else if(!lastName.matches("[a-zA-Z]+")) {
						System.out.println("Name must contain only letters!");
					}
					else {
				break;
				}
					System.out.print("Enter Last Name: ");
					lastName = scanner.nextLine();
				}
				System.out.print("Enter Phone Number: ");
				String phoneNumber;
				while (true) {
				phoneNumber = scanner.nextLine().trim();
				if (phoneNumber.matches("\\+?[0-9\\-\\s]{8,15}")) {
       					break;
				}
					System.out.println("Invalid phone number! Please enter 8-15 digits (may include +, - or spaces)");
					System.out.print("Enter Phone Number: ");
				}
				Contact newContact = new Contact(firstName, lastName, phoneNumber);
				manager.addContact(newContact);
			break;
                    
			case 2:
				System.out.print("Enter Phone Number to remove: ");
				String phoneToRemove;
				while (true) {
				phoneToRemove = scanner.nextLine().trim();
				if (phoneToRemove.matches("\\+?[0-9\\-\\s]{8,15}")) {
       					break;
				}
					System.out.println("Invalid phone number! Please enter 8-15 digits (may include +, - or spaces)");
					System.out.print("Enter Phone Number to remove: ");
				}

				manager.removeContact(phoneToRemove);
			break;
                    
			case 3:
				System.out.print("Enter Phone Number to search: ");
				String searchPhone;
				while (true) {
				searchPhone = scanner.nextLine().trim();
				if (searchPhone.matches("\\+?[0-9\\-\\s]{8,15}")) {
       					break;
				}
					System.out.println("Invalid phone number! Please enter 8-15 digits (may include +, - or spaces)");
					System.out.print("Enter Phone Number to search: ");
				}

				Contact foundByPhone = manager.findByPhoneNumber(searchPhone);
				if (foundByPhone != null) {
					System.out.println("Found: " + foundByPhone.getFullName());
				} else {
					System.out.println("Contact not found!");
				}
			break;
                    
			case 4:
				System.out.print("Enter First Name to search: ");
				String searchFirstName = scanner.nextLine();
				List<Contact> byFirstName = manager.findByFirstName(searchFirstName);
				displaySearchResults(byFirstName);
			break;
                    
			case 5:
				System.out.print("Enter Last Name to search: ");
				String searchLastName = scanner.nextLine();
				List<Contact> byLastName = manager.findByLastName(searchLastName);
				displaySearchResults(byLastName);
			break;
                    
			case 6:
				System.out.print("Enter Phone Number of contact to edit: ");
				String editPhone;
				while (true) {
				editPhone = scanner.nextLine().trim();
				if (editPhone.matches("\\+?[0-9\\-\\s]{8,15}")) {
       					break;
				}
					System.out.println("Invalid phone number! Please enter 8-15 digits (may include +, - or spaces)");
					System.out.print("Enter Phone Number of contact to edit: ");
				}

				System.out.print("Enter new First Name: ");
				String newFirstName = scanner.nextLine();
				while(true) {
					if(newFirstName.trim().isEmpty()) {
						System.out.println("Name cannot be empty!");
					} 
					else if(!newFirstName.matches("[a-zA-Z]+")) {
						System.out.println("Name must contain only letters!");
					}
					else {
				break;
				}
					System.out.print("Enter new First Name: ");
					newFirstName = scanner.nextLine();
				}
				System.out.print("Enter new Last Name: ");
				String newLastName = scanner.nextLine();
				while(true) {
					if(newLastName.trim().isEmpty()) {
						System.out.println("Name cannot be empty!");
					} 
					else if(!newLastName.matches("[a-zA-Z]+")) {
						System.out.println("Name must contain only letters!");
					}
					else {
				break;
				}
					System.out.print("Enter Last Name: ");
					newLastName = scanner.nextLine();
				}
				System.out.print("Enter new Phone Number: ");
				String newPhone;
				while (true) {
				newPhone = scanner.nextLine().trim();
				if (newPhone.matches("\\+?[0-9\\-\\s]{8,15}")) {
       					break;
				}
					System.out.println("Invalid phone number! Please enter 8-15 digits (may include +, - or spaces)");
					System.out.print("Enter new Phone Number: ");
				}
				manager.editContact(editPhone, newFirstName, newLastName, newPhone);
			break;
                    
			case 7:
				manager.displayAllContacts();
			break;
                    
			case 8:
				System.out.println("Exiting...");
			break;
                    
			default:
				System.out.println("Invalid choice!");
			}
		}
	}

	public static void displaySearchResults(List<Contact> results) {
		if (results.isEmpty()) {
			System.out.println("No contacts found!");
			return;
		}
        
		System.out.println("Found " + results.size() + " contacts:");
		for (Contact contact : results) {
			System.out.println(contact.getFullName() + " - " + contact.phoneNumber);
		}
	}

}
