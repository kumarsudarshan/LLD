package vehiclerental.service;

import vehiclerental.model.reservation.Invoice;
import vehiclerental.model.reservation.ReservationStatus;
import vehiclerental.model.reservation.VehicleReservation;

public class InvoiceServiceImpl implements InvoiceService {
    InvoiceServiceFactory invoiceServiceFactory = new InvoiceServiceFactory();

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        if (vehicleReservation.getStatus() == ReservationStatus.CANCELLED)
            return InvoiceBuilderUtil.buildCancelledInvoice(vehicleReservation);
        return invoiceServiceFactory.getInvoiceService(vehicleReservation.getVehicleReservationType())
                .computeInvoice(vehicleReservation);
    }
}
