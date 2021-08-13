package vehiclerental.model.reservation;

import lombok.Getter;
import lombok.Setter;
import vehiclerental.model.common.NotificationStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class InvoiceNotification {
    private String reservationId;
    private String userId;
    private LocalDateTime createdDate;
    private NotificationStatus notificationStatus;
}
