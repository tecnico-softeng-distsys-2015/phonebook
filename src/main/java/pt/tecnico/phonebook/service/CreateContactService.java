package pt.tecnico.phonebook.service;

import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.exception.InvalidPhoneNumberException;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class CreateContactService extends PhoneBookService {

    private String personName;
    private String contactName;
    private int phoneNumber;

    public CreateContactService(String personName, String contactName, int phoneNumber) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.personName = personName;
    }

    @Override
    public final void dispatch() throws NameAlreadyExistsException, InvalidPhoneNumberException {
        Person p = getPerson(personName);

        p.addContact(new Contact(contactName, phoneNumber));
    }
}
