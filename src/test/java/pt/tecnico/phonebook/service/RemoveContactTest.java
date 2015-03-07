package pt.tecnico.phonebook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.exception.ContactDoesNotExistException;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class RemoveContactTest extends AbstractServiceTest {

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
        RemoveContactService service = new RemoveContactService(personName, "António");
        service.execute();

        // check contact was removed
        Contact c = getContact(personName, "António");
        assertNull("contact was not removed", c);
        assertEquals("Invalid number of contacts", 0, PhoneBookService.getPerson(personName).getContactSet().size());
    }

    @Test(expected = ContactDoesNotExistException.class)
    public void removeInvalidContact() {
        final String personName = "João";
        RemoveContactService service = new RemoveContactService(personName, "Ant");
        service.execute();
    }

    @Test(expected = PersonDoesNotExistException.class)
    public void invalidPerson() {
        final String personName = "José";
        RemoveContactService service = new RemoveContactService(personName, "António");
        service.execute();
    }
}
