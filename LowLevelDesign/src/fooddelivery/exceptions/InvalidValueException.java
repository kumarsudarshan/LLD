package fooddelivery.exceptions;

public class InvalidValueException extends Exception {
    public InvalidValueException(String message) {
        super(message);
    }

    public InvalidValueException(Throwable throwable) {
        super(throwable);
    }

    public InvalidValueException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
