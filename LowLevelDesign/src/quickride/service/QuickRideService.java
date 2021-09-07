package quickride.service;

import quickride.exceptions.DriverNotExistsException;
import quickride.exceptions.RideDoesNotExistsException;
import quickride.model.Ride;
import quickride.model.RideStatus;
import quickride.model.account.Account;
import quickride.model.account.Driver;
import quickride.model.account.Rider;
import quickride.model.common.Location;
import quickride.repository.AccountStorage;
import quickride.repository.RideStorage;
import quickride.repository.StorageFactory;
import quickride.repository.StorageType;

import java.util.ArrayList;
import java.util.List;

public class QuickRideService {

    private static QuickRideService INSTANCE = null;
    private RideStorage rideStorage;
    private AccountStorage riderStorage;
    private AccountStorage driverStorage;

    private QuickRideService() {
        rideStorage = StorageFactory.getRideStorage(StorageType.IN_MEMORY);
        riderStorage = StorageFactory.getRiderStorage(StorageType.IN_MEMORY);
        driverStorage = StorageFactory.getDriverStorage(StorageType.IN_MEMORY);
    }

    public static QuickRideService getInstance() {
        if (INSTANCE == null) {
            synchronized (QuickRideService.class) {
                if (INSTANCE == null) {
                    return new QuickRideService();
                }
            }
        }
        return INSTANCE;
    }

    public Account createDriverAccount(Account driver) {
        return driverStorage.createAccount(driver);
    }

    public boolean updateDriverLocation(String driverEmailId, Location currentLocation) {
        return driverStorage.updateLocation(driverEmailId, currentLocation);
    }

    public Account createRiderAccount(Account rider) {
        return riderStorage.createAccount(rider);
    }

    public boolean updateRiderLocation(String riderEmailId, Location currentLocation) {
        return riderStorage.updateLocation(riderEmailId, currentLocation);
    }

    public List<Ride> searchRides(Location fromLocation, Location toLocation, int distance) {
        List<Ride> rideList = rideStorage.getRideList();
        List<Ride> result = new ArrayList<>();
        for (Ride ride : rideList) {
            if (ride.getRideStatus() == RideStatus.IDLE &&
                    ride.getTotalOccupiedSeats() < ride.getTotalSeats() &&
                    distance(ride.getFromLocation(), fromLocation) <= distance &&
                    distance(ride.getToLocation(), toLocation) <= distance) {
                result.add(ride);
            }
        }
        return result;
    }

    public Ride requestRide(Rider rider, int rideId) throws RideDoesNotExistsException, DriverNotExistsException {
        Ride ride = rideStorage.searchRide(rideId);
        if (ride == null) {
            throw new RideDoesNotExistsException("Ride id:" + rideId + " does not exists");
        }
        synchronized (this) {
            ride.setRideStatus(RideStatus.ACCEPTED);
            ride.setTotalOccupiedSeats(ride.getTotalOccupiedSeats() + 1);
            updateRide(ride, ride.getCreatedBy());
            System.out.println("Ride accepted");
        }
        return ride;
    }

    public void createRide(Ride ride) throws DriverNotExistsException {
        if (!driverStorage.accountExists(ride.getCreatedBy())) {
            throw new DriverNotExistsException("Driver with mail id : " + ride.getCreatedBy() + " does not exists");
        }
        rideStorage.saveRide(ride);
    }

    public void updateRide(Ride ride, String driverMailId) throws DriverNotExistsException {
        if (!driverStorage.accountExists(driverMailId)) {
            throw new DriverNotExistsException("Driver with mail id : " + driverMailId + " does not exists");
        }
        rideStorage.updateRide(ride);
    }

    public void finishRide(int rideId, String driverMailId, String riderMailId, int pricePerKM) throws DriverNotExistsException {
        if (!driverStorage.accountExists(driverMailId)) {
            throw new DriverNotExistsException("Driver with mail id : " + driverMailId + " does not exists");
        }
        rideStorage.finishRide(driverMailId, riderMailId, rideId, pricePerKM);
        System.out.println("Ride completed successfully");
    }

    public int totalFareDetails(String mailId, int rideId) {
        int fare =  rideStorage.fareDetails(mailId, rideId);
        System.out.println("Total fare: " + fare);
        return fare;
    }

    private double distance(Location x, Location y) {
        double dy = y.getLongitude() - x.getLongitude();
        double dx = y.getLatitude() - x.getLatitude();
        return Math.sqrt(dy * dy - dx * dx);
    }

}
