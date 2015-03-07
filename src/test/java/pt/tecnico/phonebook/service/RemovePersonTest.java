package pt.tecnico.phonebook.service;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class RemovePersonTest extends AbstractServiceTest {

    protected void initializeDomain() {
	PhoneBook pb = PhoneBook.getInstance();

	pb.addPerson(new Person("João"));
    }

    @Test
    public void success() {
	final String personName = "João";
	RemovePersonService service = new RemovePersonService(personName);
	service.execute();

	// check person was removed
	assertFalse("user was not removed", PhoneBookService.getPhoneBook().hasPerson(personName));
    }

    @Test(expected = PersonDoesNotExistException.class)
    public void removeNonexistingPerson() {
	RemovePersonService service = new RemovePersonService("David");
	service.execute();
    }

}
