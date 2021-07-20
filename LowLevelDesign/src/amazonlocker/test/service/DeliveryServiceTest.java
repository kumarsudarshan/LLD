package amazonlocker.test.service;

import amazonlocker.service.DeliveryService;
import amazonlocker.test.TestData;
import amazonlocker.exception.PackageSizeMappingException;
import amazonlocker.model.Locker;
import amazonlocker.model.Notification;
import amazonlocker.repository.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeliveryServiceTest {
    static DeliveryService deliveryService;

    @Before
    public void setup() {
        LockerLocationRepository.lockerLocations.add(TestData.setupLockerLocation("RMBGBGKAIN",12.876416, 77.595466));
        LockerLocationRepository.lockerLocations.add(TestData.setupLockerLocation("VMBGBGKAIN", 12.909953, 77.601866));
        LockerPackageRepository.lockerPackages.add(TestData.getLockerPackage());

        LockerRepository.lockers.addAll(LockerLocationRepository.getLockerLocation("RMBGBGKAIN").getLockers());
        for (Locker locker : LockerRepository.lockers) {
            LockerRepository.lockerMap.put(locker.getId(), locker);
        }

        OrderRepository.orderMap.put("o1", TestData.getPhoneOrder());
        OrderRepository.orderMap.put("o2", TestData.getHeadSetOrder());
        deliveryService = new DeliveryService();
    }

    @Test
    public void emulateDelivery() throws PackageSizeMappingException {
        deliveryService.deliver("o1");
        Notification notification = NotificationRepository.notificationMap.get("o1");
        Assert.assertEquals("o1", notification.getOrderId());
    }
}
