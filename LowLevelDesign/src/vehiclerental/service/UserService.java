package vehiclerental.service;


import vehiclerental.exceptions.InvalidVehicleIdException;
import vehiclerental.exceptions.ReservationNotFoundException;
import vehiclerental.exceptions.VehicleBookedException;
import vehiclerental.model.reservation.VehicleReservation;
import vehiclerental.model.vehicle.HireableVehicle;
import vehiclerental.model.vehicle.VehicleLocation;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    VehicleReservation scanToReserve(String qrCode, String userId) throws InvalidVehicleIdException, VehicleBookedException;

    VehicleReservation remoteReserve(VehicleReservation vehicleReservation);

    VehicleReservation cancel(String reservationId);

    HireableVehicle pickupVehicle(VehicleReservation vehicleReservation);

    void returnVehicle(String reservationId, VehicleLocation vehicleLocation) throws ReservationNotFoundException;

    List<HireableVehicle> getHiredVehicles(String userId);

    List<HireableVehicle> getHiredVehicles(String userId, LocalDateTime startDate,
                                           LocalDateTime endDate);
}
