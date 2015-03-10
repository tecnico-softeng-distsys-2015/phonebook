package pt.tecnico.phonebook.exception;

public class ExportDocumentException extends PhoneBookException {
    public ExportDocumentException() {
	super("Error in importing person from XML");
    }
}
