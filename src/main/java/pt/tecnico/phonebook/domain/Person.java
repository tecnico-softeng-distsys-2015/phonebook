package pt.tecnico.phonebook.domain;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

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
	for (Contact c : getContactSet())
	    c.delete();

	setPhoneBook(null);

	deleteDomainObject();
    }

    public void importFromXML(Element personElement) {
	// clear current phone book
	for (Contact c : getContactSet())
	    c.delete();

        setName(personElement.getAttribute("name").getValue());
        Element contacts = personElement.getChild("contacts");
        
        for (Element contactElement : contacts.getChildren("contact")) {
            Contact c = new Contact();
            c.importFromXML(contactElement);
            addContact(c);
        }
    }

    public Element exportToXML() {
        Element element = new Element("person");

        element.setAttribute("name", getName());

        Element contactsElement = new Element("contacts");
        element.addContent(contactsElement);

        for (Contact c : getContactSet()) {
            contactsElement.addContent(c.exportToXML());
        }

        return element;
    }
}
