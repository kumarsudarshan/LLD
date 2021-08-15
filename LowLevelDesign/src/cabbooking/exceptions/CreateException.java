package cabbooking.exceptions;

public class CreateException extends Exception {
    public CreateException(String message) {
        super(message);
    }

    public CreateException(Throwable throwable) {
        super(throwable);
    }

    public CreateException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
