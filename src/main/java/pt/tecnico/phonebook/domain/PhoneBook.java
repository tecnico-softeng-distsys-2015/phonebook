package pt.tecnico.phonebook.domain;

import org.jdom2.Element;

import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class PhoneBook extends PhoneBook_Base {

    public static PhoneBook getInstance() {
        PhoneBook pb = FenixFramework.getDomainRoot().getPhonebook();
        if (pb == null)
            pb = new PhoneBook();

        return pb;
    }

    private PhoneBook() {
        FenixFramework.getDomainRoot().setPhonebook(this);
    }

    public Person getPersonByName(String name) {
        for (Person person : getPersonSet()) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public boolean hasPerson(String personName) {
        return getPersonByName(personName) != null;
    }

    @Override
    public void addPerson(Person personToBeAdded) throws NameAlreadyExistsException {
        if (hasPerson(personToBeAdded.getName()))
            throw new NameAlreadyExistsException(personToBeAdded.getName());

        super.addPerson(personToBeAdded);
    }

    public void importPersonFromXML(Element personElement) {
	String personName = personElement.getAttribute("name").getValue();
	Person person = getPersonByName(personName);

	if (person == null) {
	    person = new Person(personName);
	}

	person.importFromXML(personElement);
    }
}
