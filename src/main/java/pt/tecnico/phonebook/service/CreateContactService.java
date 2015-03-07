package pt.tecnico.phonebook.service;

import pt.ist.fenixframework.FenixFramework;

import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;
import pt.tecnico.phonebook.exception.InvalidPhoneNumberException;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class CreateContactService extends PhoneBookService {

    private String personName;
    private String contactName;
    private int phoneNumber;
        
    public CreateContactService(String personName, String contactName, int phoneNumber) {
	this.contactName = contactName;
	this.phoneNumber = phoneNumber;
	this.personName = personName;
    }

    public final void dispatch() throws NameAlreadyExistsException, InvalidPhoneNumberException {
	Person p = getPerson(personName);

	p.addContact(new Contact(contactName, phoneNumber));
    }
}
