package fooddelivery.exceptions;

public class RestaurantNotFoundException extends Exception {
    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public RestaurantNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
