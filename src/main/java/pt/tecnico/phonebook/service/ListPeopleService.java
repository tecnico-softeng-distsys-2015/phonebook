package pt.tecnico.phonebook.service;

import java.util.ArrayList;
import java.util.List;

import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;

public class ListPeopleService extends PhoneBookService {

    private List<String> registeredPeople;

    public ListPeopleService() {
    }

    public final void dispatch() {
        PhoneBook pb = getPhoneBook();
        registeredPeople = new ArrayList<>();

        for (Person p : pb.getPersonSet()) {
            registeredPeople.add(p.getName());
        }

    }

    public final List<String> getResult() {
        return registeredPeople;
    }
}
