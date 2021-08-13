package vehiclerental.model.vehicle;

import lombok.Getter;
import lombok.Setter;
import vehiclerental.model.common.Coordinates;

@Getter
@Setter
public abstract class HireableVehicle {
    private String id;
    private String licensePlateNumber;
    private String qrCode;
    private String make;
    private String model;
    private int yearOfManufacture;
    private double mileage;
    private int numberOfSeats;
    private VehicleCategory vehicleCategory;
    private VehicleStatus vehicleStatus;
    private VehicleType vehicleType;
    private VehicleLocation parkedLocation;
    private Coordinates currentLocation;
}
