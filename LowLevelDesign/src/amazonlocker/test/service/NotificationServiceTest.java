package amazonlocker.test.service;

import amazonlocker.service.NotificationService;
import amazonlocker.test.TestData;
import amazonlocker.model.Locker;
import amazonlocker.repository.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NotificationServiceTest {
    static NotificationService notificationService;

    @Before
    public void setup() {
        LockerLocationRepository.lockerLocations.add(TestData.setupLockerLocation("RMBGBGKAIN", 12.876416, 77.595466));
        LockerLocationRepository.lockerLocations.add(TestData.setupLockerLocation("VMBGBGKAIN",12.909953, 77.601866));
        LockerPackageRepository.lockerPackages.add(TestData.getLockerPackage());

        LockerRepository.lockers.addAll(LockerLocationRepository.getLockerLocation("RMBGBGKAIN").getLockers());
        for (Locker locker : LockerRepository.lockers) {
            LockerRepository.lockerMap.put(locker.getId(), locker);
        }

        OrderRepository.orderMap.put("o1", TestData.getPhoneOrder());
        OrderRepository.orderMap.put("o2", TestData.getHeadSetOrder());
        notificationService = new NotificationService();
    }

    @Test
    public void emulateNotification() {
        notificationService.notifyCustomerOrder(TestData.getLockerPackage());
        Assert.assertEquals("o1", NotificationRepository.notificationMap.get("o1").getOrderId());
    }
}
