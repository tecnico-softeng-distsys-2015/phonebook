package pt.tecnico.phonebook.exception;

public abstract class PhoneBookException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PhoneBookException() {
    }

    public PhoneBookException(String msg) {
        super(msg);
    }
}
