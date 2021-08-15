package cabbooking.repository;

import cabbooking.exceptions.CreateException;
import cabbooking.exceptions.DriverNotFoundException;
import cabbooking.exceptions.VehicleDoesNotExistException;
import cabbooking.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapBasedStorage implements Storage {

    public Map<Integer, Rider> riderMap = new HashMap<Integer, Rider>();

    public Map<Integer, Driver> driverMap = new HashMap<Integer, Driver>();

    public Map<String, Vehicle> vehicleMap = new HashMap<String, Vehicle>();

    private static HashMapBasedStorage INSTANCE = null;

    private HashMapBasedStorage() {

    }

    public static HashMapBasedStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HashMapBasedStorage();
        }
        return INSTANCE;
    }

    public Driver createDriver(int id, String name, int age, Gender gender, Vehicle vehicle) throws CreateException {
        Driver driver = new Driver(id, name, age, gender, vehicle);
        if (driver == null || driverMap.containsKey(driver.getId())) {
            throw new CreateException("Create Driver failed!!!");
        }
        driverMap.put(driver.getId(), driver);
        vehicleMap.put(driver.getVehicle().getVenhicleNumber(), vehicle);
        return driver;
    }

    public Rider createRider(int id, String name, int age, Gender gender, Location location) throws CreateException {
        Rider rider = new Rider(id, name, age, gender, location);
        if (rider == null || riderMap.containsKey(rider.getId())) {
            throw new CreateException("Create Rider failed!!!");
        }
        riderMap.put(rider.getId(), rider);

        return rider;
    }

    public boolean updateLocation(String vehicleNumber, Location location) throws VehicleDoesNotExistException {
        if (vehicleMap.containsKey(vehicleNumber)) {
            vehicleMap.get(vehicleNumber).setLocation(location);
            return true;
        } else {
            throw new VehicleDoesNotExistException("Vehicle Number doest not exist.");
        }
    }

    public boolean toggleAvailability(int id, boolean val) throws DriverNotFoundException {
        if (driverMap.containsKey(id)) {
            driverMap.get(id).setAvailable(val);
        } else {
            throw new DriverNotFoundException("Driver Id not found");
        }
        return true;
    }

    @Override
    public Rider getRider(int riderId) {
        return riderMap.get(riderId);
    }

    @Override
    public List<Driver> getAllDriverWithinLocation(Location location, int maxRange) {
        List<Driver> driverList = new ArrayList<Driver>();
        for (Driver driver : driverMap.values()) {
            if (driver.isAvailable() && distance(driver.getVehicle().getLocation(), location) < maxRange) {
                driverList.add(driver);
            }
        }
        return driverList;
    }

    public int distance(Location l1, Location l2) {
        return (int) Math.sqrt(
                Math.pow(l2.getLongitude() - l1.getLongitude(), 2) +
                        Math.pow(l2.getLatitude() - l1.getLatitude(), 2)
        );
    }
}
