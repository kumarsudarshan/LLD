package amazonlocker.service;

import amazonlocker.model.Item;
import amazonlocker.model.Locker;
import amazonlocker.model.LockerStatus;

public class ReturnsService {

    public void returnItemsToLocker(Item item, Locker locker) {
        locker.setLockerStatus(LockerStatus.CLOSED);
    }
}
