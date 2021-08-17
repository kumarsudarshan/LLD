package fooddelivery.service;

import fooddelivery.exceptions.InvalidQuantityStockException;
import fooddelivery.exceptions.InvalidValueException;
import fooddelivery.exceptions.OrderNotFoundException;
import fooddelivery.exceptions.RestaurantNotFoundException;
import fooddelivery.model.food.Order;
import fooddelivery.repository.Storage;
import fooddelivery.repository.StorageFactory;
import fooddelivery.repository.StorageType;

import java.util.List;

public class OrderService {
    private static OrderService INSTANCE = null;
    private Storage storage = null;

    private OrderService() {
        storage = StorageFactory.getDBStorage(StorageType.IN_MEMORY_STORE);
    }

    public static OrderService getInstance() {
        if (INSTANCE == null) {
            synchronized (OrderService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OrderService();
                }
            }
        }
        return INSTANCE;
    }

    public Order placeOrder(String name, String itemName, Integer quantity) throws InvalidValueException, InvalidQuantityStockException, RestaurantNotFoundException {
        if (quantity <= 0) {
            throw new InvalidValueException("Invalid quantity value");
        }
        return storage.placeOrder(name, itemName, quantity);
    }

    public Order dispatchOrder(int orderId) throws OrderNotFoundException {
        return storage.dispatchOrder(orderId);
    }

    public List<Order> listOrders() {
        return storage.listOrders();
    }
}
