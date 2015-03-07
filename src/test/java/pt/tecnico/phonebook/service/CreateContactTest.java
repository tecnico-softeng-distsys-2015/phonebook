package pt.tecnico.phonebook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;
import pt.tecnico.phonebook.exception.InvalidPhoneNumberException;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class CreateContactTest extends AbstractServiceTest {

    protected void initializeDomain() {
	PhoneBook pb = PhoneBook.getInstance();
	
	Person p = new Person("João");
	pb.addPerson(p);
	pb.addPerson(new Person("António"));

	p.addContact(new Contact("António", 123456));
    }

    private Contact getContact(String personName, String contactName) {
	Person p = PhoneBookService.getPhoneBook().getPersonByName(personName);
	return p.getContactByName(contactName);
    }

    @Test
    public void success() {
	final String personName = "João";
	CreateContactService service = new CreateContactService(personName, "João", 654321);
	service.execute();

	// check person was created
	Contact c = getContact(personName, "João");
	assertNotNull("contact was not created", c);
	assertEquals("Invalid phone number", 654321, (int)c.getPhoneNumber());
    }

    @Test
    public void successWithPersonWithoutContacts() {
	final String personName = "António";
	CreateContactService service = new CreateContactService(personName, "João", 654321);
	service.execute();

	// check person was created
	Contact c = getContact(personName, "João");
	assertNotNull("contact was not created", c);
	assertEquals("Invalid phone number", 654321, (int)c.getPhoneNumber());
	assertEquals("Invalid number of contacts", 1 , PhoneBookService.getPhoneBook().getPersonByName(personName).getContactSet().size());
    }

    @Test(expected = NameAlreadyExistsException.class)
    public void invalidContactCreationWithDuplicateName() {
	final String personName = "João";
	CreateContactService service = new CreateContactService(personName, "António", 654321);
	service.execute();
    }

    @Test(expected = PersonDoesNotExistException.class)
    public void invalidPerson() {
	final String personName = "José";
	CreateContactService service = new CreateContactService(personName, "António", 654321);
	service.execute();
    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void invalidContactCreationWithInvalidPhoneNumber() {
	final String personName = "João";
	CreateContactService service = new CreateContactService(personName, "Jorge", -654321);
	service.execute();
    }
    
}
