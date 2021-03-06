package cabbooking.service;

import cabbooking.exceptions.CreateException;
import cabbooking.exceptions.DriverNotAvailableException;
import cabbooking.exceptions.DriverNotFoundException;
import cabbooking.exceptions.VehicleDoesNotExistException;
import cabbooking.model.*;

public class CabBookingService {

    public static CabBookingService cabBookingService = null;

    private CabBookingService() {

    }

    private UserService userService = UserService.getInstance();
    private RideService rideService = RideService.getInstance();

    public static CabBookingService getInstance() {
        if (cabBookingService == null) {
            return new CabBookingService();
        }
        return cabBookingService;
    }

    public Driver registerDriver(int id, String name, int age, String gender, Vehicle vehicle) throws CreateException {
        Driver driver = null;
        try {
            driver = userService.registerDriver(id, name, age, Gender.identifyGender(gender), vehicle);
        } catch (CreateException ex) {
            //log

        }
        return driver;
    }

    public Rider registerRider(int id, String name, int age, String gender, Location location) throws CreateException {
        Rider rider = null;
        try {
            rider = userService.registerRider(id, name, age, Gender.identifyGender(gender), location);
        } catch (CreateException ex) {
            //log

        }
        return rider;
    }

    public boolean updateLocation(String vehicleNumber, Location location) {
        try {
            userService.updateLocation(vehicleNumber, location);
        } catch (VehicleDoesNotExistException ex) {
            return false;
        }

        return true;
    }

    public boolean switchAvailability(int driverId, boolean val) {
        try {
            userService.toggleDriverAvailability(driverId, val);
        } catch (DriverNotFoundException ex) {
            return false;
        }
        return true;
    }

    public Driver bookRide(int riderId, Location location, Location toLocation) {
        try {
            return rideService.bookRide(riderId, location, toLocation);
        } catch (DriverNotAvailableException ex) {
            System.out.println("No driver available now.");
        }
        return null;
    }

    public Ride endRide(int riderId, int ratePerKm) {
        return rideService.endRide(riderId, ratePerKm);
    }
}
