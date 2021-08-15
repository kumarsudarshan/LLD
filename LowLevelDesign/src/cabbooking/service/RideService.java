package cabbooking.service;

import cabbooking.repository.HashMapBasedStorage;
import cabbooking.exceptions.DriverNotAvailableException;
import cabbooking.model.Driver;
import cabbooking.model.Location;
import cabbooking.model.Ride;
import cabbooking.model.Rider;
import cabbooking.repository.Storage;
import cabbooking.repository.StorageFactory;
import cabbooking.repository.StorageType;

import java.util.ArrayList;
import java.util.List;

public class RideService {

    private Storage storage = null;

    private static Integer MAX_DISTANCE = 15;

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
        rider.getRides().add(ride);
        return driver.get(0);
    }

    public List<Driver> fetchAvailableDrivers(Location location) {
        return storage.getAllDriverWithinLocation(location, MAX_DISTANCE);
    }
}
