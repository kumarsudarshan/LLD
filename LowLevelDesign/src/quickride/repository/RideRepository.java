package quickride.repository;

import quickride.model.Ride;
import quickride.model.RideStatus;
import quickride.model.common.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRepository implements RideStorage {
    Map<Integer, Ride> rideMap = new HashMap<>();
    Map<String, Map<Integer, Integer>> userPriceMap = new HashMap<>();

    private static RideRepository INSTANCE = null;

    private RideRepository() {

    }

    public static RideRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (RideRepository.class) {
                if (INSTANCE == null) {
                    return new RideRepository();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public List<Ride> getRideList() {
        List<Ride> rideList = new ArrayList<>();
        for (Map.Entry<Integer, Ride> rideEntry : rideMap.entrySet()) {
            rideList.add(rideEntry.getValue());
        }
        return rideList;
    }

    @Override
    public Ride searchRide(int id) {
        return rideMap.get(id);
    }

    @Override
    public void saveRide(Ride ride) {
        rideMap.put(ride.getId(), ride);
    }

    @Override
    public boolean updateRide(Ride ride) {
        if (rideMap.containsKey(ride.getId())) {
            rideMap.put(ride.getId(), ride);
            return true;
        }
        return false;
    }

    @Override
    public boolean finishRide(String driverMailId, String riderMailId, int rideId, int pricePerKM) {
        Ride ride = rideMap.get(rideId);
        ride.setTotalOccupiedSeats(ride.getTotalOccupiedSeats() - 1);
        ride.setRideStatus(RideStatus.COMPLETED);

        Map<Integer, Integer> driverPriceMap = new HashMap<>();
        if (userPriceMap.containsKey(driverMailId)) {
            driverPriceMap = userPriceMap.get(driverMailId);
        }
        driverPriceMap.put(rideId, (int) (pricePerKM * distance(ride.getFromLocation(), ride.getToLocation())));
        userPriceMap.put(driverMailId, driverPriceMap);

        Map<Integer, Integer> riderPriceMap = new HashMap<>();
        if (userPriceMap.containsKey(riderMailId)) {
            riderPriceMap = userPriceMap.get(riderMailId);
        }
        riderPriceMap.put(rideId, (int) (pricePerKM * distance(ride.getFromLocation(), ride.getToLocation())));
        userPriceMap.put(riderMailId, riderPriceMap);

        return true;
    }

    @Override
    public int fareDetails(String mailId, int rideId) {
        return userPriceMap.get(mailId).get(rideId);
    }

    private double distance(Location x, Location y) {
        double dy = y.getLongitude() - x.getLongitude();
        double dx = y.getLatitude() - x.getLatitude();
        return Math.sqrt(dy * dy - dx * dx);
    }
}
