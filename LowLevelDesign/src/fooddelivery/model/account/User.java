package fooddelivery.model.account;

import fooddelivery.model.common.Gender;
import fooddelivery.model.food.Order;
import fooddelivery.model.food.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class User extends Account {

    private int id;
    private String name;
    private Gender gender;
    private Long phone;
    private Long pinCode;
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }


    public User(int id, Long phone, String name, Long pinCode, Gender gender) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.pinCode = pinCode;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }
}