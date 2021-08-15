package cabbooking.model;

public abstract class Vehicle {
    private String vehicleName;
    private String vehicleNumber;
    private int availableSeats;
    private VehicleType type;
    private Location location;

    public Vehicle(String vehicleName, String vehicleNumber, Location location, VehicleType type) {
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.location = location;
        this.availableSeats = this.type.seats;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVenhicleNumber() {
        return vehicleNumber;
    }

    public void setVenhicleNumber(String venhicleNumber) {
        this.vehicleNumber = venhicleNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public String toString() {
        return "Vehicle [vehicleName=" + vehicleName + ", vehicleNumber=" + vehicleNumber + ", availableSeats="
                + availableSeats + ", type=" + type + ", location=" + location + "]";
    }
}
