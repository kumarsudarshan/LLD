package vehiclerental.service;


import vehiclerental.model.account.User;
import vehiclerental.model.common.NotificationStatus;
import vehiclerental.model.reservation.ReservationReminder;
import vehiclerental.model.reservation.VehicleReservation;
import vehiclerental.repository.UserRepository;
import vehiclerental.repository.VehicleReservationRepository;

public class BookingReminderServiceImpl implements BookingReminderService {

    VehicleReservationRepository vehicleReservationRepository = new VehicleReservationRepository();

    @Override
    public void notifyUser(ReservationReminder reservationReminder) {
        VehicleReservation vehicleReservation =
                vehicleReservationRepository.getVehicleReservation(reservationReminder.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        System.out.println("Notified user" + user.getContact().getEmail());
        reservationReminder.setNotificationStatus(NotificationStatus.SENT);
    }
}
