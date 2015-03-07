package pt.tecnico.phonebook.domain;

import pt.tecnico.phonebook.exception.InvalidPhoneNumberException;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class Contact extends Contact_Base {
    
    public Contact(String name, Integer phoneNumber) throws InvalidPhoneNumberException {
        setName(name);

	if (phoneNumber <= 0)
	    throw new InvalidPhoneNumberException(phoneNumber);

        setPhoneNumber(phoneNumber);
    }

    @Override
    public void setPerson(Person person) throws NameAlreadyExistsException {
        if (person == null) {
            super.setPerson(null);
            return;
        }

        person.addContact(this);
    }    
}
