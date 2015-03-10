package pt.tecnico.phonebook.service;

import pt.ist.fenixframework.FenixFramework;
import java.io.UnsupportedEncodingException;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.exception.ExportDocumentException;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class ExportPhoneBookService extends PhoneBookService {
    private byte[] doc;
    private String personName;

    public ExportPhoneBookService(String personName) {
	this.personName = personName;
    }

    @Override
    protected void dispatch() throws ExportDocumentException, PersonDoesNotExistException{
	org.jdom2.Document jdomDoc = new org.jdom2.Document();
        Person person = getPerson(this.personName);

	jdomDoc.setRootElement(person.exportToXML());

	XMLOutputter xml = new XMLOutputter();
	try {
	    this.doc = xml.outputString(jdomDoc).getBytes("UTF-8");
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	    throw new ExportDocumentException();
        }
    }

    public final byte[] getResult() {
	return this.doc;
    }
}
