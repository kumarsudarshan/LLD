package vehiclerental.service;

import vehiclerental.exceptions.VehicleNotExistsException;
import vehiclerental.model.vehicle.HireableVehicle;

public interface VehicleService {

    HireableVehicle getVehicleById(String id);

    HireableVehicle getVehicleByQrCode(String qrCode);

    HireableVehicle addVehicle(HireableVehicle hireableVehicle);

    void updateQrCode(String vehicleId, String qrCode) throws VehicleNotExistsException;

    void removeVehicle(String vehicleId) throws VehicleNotExistsException;
}
