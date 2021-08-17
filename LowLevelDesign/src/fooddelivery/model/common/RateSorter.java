package fooddelivery.model.common;

import fooddelivery.model.food.Restaurant;

import java.util.Comparator;

public class RateSorter implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        if (r1.getRating() == null || r2.getRating() == null) return 0;
        if (r1.getRating() > r2.getRating()) {
            return 1;
        } else if (r1.getRating() == r2.getRating()) {
            return 0;
        } else {
            return -1;
        }
    }
}
