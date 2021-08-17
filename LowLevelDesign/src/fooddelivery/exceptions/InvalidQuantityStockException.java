package fooddelivery.exceptions;

public class InvalidQuantityStockException extends Exception {
    public InvalidQuantityStockException(String message) {
        super(message);
    }

    public InvalidQuantityStockException(Throwable throwable) {
        super(throwable);
    }

    public InvalidQuantityStockException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
