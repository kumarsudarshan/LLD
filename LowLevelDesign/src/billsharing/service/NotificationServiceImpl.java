package billsharing.service;

import billsharing.model.Expense;
import billsharing.model.User;

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifyUser(User user, Expense expense) {
        System.out.println("Notified");
    }
}
