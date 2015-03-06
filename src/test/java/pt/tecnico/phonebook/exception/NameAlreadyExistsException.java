package pt.tecnico.phonebook.exception;

public class NameAlreadyExistsException extends PhoneBookException {

	private static final long serialVersionUID = 1L;

	private String conflictingName;
	
	public NameAlreadyExistsException(String conflictingName) {
		this.conflictingName = conflictingName;
	}
	
	public String getConflictingName() {
		return this.conflictingName;
	}
	
}
