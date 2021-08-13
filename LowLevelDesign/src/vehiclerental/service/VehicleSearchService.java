package vehiclerental.service;

import vehiclerental.model.vehicle.HireableVehicle;
import vehiclerental.model.vehicle.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleSearchService {
    List<HireableVehicle> search(VehicleType vehicleType, String city,
                                 LocalDateTime fromDate, LocalDateTime toDate);

    List<HireableVehicle> search(String make, String city,
                                 String model, LocalDateTime fromDate, LocalDateTime toDate);

    List<HireableVehicle> search(int seats, String city,
                                 LocalDateTime fromDate, LocalDateTime toDate);
}
