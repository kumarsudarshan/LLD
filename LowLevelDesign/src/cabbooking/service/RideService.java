package cabbooking.service;

import cabbooking.exceptions.DriverNotAvailableException;
import cabbooking.model.*;
import cabbooking.repository.Storage;
import cabbooking.repository.StorageFactory;
import cabbooking.repository.StorageType;

import java.util.List;

public class RideService {

    private Storage storage = null;

    private static Integer MAX_DISTANCE = 3;

    public static RideService rideService = null;

    private RideService() {
        storage = StorageFactory.getDBStorage(StorageType.IN_MEMORY_STORE);
    }

    public static RideService getInstance() {
        if (rideService == null) {
            return new RideService();
        }
        return rideService;
    }

    public Driver bookRide(int riderId, Location fromLocation, Location toLocation) throws DriverNotAvailableException {
        Rider rider = storage.getRider(riderId);
        List<Driver> driver = fetchAvailableDrivers(fromLocation);
        if (driver.isEmpty()) throw new DriverNotAvailableException("No driver available now.");
        driver.get(0).setAvailable(false);
        Ride ride = new Ride(driver.get(0), rider, fromLocation, toLocation);
        ride.setTripStatus(TripStatus.IN_PROGRESS);
        rider.getRides().add(ride);
        storage.setRide(riderId, ride);
        return driver.get(0);
    }

    public List<Driver> fetchAvailableDrivers(Location location) {
        return storage.getAllDriverWithinLocation(location, MAX_DISTANCE);
    }

    public Ride endRide(int riderId, int ratePerKm) {
        Ride ride = storage.getRide(riderId);
        ride.setTripStatus(TripStatus.FINISHED);
        int tripPrice = calculateTripPrice(ratePerKm, ride.getFromLocation(), ride.getToLocation());
        ride.setTripPrice(tripPrice);
        storage.setRide(riderId, ride);
        return ride;
    }

    private int calculateTripPrice(int ratePerKm, Location fromLocation, Location toLocation) {
        return ratePerKm * distance(fromLocation, toLocation);
    }

    private int distance(Location l1, Location l2) {
        return (int) Math.sqrt(
                Math.pow(l2.getLongitude() - l1.getLongitude(), 2) +
                        Math.pow(l2.getLatitude() - l1.getLatitude(), 2)
        );
    }
}
