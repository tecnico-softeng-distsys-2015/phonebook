package pt.tecnico.phonebook.service;

import pt.ist.fenixframework.FenixFramework;

import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class CreatePersonService extends PhoneBookService {

    private String personName;
        
    public CreatePersonService(String name) {
	this.personName = name;
    }

    public final void dispatch() throws NameAlreadyExistsException {
	getPhoneBook().addPerson(new Person(personName));
    }
}
