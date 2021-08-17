package fooddelivery;

import fooddelivery.exceptions.*;
import fooddelivery.model.account.User;
import fooddelivery.model.common.Gender;
import fooddelivery.model.common.MenuItem;
import fooddelivery.model.food.Order;
import fooddelivery.model.food.Rating;
import fooddelivery.model.food.Restaurant;
import fooddelivery.service.OrderService;
import fooddelivery.service.RestaurantService;
import fooddelivery.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {
    public static void main(String[] args) throws InvalidValueException, AccountDoesNotExistsException, ReservationNotFoundException, RestaurantNotFoundException, InvalidQuantityStockException {
        UserService userService = UserService.getInstance();
        RestaurantService restaurantService = RestaurantService.getInstance();
        OrderService orderService = OrderService.getInstance();

        User user1 = userService.registerUser(9901442238l, "Kumar", 560037L, Gender.MALE);
        User user2 = userService.registerUser(9507735111l, "Sudarshan", 560045L, Gender.MALE);
        User user3 = userService.registerUser(9905688762l, "Rahul", 560040L, Gender.MALE);

        User logIn = userService.login(user1.getPhone());
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("pizza", 100, 5));
        Restaurant r1 = restaurantService.registerRestaurant("Restaurant1", Arrays.asList(560037l, 560040l, 560045l), menuItemList, 100, 10);

        menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("burger", 100, 5));
        Restaurant r2 = restaurantService.registerRestaurant("Restanrant2", Arrays.asList(560037l, 560045l), menuItemList, 50, 15);
        r1 = restaurantService.updateQuantity("Restaurant1", 5);
        logIn = userService.login(user2.getPhone());

        menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("sandwich", 100, 5));
        Restaurant r3 = restaurantService.registerRestaurant("Restuarant3", Arrays.asList(560037l, 560040l), menuItemList, 50, 15);

        logIn = userService.login(user3.getPhone());

        Restaurant r4 = restaurantService.registerRestaurant("Restaurant4", Arrays.asList(3l, 4l), menuItemList, 50, 15);

        logIn = userService.login(user1.getPhone());
        restaurantService.showRestaurant("rating");
        Order order = orderService.placeOrder("Restaurant1", "pizza", 5);
        Rating rating = restaurantService.rateRestaurant("Restaurant1", 5, "good");
        List<Order> orderList = orderService.listOrders();
        restaurantService.showRestaurant("rating");

    }
}
