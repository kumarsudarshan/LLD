package cabbooking.model;

public class Car extends Vehicle {

    public Car(String vehicleName, String vehicleNumber, Location location, VehicleType vehicleType) {
        super(vehicleName, vehicleNumber, location, vehicleType);
    }

    @Override
    public String toString() {
        return "Car [getAvailableSeats()=" + getAvailableSeats() + ", getVehicleName()=" + getVehicleName()
                + ", getVenhicleNumber()=" + getVenhicleNumber() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
