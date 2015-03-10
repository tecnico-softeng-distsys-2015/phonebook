package pt.tecnico.phonebook.service;

import pt.ist.fenixframework.FenixFramework;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.exception.ImportDocumentException;

public class ImportPhoneBookService extends PhoneBookService {
    private final byte[] doc;
        
    public ImportPhoneBookService(byte[] doc) {
	this.doc = doc;
    }

    @Override
    protected void dispatch() throws ImportDocumentException{
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

	Element rootElement = jdomDoc.getRootElement();
	getPhoneBook().importPersonFromXML(rootElement);
    }    
}
