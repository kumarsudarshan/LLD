package vehiclerental.service;

import vehiclerental.model.account.User;
import vehiclerental.model.common.NotificationStatus;
import vehiclerental.model.reservation.InvoiceNotification;
import vehiclerental.model.reservation.VehicleReservation;
import vehiclerental.repository.UserRepository;
import vehiclerental.repository.VehicleReservationRepository;

public class InvoiceNotificationServiceImpl implements InvoiceNotificationService {

    public void notifyUser(InvoiceNotification invoiceNotification) {
        VehicleReservation vehicleReservation = VehicleReservationRepository.vehicleReservationMap
                .get(invoiceNotification.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        System.out.println("Notification sent " + user.getContact().getEmail());
        invoiceNotification.setNotificationStatus(NotificationStatus.SENT);
    }
}
