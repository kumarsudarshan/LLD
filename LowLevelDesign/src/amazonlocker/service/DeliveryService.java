package amazonlocker.service;

import amazonlocker.exception.PackageSizeMappingException;
import amazonlocker.model.*;
import amazonlocker.model.Package;
import amazonlocker.repository.LockerPackageRepository;
import amazonlocker.utils.IdGenerator;
import amazonlocker.utils.SizeUtil;

import java.util.List;

public class DeliveryService {
    NotificationService notificationService = new NotificationService();
    OrderService orderService = new OrderService();
    LockerService lockerService = new LockerService();

    /*
    Steps:
        1. fetch order details
        2. fetch item lists on that order
        3. create new package
        4. get locker size based on the package size
        5. get locker details based on locker size and geo location
        6. create new locker package and set status of that locker as closed
        7. notify to the user
     */
    public void deliver(String orderId) throws PackageSizeMappingException {
        Order order = orderService.getOrder(orderId);
        List<Item> items = orderService.getItemsForOrder(orderId);
        Package aPackage = new Package(orderId, items);
        LockerSize lockerSize = SizeUtil.getLockerSizeForPack(aPackage.getPackageSize());
        Locker locker = lockerService.getLocker(lockerSize, order.getDeliveryGeoLocation());
        LockerPackage lockerPackage = new LockerPackage();
        lockerPackage.setOrderId(orderId);
        lockerPackage.setLockerId(locker.getId());
        lockerPackage.setCode(IdGenerator.generateId(6));
        LockerPackageRepository.lockerPackages.add(lockerPackage);
        locker.setLockerStatus(LockerStatus.CLOSED);
        notificationService.notifyCustomerOrder(lockerPackage);
    }
}
