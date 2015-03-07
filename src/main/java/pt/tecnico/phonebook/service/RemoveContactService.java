package pt.tecnico.phonebook.service;

import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.exception.ContactDoesNotExistException;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class RemoveContactService extends PhoneBookService {

    private String personName;
    private String contactName;

    public RemoveContactService(String personName, String contactName) {
        this.contactName = contactName;
        this.personName = personName;
    }

    public final void dispatch() throws ContactDoesNotExistException, PersonDoesNotExistException {
        Person p = getPerson(personName);
        Contact c = p.getContactByName(contactName);

        if (c == null)
            throw new ContactDoesNotExistException(this.contactName);

        c.delete();
    }
}
