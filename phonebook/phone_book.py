import re
class Contact:
	def __init__(self, first_name, last_name, phone_number):
		self.first_name = first_name
		self.last_name = last_name
		self.phone_number = phone_number

	def __str__(self):
		return (f"Name: {self.first_name} {self.last_name}\n")
		f"Phone: {self.phone_number}\n"


class ContactManager:
	def __init__(self):
		self.contacts = []

	def add_contact(self):		
		while True:
    			first_name = input("Enter First Name: ").strip()
    			if not first_name:
        			print("Name cannot be empty!")
    			elif not first_name.isalpha():
        			print("Name must contain only letters!")
    			else:
        			break
		while True:
    			last_name = input("Enter last Name: ").strip()
    			if not last_name:
        			print("Name cannot be empty!")
    			elif not last_name.isalpha():
        			print("Name must contain only letters!")
    			else:
        			break
		while True:
			phone_number = input("Enter Phone Number: ").strip()
			if re.fullmatch(r'^\+?[\d\-\s]{8,15}$', phone_number):
				break
			print("Invalid phone number! Please enter 8-15 digits (may include +, - or spaces)")
			print(f"Valid phone number entered: {phone_number}")
		contact = Contact(first_name, last_name, phone_number)
		self.contacts.append(contact)
		print("Contact added successfully.\n")

	def remove_contact(self):
		while True:
			phone_number = input("Enter phone number of the contact to remove: ").strip()
			if re.fullmatch(r'^\+?[\d\-\s]{8,15}$', phone_number):
				break
			print("Invalid phone number! Please enter 8-15 digits (may include +, - or spaces)")
			print(f"Valid phone number entered: {phone_number}")
		for contact in self.contacts:
			if contact.phone_number == phone_number:
				self.contacts.remove(contact)
				print("Contact removed successfully.\n")
				return
			print("Contact not found.\n")

	def find_by_phone(self):
		phone_number = input("Enter phone number to search: ")
		for contact in self.contacts:
			if contact.phone_number == phone_number:
				print(contact)
				return
			print("Contact not found.\n")

	def find_by_first_name(self):
		first_name = input("Enter first name to search: ")
		found = False
		for contact in self.contacts:
			if contact.first_name.lower() == first_name.lower():
				print(contact)
			print()
		found = True
		if not found:
			print("No contacts found with that first name.\n")

	def find_by_last_name(self):
		last_name = input("Enter last name to search: ")
		found = False
		for contact in self.contacts:
			if contact.last_name.lower() == last_name.lower():
				print(contact)
			print()
		found = True
		if not found:
			print("No contacts found with that last name.\n")

	def edit_contact(self):
		phone_number = input("Enter phone number of the contact to edit: ")
		for contact in self.contacts:
			if contact.phone_number == phone_number:
				print("Editing contact:")
				print(contact)
			contact.first_name = input("Enter new first name: ")
			contact.last_name = input("Enter new last name: ")
			contact.phone_number = input("Enter new phone number: ")
			print("Contact updated successfully.\n")
			return
		print("Contact not found.\n")

	def display_all_contacts(self):
		if not self.contacts:
			print("No contacts available.\n")
			for contact in self.contacts:
				print(contact)
			print("-" * 30)


def main():
	manager = ContactManager()
	while True:
		print("""
	Contact Manager App
	1. Add Contact
	2. Remove Contact
	3. Find by Phone Number
	4. Find by First Name
	5. Find by Last Name
	6. Edit Contact
	7. Display All Contacts
	8. Exit
		""")
		choice = input("Enter your choice (1-8): ")

		if choice == '1':
			manager.add_contact()
		elif choice == '2':
			manager.remove_contact()
		elif choice == '3':
			manager.find_by_phone()
		elif choice == '4':
			manager.find_by_first_name()
		elif choice == '5':
			manager.find_by_last_name()
		elif choice == '6':
			manager.edit_contact()
		elif choice == '7':
			manager.display_all_contacts()
		elif choice == '8':
			print("Exiting Contact Manager. Goodbye!")
			break
		else:
			print("Invalid choice. Please try again.\n")


if __name__ == "__main__":
    main()
