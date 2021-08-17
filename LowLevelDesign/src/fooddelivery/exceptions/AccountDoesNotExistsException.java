package fooddelivery.exceptions;

public class AccountDoesNotExistsException extends Exception {
    public AccountDoesNotExistsException(String message) {
        super(message);
    }

    public AccountDoesNotExistsException(Throwable throwable) {
        super(throwable);
    }

    public AccountDoesNotExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}