package fooddelivery.service;

import fooddelivery.repository.UserDao;
import fooddelivery.model.Order;

import java.util.List;

public class OrderService {
    private static OrderService instance = null;

    private OrderService() {

    }

    public static OrderService getInstance() {
        if (instance == null) {
            synchronized (OrderService.class) {
                if (instance == null) {
                    instance = new OrderService();
                }
            }
        }
        return instance;
    }

    UserDao userDao = UserDao.getInstance();

    public Order placeOrder(String name, Integer quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid value for mandatory fields");
            return null;
        }
        return userDao.placeOrder(name, quantity);
    }

    public List<Order> listOrders() {
        return userDao.listOrders();
    }
}
