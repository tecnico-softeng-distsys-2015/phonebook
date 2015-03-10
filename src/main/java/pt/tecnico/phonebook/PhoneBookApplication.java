package pt.tecnico.phonebook;

import java.util.Set;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.TransactionManager;

import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;

import pt.tecnico.phonebook.service.CreateContactService;
import pt.tecnico.phonebook.service.CreatePersonService;
import pt.tecnico.phonebook.service.ListPeopleService;
import pt.tecnico.phonebook.service.ListPersonPhoneBook;
import pt.tecnico.phonebook.service.ImportPhoneBookService;
import pt.tecnico.phonebook.service.ExportPhoneBookService;
import pt.tecnico.phonebook.service.RemovePersonService;
import pt.tecnico.phonebook.service.dto.ContactDto;
import pt.tecnico.phonebook.exception.ImportDocumentException;
import pt.tecnico.phonebook.exception.ExportDocumentException;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;
import pt.tecnico.phonebook.exception.InvalidPhoneNumberException;

import javax.transaction.*;

public class PhoneBookApplication {

    public static void main(String[] args) {
        System.out.println("Welcome to the PhoneBook application!");

	populateDomain();

	ListPeopleService listPeople = new ListPeopleService();
	listPeople.execute();

	for (String personName : listPeople.getResult()) {
	    System.out.println("The Contact book of " + personName + " :");
	    ListPersonPhoneBook listPhoneBook = new ListPersonPhoneBook(personName);
	    listPhoneBook.execute();
	    for(ContactDto contact : listPhoneBook.getResult()) {
		System.out.println("\t Name: " + contact.getName() + " phone: " + contact.getPhoneNumber());
	    }
	}

        byte[] docManel = convertToXML("Manel");

        printDomainInXML(docManel);

        byte[] docMaria = convertToXML("Maria");

        printDomainInXML(docMaria);

        removeAllPeople();

        byte[] doc = convertToXML("Manel");

        doc = convertToXML("Maria");

        recoverFromBackup(docManel);
        recoverFromBackup(docMaria);

        doc = convertToXML("Manel");
	printDomainInXML(doc);

        doc = convertToXML("Maria");
	System.out.println("doc = " + doc);
	printDomainInXML(doc);
    }

    private static void recoverFromBackup(byte[] doc) {
	try {
	    ImportPhoneBookService importService = new ImportPhoneBookService(doc);
	    importService.execute();
	} catch (ImportDocumentException ide) {
	    System.err.println("Error importing document");
	}
    }

    @Atomic
    private static boolean isInicialized() {
	PhoneBook pb = PhoneBook.getInstance();
	return !pb.getPersonSet().isEmpty();
    }

    private static void createPerson(String personName) {
	try {
	    CreatePersonService createPerson = new CreatePersonService(personName);
	    createPerson.execute();
	} catch (NameAlreadyExistsException nae) {
	    System.err.println("Error creating a person: " + nae.getMessage());
	}
    }

    private static void createContact(String personName, String contactName, int phoneNumber) {
	try {
	    CreateContactService createContact = new CreateContactService(personName, contactName, phoneNumber);
	    createContact.execute();
	} catch (NameAlreadyExistsException | InvalidPhoneNumberException| PersonDoesNotExistException ie) {
	    System.err.println("Error creating a contact: " + ie.getMessage());
	}
    }

    static void populateDomain() {
        if (isInicialized())
            return;

        // setup the initial state if phonebook is empty

	createPerson("Manel");
	createContact("Manel", "SOS", 112);
	createContact("Manel", "IST", 214315112);

 	createPerson("Maria");
	createContact("Maria", "SOS", 112);
	createContact("Maria", "IST", 214315112);
	createContact("Maria", "Manel", 333333333);
    }
        
    public static byte[] convertToXML(String personName) {
	try {
	    ExportPhoneBookService exportService = new ExportPhoneBookService(personName);
	    
	    exportService.execute();
	    return exportService.getResult();
	} catch (ExportDocumentException | PersonDoesNotExistException de) {
	    System.err.println("Error in exporting to XML: " + de.getMessage());
	}
        return null;
    }

    public static void printDomainInXML(byte[] doc) {
	if (doc == null) {
	    System.err.println("Null Document to print");
	    return;
	}

	org.jdom2.Document jdomDoc;
        SAXBuilder builder = new SAXBuilder();
        builder.setIgnoringElementContentWhitespace(true);
        try {
            jdomDoc = builder.build(new ByteArrayInputStream(doc));
        } catch (JDOMException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ImportDocumentException();
        }

        XMLOutputter xml = new XMLOutputter();
        xml.setFormat(Format.getPrettyFormat());
        System.out.println(xml.outputString(jdomDoc));
    }

    static void removeAllPeople() {
	ListPeopleService listPeople = new ListPeopleService();
	listPeople.execute();

	for (String personName : listPeople.getResult()) {
	    RemovePersonService removePerson = new RemovePersonService(personName);
	    removePerson.execute();
        }
    }
}
