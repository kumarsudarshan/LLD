package quickride.model;

import quickride.model.common.Location;

import java.util.Random;

public class Ride {
    private int id;
    int totalSeats;
    int totalOccupiedSeats;
    private Location fromLocation;
    private Location toLocation;
    private RideStatus rideStatus;
    private String createdBy;

    public Ride(int totalSeats, Location fromLocation, Location toLocation, String createdBy) {
        this.id = new Random().nextInt();
        this.totalSeats = totalSeats;
        this.totalOccupiedSeats = 0;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.rideStatus = RideStatus.IDLE;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalOccupiedSeats() {
        return totalOccupiedSeats;
    }

    public void setTotalOccupiedSeats(int totalOccupiedSeats) {
        this.totalOccupiedSeats = totalOccupiedSeats;
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

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", totalSeats=" + totalSeats +
                ", totalOccupiedSeats=" + totalOccupiedSeats +
                ", fromLocation=" + fromLocation +
                ", toLocation=" + toLocation +
                ", rideStatus=" + rideStatus +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
