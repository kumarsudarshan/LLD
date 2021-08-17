package fooddelivery.exceptions;

public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException(String message) {
        super(message);
    }

    public ReservationNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public ReservationNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}