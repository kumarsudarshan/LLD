package fooddelivery.repository;

import fooddelivery.exceptions.*;
import fooddelivery.model.account.User;
import fooddelivery.model.common.*;
import fooddelivery.model.food.Order;
import fooddelivery.model.food.Rating;
import fooddelivery.model.food.Restaurant;
import fooddelivery.service.NotificationService;
import fooddelivery.service.NotificationServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HashMapBasedStorage implements Storage {

    private static volatile HashMapBasedStorage INSTANCE = null;

    private HashMapBasedStorage() {

    }

    public static HashMapBasedStorage getInstance() {
        if (INSTANCE == null) {
            synchronized (HashMapBasedStorage.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HashMapBasedStorage();
                }
            }
        }
        return INSTANCE;
    }

    private HashMap<Integer, User> userHashMap = new HashMap<>();
    private HashMap<Long, Integer> phoneNumberMap = new HashMap<>();
    private HashMap<String, Restaurant> restaurantNameMap = new HashMap<>();
    private HashMap<Integer, Order> orderMap = new HashMap<>();

    private User loggedInUser = null; //keeping logged in user, initially no user is there
    private NotificationService notificationService = new NotificationServiceImpl();

    public User registerUser(Long phone, String name, Long pinCode, Gender gender) throws InvalidValueException {

        if (phoneNumberMap.containsKey(phone)) {
            User user = userHashMap.get(phoneNumberMap.get(phone));
            throw new InvalidValueException("User already exists");
        }
        User user = new User(KeyGenerator.getKey(), phone, name, pinCode, gender);
        phoneNumberMap.put(phone, user.getId());
        userHashMap.put(user.getId(), user);
        System.out.println("User Created successfully with id:" + user.getId());
        return user;
    }

    public User login(Long phone) throws AccountDoesNotExistsException {
        if (!phoneNumberMap.containsKey(phone)) {
            throw new AccountDoesNotExistsException("User does not exist - " + phone);
        }
        User user = userHashMap.get(phoneNumberMap.get(phone));

        loggedInUser = user;
        System.out.println("Logged in successfully : user id" + user.getId());
        System.out.println();
        return user;
    }

    public Restaurant registerRestaurant(String name, List<Long> pinCodeList, List<MenuItem> item, int price, int quantity) throws InvalidValueException {
        if (restaurantNameMap.containsKey(name)) {
            throw new InvalidValueException("Restaurant already exist");
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setId(KeyGenerator.getKey());
        restaurant.setName(name);
        restaurant.setMenuItem(item);
        restaurant.setQuantity(quantity);
        restaurant.setPrice(price);
        restaurant.setServiceablePincode(pinCodeList);
        restaurant.setCreatedBy(loggedInUser.getId());
        restaurantNameMap.put(name, restaurant);
        loggedInUser.getRestaurants().add(restaurant);
        System.out.println("Restaurant registered successfully : id" + restaurant.getId());
        return restaurant;
    }

    public Rating rateRestaurant(String name, Integer rating, String comment) throws ReservationNotFoundException, InvalidValueException {
        Restaurant restaurant = restaurantNameMap.get(name);
        if (restaurant == null) {
            throw new ReservationNotFoundException("Restaurant with given name not found : " + name);
        }
        if (loggedInUser.getId() != restaurant.getCreatedBy()) {
            throw new InvalidValueException("Restaurant owner can't give rating.");
        }
        Rating review = new Rating();
        review.setId(KeyGenerator.getKey());
        review.setComment(comment);
        review.setScore(rating);

        if (restaurant.getReviews() == null || restaurant.getReviews().size() == 0) {
            restaurant.setRating(Float.valueOf(rating));
        } else {
            float currentScore =
                    (restaurant.getRating() * restaurant.getReviews().size() + rating) / (restaurant.getReviews().size() + 1);
            restaurant.setRating(currentScore);
        }
        restaurant.getReviews().add(review);
        return review;
    }

    public Restaurant updateQuantity(String name, int quantity) throws RestaurantNotFoundException, InvalidValueException {
        Restaurant restaurant = restaurantNameMap.get(name);
        if (restaurant == null) {
            throw new RestaurantNotFoundException("Restaurant not found with given name: " + name);
        }
        if (loggedInUser.getId() != restaurant.getCreatedBy()) {
            throw new InvalidValueException("Other user can't update the quantity");
        }
        restaurant.setQuantity(restaurant.getQuantity() + quantity);
        return restaurant;
    }

    public List<Restaurant> showRestaurant(String sortBy) {
        List<Restaurant> restaurantList = loggedInUser.getRestaurants();
        List<Restaurant> restaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getServiceablePincode().contains(loggedInUser.getPinCode()) && restaurant.getQuantity() > 0) {
                restaurants.add(restaurant);
            }
        }

        // sort based on rating
        if (sortBy.equalsIgnoreCase("rating")) {
            Collections.sort(restaurants, new RateSorter());
            return restaurants;
        }

        //sort based on price
        if (sortBy.equalsIgnoreCase("price")) {
            Collections.sort(restaurants, new PriceSorter());
            return restaurants;
        }
        return restaurants;

    }

    public Order placeOrder(String name, String itemName, Integer quantity) throws RestaurantNotFoundException, InvalidQuantityStockException {
        Restaurant restaurant = restaurantNameMap.get(name);
        if (restaurant == null) {
            throw new RestaurantNotFoundException("Restaurant not found with given name: " + name);
        }
        if (restaurant.getQuantity() == 0) {
            throw new InvalidQuantityStockException("Out of stock, try later");
        }
        if (restaurant.getQuantity() < quantity) {
            throw new InvalidQuantityStockException("Restaurant can only serve " + restaurant.getQuantity() +
                    " quantity. Either order less quantity or try later");
        }
        Order order = new Order();
        order.setId(KeyGenerator.getKey());
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(itemName, quantity));
        order.setOrderItems(orderItemList);
        order.setQuantity(quantity);
        order.setUserId(loggedInUser.getId());
        order.setTimestamp(System.currentTimeMillis());
        order.setOrderStatus(OrderStatus.RECEIVED);
        order.setCost((long) (quantity * restaurant.getPrice()));

        // setting remaining quantity
        restaurant.setQuantity(restaurant.getQuantity() - quantity);

        loggedInUser.getOrders().add(order);
        orderMap.put(order.getId(), order);
        Notification notification = new Notification(loggedInUser.getId(), OrderStatus.RECEIVED, "Received your order");
        notificationService.notifyUser(notification);
        return order;
    }

    public Order dispatchOrder(int orderId) throws OrderNotFoundException {
        if (!orderMap.containsKey(orderId)) {
            throw new OrderNotFoundException("Order Not found!!!");
        }
        Order order = orderMap.get(orderId);
        order.setOrderStatus(OrderStatus.DISPATCHED);
        Notification notification = new Notification(loggedInUser.getId(), OrderStatus.DISPATCHED, "Dispatched your order");
        notificationService.notifyUser(notification);
        return order;
    }

    public List<Order> listOrders() {
        return loggedInUser.getOrders();
    }
}
