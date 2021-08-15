package cabbooking.service;

import cabbooking.exceptions.CreateException;
import cabbooking.repository.HashMapBasedStorage;
import cabbooking.exceptions.DriverNotFoundException;
import cabbooking.exceptions.VehicleDoesNotExistException;
import cabbooking.model.*;
import cabbooking.repository.Storage;
import cabbooking.repository.StorageFactory;
import cabbooking.repository.StorageType;

public class UserService {

    public static UserService userService = null;

    private Storage storage = null;

    private UserService() {
        storage = StorageFactory.getDBStorage(StorageType.IN_MEMORY_STORE);
    }

    public static UserService getInstance() {
        if (userService == null) {
            return new UserService();
        }

        return userService;
    }

    public Driver registerDriver(int id, String name, int age, Gender gender, Vehicle vehicle) throws CreateException {
        return storage.createDriver(id, name, age, gender, vehicle);
    }

    public Rider registerRider(int id, String name, int age, Gender gender, Location location) throws CreateException {
        return storage.createRider(id, name, age, gender, location);
    }


    public boolean updateLocation(String vehicleNumber, Location location) throws VehicleDoesNotExistException {
        return storage.updateLocation(vehicleNumber, location);
    }

    public boolean toggleDriverAvailability(int driverId, boolean val) throws DriverNotFoundException {
        return storage.toggleAvailability(driverId, val);
    }
}
