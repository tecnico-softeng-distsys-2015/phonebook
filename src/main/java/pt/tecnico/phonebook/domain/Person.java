package pt.tecnico.phonebook.domain;

public class Person extends Person_Base {
    
    public Person(String name) {
        super();
        setName(name);
    }                 
    
    @Override
    public void setPhoneBook(PhoneBook pb) {
	if (pb == null)
	    super.setPhoneBook(null);
	else
	    pb.addPerson(this);
    }
}
