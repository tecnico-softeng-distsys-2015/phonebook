package pt.tecnico.phonebook.domain;

import src.main.java.pt.tecnico.phonebook.exception.ImportDocumentException;

public class Contact extends Contact_Base {

    protected Contact() {
    }

    public Contact(String name, Integer phoneNumber) throws InvalidPhoneNumberException {
        setName(name);
        setPhoneNumber(phoneNumber);
    }

    @Override
    public void setPhoneNumber(Integer phoneNumber) {
        if (phoneNumber <= 0) {
            throw new InvalidPhoneNumberException(phoneNumber);
        }
        super.setPhoneNumber(phoneNumber);
    }

    @Override
    public void setPerson(Person person) throws NameAlreadyExistsException {
        if (person == null) {
            super.setPerson(null);
            return;
        }

        person.addContact(this);
    }

    public void delete() {
        setPerson(null);
        deleteDomainObject();
    }

    public void importFromXML(Element contactElement) throws ImportDocumentException {
        setName(contactElement.getAttribute("name").getValue());
        try {
            setPhoneNumber(contactElement.getAttribute("phoneNumber").getIntValue());
        } catch (DataConversionException e) {
            throw new ImportDocumentException();
        }
    }
}
