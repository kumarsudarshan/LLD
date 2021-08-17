package fooddelivery.model.food;

import fooddelivery.model.common.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private List<MenuItem> menuItem;
    private int price;
    private List<Long> serviceablePincode;
    private int quantity;
    private Float rating;
    private List<Rating> ratings = new ArrayList<>();

    private Integer createdBy;

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public List<Rating> getReviews() {
        return ratings;
    }

    public void setReviews(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(List<MenuItem> menuItem) {
        this.menuItem = menuItem;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Long> getServiceablePincode() {
        return serviceablePincode;
    }

    public void setServiceablePincode(List<Long> serviceablePincode) {
        this.serviceablePincode = serviceablePincode;
    }
}