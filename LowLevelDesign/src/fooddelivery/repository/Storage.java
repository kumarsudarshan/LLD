package fooddelivery.repository;

import fooddelivery.exceptions.*;
import fooddelivery.model.account.User;
import fooddelivery.model.common.Gender;
import fooddelivery.model.common.MenuItem;
import fooddelivery.model.food.Order;
import fooddelivery.model.food.Rating;
import fooddelivery.model.food.Restaurant;

import java.util.List;

public interface Storage {
    public User registerUser(Long phone, String name, Long pinCode, Gender gender) throws InvalidValueException;

    public User login(Long  phone) throws AccountDoesNotExistsException;

    public Restaurant registerRestaurant(String name, List<Long> pinCodeList, List<MenuItem> item, int price, int quantity) throws InvalidValueException;

    public Rating rateRestaurant(String name, Integer rating, String comment) throws ReservationNotFoundException, InvalidValueException;

    public Restaurant updateQuantity(String name, int quantity) throws RestaurantNotFoundException, InvalidValueException;

    public List<Restaurant> showRestaurant(String sortBy);

    public Order placeOrder(String name, String itemName, Integer quantity) throws RestaurantNotFoundException, InvalidQuantityStockException;

    public Order dispatchOrder(int orderId) throws OrderNotFoundException;

    public List<Order> listOrders();

}
