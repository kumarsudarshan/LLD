package cabbooking.repository;

import cabbooking.exceptions.CreateException;
import cabbooking.exceptions.DriverNotFoundException;
import cabbooking.exceptions.VehicleDoesNotExistException;
import cabbooking.model.*;

import java.util.List;
import java.util.Map;

public interface Storage {

    public Driver createDriver(int id, String name, int age, Gender gender, Vehicle vehicle) throws CreateException;

    public Rider createRider(int id, String name, int age, Gender gender, Location location) throws CreateException;

    public boolean updateLocation(String vehicleNumber, Location location) throws VehicleDoesNotExistException;

    public boolean toggleAvailability(int id, boolean val) throws DriverNotFoundException;

    public Rider getRider(int riderId);

    public List<Driver> getAllDriverWithinLocation(Location location, int maxRange);

    Ride getRide(int riderId);

    void setRide(int riderId, Ride ride);
}
