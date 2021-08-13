package vehiclerental.service;

import vehiclerental.model.account.User;
import vehiclerental.model.reservation.Invoice;
import vehiclerental.model.reservation.VehicleFixedCosts;
import vehiclerental.model.reservation.VehicleReservation;
import vehiclerental.model.vehicle.HireableVehicle;
import vehiclerental.repository.UserRepository;
import vehiclerental.repository.VehicleRepository;

import java.util.UUID;

public class InvoiceBuilderUtil {
    public static Invoice buildCancelledInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(hireableVehicle.getVehicleType());
        double taxes = fixedCost * .18;
        invoice.setUsageCharges(fixedCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(fixedCost + taxes);
        return invoice;
    }
}
