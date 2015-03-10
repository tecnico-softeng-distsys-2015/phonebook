package pt.tecnico.phonebook.exception;

public class ImportDocumentException extends PhoneBookException {
    public ImportDocumentException() {
	super("Error in importing person from XML");
    }
}
