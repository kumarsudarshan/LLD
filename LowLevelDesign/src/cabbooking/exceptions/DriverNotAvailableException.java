package cabbooking.exceptions;

public class DriverNotAvailableException extends Exception {

    public DriverNotAvailableException(String message) {
        super(message);
    }

    public DriverNotAvailableException(Throwable throwable) {
        super(throwable);
    }

    public DriverNotAvailableException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
