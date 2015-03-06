package pt.tecnico.phonebook.service;

import pt.ist.fenixframework.Atomic;

import pt.ist.fenixframework.FenixFramework;

import pt.tecnico.phonebook.exception.PhoneBookException;
import pt.tecnico.phonebook.domain.PhoneBook;

public abstract class PhoneBookService {

    @Atomic
    public final void execute() throws PhoneBookException {
        dispatch();
    }

    static PhoneBook getPhoneBook() {
	return FenixFramework.getDomainRoot().getPhonebook();
    }

    protected abstract void dispatch() throws PhoneBookException;
}
