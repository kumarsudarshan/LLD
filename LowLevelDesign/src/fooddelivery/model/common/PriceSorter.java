package fooddelivery.model.common;


import fooddelivery.model.food.Restaurant;

import java.util.Comparator;

public class PriceSorter implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        return r1.getPrice() - r2.getPrice();
    }
}
