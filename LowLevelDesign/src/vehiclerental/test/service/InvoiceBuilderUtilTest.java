package vehiclerental.test.service;

import org.junit.Assert;
import org.junit.Test;
import vehiclerental.exceptions.InvalidVehicleIdException;
import vehiclerental.exceptions.VehicleBookedException;
import vehiclerental.model.account.User;
import vehiclerental.model.reservation.Invoice;
import vehiclerental.model.reservation.VehicleInventory;
import vehiclerental.model.reservation.VehicleReservation;
import vehiclerental.model.vehicle.HireableVehicle;
import vehiclerental.repository.UserRepository;
import vehiclerental.repository.VehicleInventoryRepository;
import vehiclerental.repository.VehicleRepository;
import vehiclerental.service.InvoiceBuilderUtil;
import vehiclerental.service.UserService;
import vehiclerental.service.UserServiceImpl;
import vehiclerental.test.TestData;

import java.util.List;

public class InvoiceBuilderUtilTest {
    @Test
    public void shouldBuildCancelledInvoice()
            throws VehicleBookedException, InvalidVehicleIdException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }

        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());

        vehicleReservation = userService.cancel(vehicleReservation.getReservationId());

        Invoice invoice = InvoiceBuilderUtil.buildCancelledInvoice(vehicleReservation);

        Assert.assertNotNull(invoice);
        Assert.assertEquals(invoice.getUsageCharges(), 50, 0);
        Assert.assertEquals(invoice.getTaxes(), 9, 0);
        Assert.assertEquals(invoice.getTotal(), 59, 0);
    }
}
