package amazonlocker.test.service;

import amazonlocker.exception.*;
import amazonlocker.model.Package;
import amazonlocker.service.DeliveryService;
import amazonlocker.service.LockerService;
import amazonlocker.test.TestData;
import amazonlocker.model.*;
import amazonlocker.repository.*;
import amazonlocker.utils.SizeUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class LockerServiceTest {
    static LockerService lockerService;
    static DeliveryService deliveryService;

    @Before
    public void setup() {
        LockerLocationRepository.lockerLocations.add(TestData.setupLockerLocation("RMBGBGKAIN",12.876416, 77.595466));
        LockerLocationRepository.lockerLocations.add(TestData.setupLockerLocation("VMBGBGKAIN",12.909953, 77.601866));
        LockerPackageRepository.lockerPackages.add(TestData.getLockerPackage());

        LockerRepository.lockers.addAll(LockerLocationRepository.getLockerLocation("RMBGBGKAIN").getLockers());
        for (Locker locker : LockerRepository.lockers) {
            LockerRepository.lockerMap.put(locker.getId(), locker);
        }

        OrderRepository.orderMap.put("o1", TestData.getPhoneOrder());
        OrderRepository.orderMap.put("o2", TestData.getHeadSetOrder());
        lockerService = new LockerService();
        deliveryService = new DeliveryService();
    }

    @Test
    public void shouldReturnLocker() {
        String lockerId = LockerRepository.lockers.get(0).getId();
        Assert.assertNotNull(lockerService.findLockerIbyId(lockerId));
    }

    @Test
    public void shouldGetLocker() {
        Locker locker = lockerService.getLocker(LockerSize.XS, new GeoLocation(12.909953, 77.601866));
        Assert.assertNotNull(locker);
    }

    @Test
    public void shouldGetLockerSizeForPack() throws PackageSizeMappingException {
        Package aPackage = TestData.getPackage();
        LockerSize lockerSize = SizeUtil.getLockerSizeForPack(aPackage.getPackageSize());
        System.out.println(lockerSize);
        Assert.assertNotNull(lockerSize);
    }

    @Test
    public void emulatePickFromLocker() throws PackageSizeMappingException, LockeCodeMisMatchException, LockerNotFoundException, PackPickTimeExceededException, PickupCodeExpiredException {
        deliveryService.deliver("o1");
        Notification notification = NotificationRepository.notificationMap.get("o1");
        LockerPackage lockerPackage = LockerPackageRepository.lockerPackages.stream().filter(lockerPackage1 -> lockerPackage1.getOrderId().equals("o1")).findFirst().get();
        lockerService.pickFromLocker(lockerPackage.getLockerId(), lockerPackage.getCode(), LocalDateTime.now());

        Assert.assertEquals(LockerStatus.AVAILALBE, LockerRepository.lockerMap.get(notification.getLockerId()).getLockerStatus());
    }
}
