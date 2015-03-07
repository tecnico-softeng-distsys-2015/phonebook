package pt.tecnico.phonebook.domain;

import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class Person extends Person_Base {
    
    public Person(String name) {
        super();
        setName(name);
    }                 
    
    @Override
    public void setPhoneBook(PhoneBook pb) {
	if (pb == null)
	    super.setPhoneBook(null);
	else
	    pb.addPerson(this);
    }

    @Override
    public void addContact(Contact contactToBeAdded) throws NameAlreadyExistsException {
        if (hasContact(contactToBeAdded.getName()))
                throw new NameAlreadyExistsException(contactToBeAdded.getName());
        
        super.addContact(contactToBeAdded);
    }

    public Contact getContactByName(String name) {
        for(Contact contact : getContactSet()) {
            if(contact.getName().equals(name)) {
                return contact;
            }
        }

        return null;
    }
    
    public boolean hasContact(String contactName) {
        return getContactByName(contactName) != null;
    }

    public void delete() {
	setPhoneBook(null);
	deleteDomainObject();
    }
}
