package vehiclerental.service;


import vehiclerental.model.reservation.Invoice;
import vehiclerental.model.reservation.VehicleReservation;

public interface InvoiceService {
    Invoice computeInvoice(VehicleReservation vehicleReservation);
}
