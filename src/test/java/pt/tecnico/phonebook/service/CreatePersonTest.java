package pt.tecnico.phonebook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class CreatePersonTest extends AbstractServiceTest {

    protected void initializeDomain() {
	PhoneBook pb = PhoneBook.getInstance();

	pb.addPerson(new Person("João"));
    }

    @Test
    public void success() {
	final String personName = "David";
	CreatePersonService service = new CreatePersonService(personName);
	service.execute();

	// check person was created
	assertTrue("user was not created", PhoneBookService.getPhoneBook().hasPerson(personName));
    }

    @Test(expected = NameAlreadyExistsException.class)
    public void unauthorizedUserCreation() {
	CreatePersonService service = new CreatePersonService("João");
	service.execute();
    }

}
