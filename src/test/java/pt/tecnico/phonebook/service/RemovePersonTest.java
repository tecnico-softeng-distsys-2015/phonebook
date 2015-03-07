package pt.tecnico.phonebook.service;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class RemovePersonTest extends AbstractServiceTest {

    private Person added;

    protected void initializeDomain() {
        PhoneBook pb = PhoneBook.getInstance();
        added = new Person("João");

        pb.addPerson(added);
    }

    @Test
    public void success() {
        final String personName = "João";
        RemovePersonService service = new RemovePersonService(personName);
        service.execute();

        // check person was removed
        assertFalse("user was not removed", PhoneBookService.getPhoneBook().hasPerson(personName));
    }

    @Test
    public void successRemovePersonWithContacts() {
        final String personName = "João";

        added.addContact(new Contact("ww", 123));
        added.addContact(new Contact("ww2", 123));

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
