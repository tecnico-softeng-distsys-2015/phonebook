package pt.tecnico.phonebook.service;

import pt.ist.fenixframework.FenixFramework;

import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class RemovePersonService extends PhoneBookService {

    private String personName;
        
    public RemovePersonService(String name) {
	this.personName = name;
    }

    public final void dispatch() throws PersonDoesNotExistException {
	Person p = getPerson(this.personName);

	p.delete();
    }
}
