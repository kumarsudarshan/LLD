package vehiclerental.service;


import vehiclerental.model.reservation.InvoiceNotification;

public interface InvoiceNotificationService {
    void notifyUser(InvoiceNotification invoice);
}
