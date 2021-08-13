package vehiclerental.service;

import vehiclerental.model.reservation.VehicleReservation;
import vehiclerental.model.vehicle.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleReservationService {
    List<VehicleReservation> getReservations(VehicleType vehicleType);

    boolean isVehicleBooked(String qrCode, LocalDateTime fromDate, LocalDateTime toDate);
}
