package pt.tecnico.phonebook.exception;

public class ExportDocumentException extends PhoneBookException {

    private static final long serialVersionUID = 1L;

    public ExportDocumentException() {
        super("Error in importing person from XML");
    }
}
