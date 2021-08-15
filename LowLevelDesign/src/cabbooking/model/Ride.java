package cabbooking.model;

public class Ride {

    private Driver driver;
    private Rider ride;
    private TripStatus tripStatus;
    private int tripPrice;
    private Location fromLocation;
    private Location toLocation;

    public Ride(Driver drive, Rider rider, Location fromLocation, Location toLocation) {
        this.driver = driver;
        this.ride = ride;
        this.tripStatus = TripStatus.NOT_STARTED;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rider getRide() {
        return ride;
    }

    public void setRide(Rider ride) {
        this.ride = ride;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public int getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(int tripPrice) {
        this.tripPrice = tripPrice;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public void setToLocation(Location toLocation) {
        this.toLocation = toLocation;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "driver=" + driver +
                ", ride=" + ride +
                ", tripStatus=" + tripStatus +
                ", tripPrice=" + tripPrice +
                ", fromLocation=" + fromLocation +
                ", toLocation=" + toLocation +
                '}';
    }
}
