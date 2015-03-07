package pt.tecnico.phonebook.service;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;
import pt.tecnico.phonebook.exception.PhoneBookException;

public abstract class PhoneBookService {

    @Atomic
    public final void execute() throws PhoneBookException {
        dispatch();
    }

    static PhoneBook getPhoneBook() {
        return FenixFramework.getDomainRoot().getPhonebook();
    }

    static Person getPerson(String personName) throws PersonDoesNotExistException {
        Person p = getPhoneBook().getPersonByName(personName);

        if (p == null)
            throw new PersonDoesNotExistException(personName);

        return p;
    }

    protected abstract void dispatch() throws PhoneBookException;
}
