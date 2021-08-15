package cabbooking.exceptions;

public class VehicleDoesNotExistException extends Exception {

    public VehicleDoesNotExistException(String message) {
        super(message);
    }

    public VehicleDoesNotExistException(Throwable throwable) {
        super(throwable);
    }

    public VehicleDoesNotExistException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
