package fooddelivery.exceptions;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public OrderNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
