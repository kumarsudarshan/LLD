package vehiclerental.service;


import vehiclerental.model.reservation.ReservationReminder;

public interface BookingReminderService {
    void notifyUser(ReservationReminder reservationReminder);
}
