package fooddelivery.service;

import fooddelivery.exceptions.InvalidValueException;
import fooddelivery.exceptions.ReservationNotFoundException;
import fooddelivery.exceptions.RestaurantNotFoundException;
import fooddelivery.model.common.MenuItem;
import fooddelivery.model.food.Rating;
import fooddelivery.model.food.Restaurant;
import fooddelivery.repository.Storage;
import fooddelivery.repository.StorageFactory;
import fooddelivery.repository.StorageType;

import java.util.List;

public class RestaurantService {
    private static volatile RestaurantService INSTANCE = null;

    private Storage storage = null;

    private RestaurantService() {
        storage = StorageFactory.getDBStorage(StorageType.IN_MEMORY_STORE);
    }

    public static RestaurantService getInstance() {
        if (INSTANCE == null) {
            synchronized (RestaurantService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RestaurantService();
                }
            }
        }
        return INSTANCE;
    }

    public Restaurant registerRestaurant(String name, List<Long> pinCodes, List<MenuItem> menuItem, int price, int quantity) throws InvalidValueException {
        if (price <= 0 || quantity < 0) {
            throw new InvalidValueException("Invalid quantity or price value");
        } else if (name.isEmpty()) {
            System.out.println("invalid value for name");
            throw new InvalidValueException("Name is empty");
        }
        return storage.registerRestaurant(name, pinCodes, menuItem, price, quantity);
    }

    public Rating rateRestaurant(String name, Integer rating, String comment) throws InvalidValueException, ReservationNotFoundException {
        if (rating == null || rating <= 0 || rating > 5) {
            throw new InvalidValueException("Invalid rating value, must be from 1 to 5");
        }
        return storage.rateRestaurant(name, rating, comment);
    }

    public Restaurant updateQuantity(String name, int quantity) throws InvalidValueException, RestaurantNotFoundException {
        if (quantity <= 0) {
            throw new InvalidValueException("Invalid quantity fields");
        }
        return storage.updateQuantity(name, quantity);
    }

    public List<Restaurant> showRestaurant(String sortBy) {
        return storage.showRestaurant(sortBy);
    }
}