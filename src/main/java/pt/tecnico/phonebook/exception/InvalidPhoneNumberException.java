package pt.tecnico.phonebook.exception;

public class InvalidPhoneNumberException extends PhoneBookException {

    private static final long serialVersionUID = 1L;

    private int phoneNumber;

    public InvalidPhoneNumberException(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getInvalidPhoneNumber() {
        return this.phoneNumber;
    }
}
