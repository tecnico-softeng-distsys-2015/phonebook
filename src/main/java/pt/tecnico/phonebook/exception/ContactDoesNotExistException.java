package pt.tecnico.phonebook.exception;

public class ContactDoesNotExistException extends PhoneBookException {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private String contactName;

	public ContactDoesNotExistException(String contactName) {
		this.contactName = contactName;
	}
	
	public String getContactName() {
		return this.contactName;
	}
}
