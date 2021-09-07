package quickride.repository;

import quickride.model.Ride;

import java.util.List;

public interface RideStorage {
    public List<Ride> getRideList();

    public Ride searchRide(int id);

    public void saveRide(Ride ride);

    public boolean updateRide(Ride ride);

    public boolean finishRide(String driverMailId, String mailId, int rideId, int pricePerKM);

    public int fareDetails(String mailId, int rideId);

}
