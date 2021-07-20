package billsharing.service;

import billsharing.model.Expense;
import billsharing.model.User;

public interface NotificationService {
    void notifyUser(User user, Expense expense);
}
