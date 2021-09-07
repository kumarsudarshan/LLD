package quickride.model.account;

import quickride.model.Ride;
import quickride.model.common.Location;

import java.util.Random;

public class Rider extends Account {
    private Ride currentRide;
    private int totalRidesCompleted;
    private int rating;
    private Location currentLocation;

    public Rider(String name, String gender, Long phone, Location currentLocation, String email, String password) {
        super(new Random().nextInt(), name, gender, phone, email, password);
        this.currentLocation = currentLocation;
    }

    public Ride getCurrentRide() {
        return currentRide;
    }

    public void setCurrentRide(Ride currentRide) {
        this.currentRide = currentRide;
    }

    public int getTotalRidesCompleted() {
        return totalRidesCompleted;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
