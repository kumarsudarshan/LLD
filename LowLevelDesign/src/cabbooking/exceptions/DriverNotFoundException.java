package cabbooking.exceptions;

public class DriverNotFoundException extends Exception {
    public DriverNotFoundException(String message) {
        super(message);
    }

    public DriverNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public DriverNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
