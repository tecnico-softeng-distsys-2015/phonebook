package pt.tecnico.phonebook.service;

import pt.ist.fenixframework.FenixFramework;

import java.util.List;
import java.util.ArrayList;

import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.service.dto.ContactDto;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class ListPersonPhoneBook extends PhoneBookService {

    private List<ContactDto> phonebook;
    private String personName;
        
    public ListPersonPhoneBook(String personName) {
	this.personName = personName;
    }

    public final void dispatch() throws PersonDoesNotExistException {
	Person person = getPerson(this.personName);
	phonebook = new ArrayList<>();

	for (Contact c : person.getContactSet()) {
	    phonebook.add(new ContactDto(c.getName(), c.getPhoneNumber()));
	}
    }

    public final List<ContactDto> getResult() {
	return phonebook;
    }
}
